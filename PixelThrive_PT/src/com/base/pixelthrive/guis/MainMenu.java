package com.base.pixelthrive.guis;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Color;

import com.base.engine.Button;
import com.base.engine.Font;
import com.base.engine.Input;
import com.base.engine.Render;
import com.base.engine.Vector2f;
import com.base.engine.Window;
import com.base.pixelthrive.Block;
import com.base.pixelthrive.GUI;
import com.base.pixelthrive.PTGame;
import com.base.pixelthrive.World;

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
	private CopyOnWriteArrayList<Splash> splash = new CopyOnWriteArrayList<Splash>();

	private int buttSpace = 20;

	private Color[][] pix = new Color[Window.getWidth() / 16 + 1][Window.getHeight() / 16 + 1];

	private float titleScale = 0f, titleSpeed = 1f;

	private final String[] splashes = {"Now available in North Korea!", "Thanks to DarkKnight!", "Thanks to PhuckYuToo", 
			"Thanks to Ca$h!", "OMG THIS GAME HAS SQUARES, MINECRAFT RIPOFF!", "GG!", 
			"Powered by 9 volt batteries!", "ARMIIIIIN!!!", "Don't let the old man get to you!",
			"PhuckYu? PhuckYuToo!", "'68, into the world born!", "We're taking over this town!",
			"Don't let the rain kill you!", "Are you talking to me?", "Sascha is a fatass!",
			"OH NO! IT'S AN ABNORMAL!", "Now reloaded with .42 caliber bullets!", 
			"It costs 90000 dollars to play this for 2 seconds!", "Microtransactions suck!",
			"You're a wizard, Jerry!", "Ya can't see California without MB's eyes!",
			"See who gives a phuck!", "Is this real life?", "Now with the dark side!",
			"It's time to RISE!", "War is not the answer!", "Enter the carnival of souls!",
			"What in the heaven?", "Less content. More goat.", "Master!", "Disorder, disorder!",
			"1v1 me rust get rekt son", "MIKASAAAA!!!", "Everyone is a world before they are a man!",
			"Now gluten free!", "Diabeetus sucks", "Fat plumbers sold seperately!"};

	private World world;
	
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
		for(int i = 0; i < 2; i++) spawnSplash();
	}

	public void update(float delta)
	{
		if(active)
		{
			titleSpeed -= 0.01f;
			if(titleSpeed > 0f) titleScale = 1f - titleSpeed;

			for(Button button : mainMenuButtons) button.update(delta);
			for(int i = 0; i < getSplashes().size(); i++)
			{
				if(getSplashes().get(i).isOut)
				{
					getSplashes().remove(i);
					continue;
				}
				getSplashes().get(i).update(delta);
			}
			notAvailable.update(delta);
			notAvailable.setPos(Input.getFixedMousePosition().add(6));

			if(new Random().nextInt(100) == 0 && getSplashes().size() < 8) spawnSplash();
		}
		else
		{
			world.update(delta);
		}
	}

	public void input(float delta)
	{
		if(active)
		{
			for(Button button : mainMenuButtons) button.input(delta);
			if(exit.isClicked()) System.exit(0);
			if(singlePlayer.isClicked()) initSP();
			notAvailable.input(delta);
		}
	}

	public void render()
	{
		if(active)
		{
			Render.pushMatrix();
			for(int x = 0; x < pix.length; x++) for(int y = 0; y < pix[0].length; y++) Render.drawRectangle(new Vector2f(x, y).mul(16), new Vector2f(16), pix[x][y]);
			for(Splash spl : getSplashes()) spl.render();
			Render.pushMatrix();
			Render.scale(new Vector2f(Window.getSize().getX(), 40).div(2), titleScale);
			Render.drawCenteredStringFixedY(Vector2f.ZERO, new Vector2f(Window.getSize().getX(), 40), PTGame.TITLE, new Color(0xffffff), title, new Vector2f(3));
			Render.popMatrix();
			Render.drawString(Window.getSize().sub(Render.getStringSize(PTGame.VERSION, version)), PTGame.VERSION, new Color(0xffffff), version, new Vector2f(3));
			for(Button button : mainMenuButtons) button.render(Vector2f.ZERO);
			if(multiPlayer.isHover() && !multiPlayer.isEnabled()) notAvailable.render();
			Render.popMatrix();
		}
		else
		{
			world.render();
		}
	}

	private void spawnSplash()
	{
		int y = new Random().nextInt(Window.getHeight() - 20);
		for(Splash spl : getSplashes()) if(spl.getPos().getY() == y) return;
		getSplashes().add(new Splash(y, splashes[new Random().nextInt(splashes.length)], new Random().nextInt(30) + 20));
	}

	public synchronized CopyOnWriteArrayList<Splash> getSplashes()
	{
		return splash;
	}

	public class Splash
	{
		private String splash;
		private Font font;
		private Color col;
		private Vector2f pos;
		public boolean isOut = false;
		private float moveSpeed = new Random().nextFloat() + 1.2f;

		public Splash(int y, String txt, float scale)
		{
			splash = txt;
			font = Render.getFont(scale);
			int r = new Random().nextInt(215) + 40;
			int g = new Random().nextInt(215) + 40;
			int b = new Random().nextInt(215) + 40;
			col = new Color(r, g, b);
			pos = new Vector2f(Window.getWidth() + 2, y);
		}

		public Vector2f getPos()
		{
			return pos;
		}

		public void update(float delta)
		{
			if(pos.getX() > -Render.getStringSize(splash, font).getX()) pos = pos.sub(moveSpeed, 0);
			else isOut = true;
		}

		public void render()
		{
			Render.drawString(pos, splash, col, font, new Vector2f(2));
		}
	}
	
	private void initSP()
	{
		world = new World(this, Block.SCREEN_BLOCK);
		this.active = false;
	}
}
