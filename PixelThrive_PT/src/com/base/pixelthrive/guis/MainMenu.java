package com.base.pixelthrive.guis;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;

import com.base.engine.Button;
import com.base.engine.Font;
import com.base.engine.Input;
import com.base.engine.Render;
import com.base.engine.Vector2f;
import com.base.engine.Window;
import com.base.pixelthrive.GUI;
import com.base.pixelthrive.PTGame;

public class MainMenu extends GUI
{
	private Font title = Render.getFont(57f), version = Render.getFont(30f);

	private Vector2f buttonSize = new Vector2f(250, 40);
	private TextBox notAvailable = new TextBox("This feature is currently not available.", 20f);
	private Button singlePlayer = new Button(Window.getAbsoluteCenter(buttonSize), buttonSize, 1);
	private Button multiPlayer = new Button(Window.getAbsoluteCenter(buttonSize), buttonSize, 1);
	private Button options = new Button(Window.getAbsoluteCenter(buttonSize), buttonSize, 1);
	private Button exit = new Button(Window.getAbsoluteCenter(buttonSize), buttonSize, 1);

	private ArrayList<Button> mainMenuButtons = new ArrayList<Button>();

	private int buttSpace = 20;

	private Color[][] pix = new Color[Window.getWidth() / 16 + 1][Window.getHeight() / 16 + 1];

	public MainMenu()
	{
		singlePlayer.setText("Singleplayer", 26f, new Vector2f(2));
		multiPlayer.setText("Multiplayer", 26f, new Vector2f(2));
		multiPlayer.setEnabled(false);
		options.setText("Options", 26f, new Vector2f(2));
		exit.setText("Exit", 26f, new Vector2f(2));
		mainMenuButtons.add(singlePlayer);
		mainMenuButtons.add(multiPlayer);
		mainMenuButtons.add(options);
		mainMenuButtons.add(exit);
		for(int i = 0; i < mainMenuButtons.size(); i++) mainMenuButtons.get(i).setPos(Window.getAbsoluteCenter(buttonSize).add(0, (buttSpace * mainMenuButtons.size()) - ((buttonSize.getY() + buttSpace) * (mainMenuButtons.size() - 1 - i))));

		for(int x = 0; x < pix.length; x++)
			for(int y = 0; y < pix[0].length; y++)
			{
				pix[x][y] = new Color(60, 180, 220);
				if(new Random().nextBoolean())
				{
					if(new Random().nextBoolean()) pix[x][y] = pix[x][y].darker(0.15f);
					else pix[x][y] = pix[x][y].brighter(0.15f);
				}
			}
	}

	public void update(float delta)
	{
		if(active)
		{
			for(Button button : mainMenuButtons) button.update(delta);
			notAvailable.update(delta);
			notAvailable.setPos(Input.getFixedMousePosition().add(6));
		}
	}

	public void input(float delta)
	{
		if(active)
		{
			for(Button button : mainMenuButtons) button.input(delta);
			notAvailable.input(delta);
		}
	}

	public void render()
	{
		if(active)
		{
			Render.pushMatrix();

			//			Render.drawRectangle(Vector2f.ZERO, Window.getSize(), new Color(60, 180, 220)); //background
			for(int x = 0; x < pix.length; x++)
				for(int y = 0; y < pix[0].length; y++)
				{
					Render.drawRectangle(new Vector2f(x, y).mul(16), new Vector2f(16), pix[x][y]);
				}


			Render.drawCenteredStringFixedY(Vector2f.ZERO, new Vector2f(Window.getSize().getX(), 40), PTGame.TITLE, new Color(0xffffff), title, new Vector2f(3)); //title
			Render.drawString(Window.getSize().sub(Render.getStringSize(PTGame.VERSION, version)), PTGame.VERSION, new Color(0xffffff), version, new Vector2f(3)); //version text
			for(Button button : mainMenuButtons) button.render(Vector2f.ZERO);
			if(multiPlayer.isHover() && !multiPlayer.isEnabled()) notAvailable.render();
			Render.popMatrix();
		}
	}
}
