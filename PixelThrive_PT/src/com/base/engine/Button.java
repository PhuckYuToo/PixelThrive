package com.base.engine;

import java.awt.Rectangle;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

public class Button
{
	private Vector2f pos, size, shadow;
	private Rectangle button = new Rectangle();
	private boolean isDown = false, isHover = false, enabled = true;
	private int choice = 0, choices;

	private Font font;
	private String txt = "";
	private int borderSize = 2;

	private Color buttonCol;

	public Button(Vector2f pos, Vector2f size, int choices)
	{
		this.choices = choices;
		this.pos = pos;
		this.size = size;
		int r = new Random().nextInt(160) + 40;
		int g = new Random().nextInt(160) + 40;
		int b = new Random().nextInt(160) + 40;
		buttonCol = new Color(r, g, b);
	}

	public Button(Vector2f min, Vector2f max, Vector2f size, int choices)
	{
		this(min.add(max.div(2)).sub(size.div(2)), size, choices);
	}

	public void setText(String txt, float size, Vector2f shadow)
	{
		this.txt = txt;
		font = Render.getFont(size);
		this.shadow = shadow;
	}

	public void setPos(Vector2f pos)
	{
		this.pos = pos;
	}

	public void setBorderSize(int size)
	{
		borderSize = size;
	}

	public void input(float delta)
	{
		if(button.contains(Mouse.getX(), Window.getHeight() - Mouse.getY())) isHover = true;
		else isHover = false;
		if(isHover && enabled)
		{
			if(Input.getMouse(0)) isDown = true;
			else isDown = false;
		}
		else isDown = false;
		if(isHover && Input.getMouseDown(0)) nextChoice();
	}

	public void update(float delta)
	{
		button.setBounds((int)pos.getX(), (int)pos.getY(), (int)size.getX(), (int)size.getY());
	}

	public void render(Vector2f offset)
	{
		Render.pushMatrix();
		Render.drawRectangle(pos.sub(offset), size, 0, 0, 0);
		if(isHover && enabled)
		{
			if(isDown) Render.drawRectangle(pos.sub(offset).add(borderSize), size.sub(borderSize * 2), buttonCol.darker().r, buttonCol.darker().g, buttonCol.darker().b);
			else Render.drawRectangle(pos.sub(offset).add(borderSize), size.sub(borderSize * 2), buttonCol.brighter().r, buttonCol.brighter().g, buttonCol.brighter().b);
		}
		else
		{
			if(!enabled) Render.drawRectangle(pos.sub(offset).add(borderSize), size.sub(borderSize * 2), 50, 50, 50);
			else Render.drawRectangle(pos.sub(offset).add(borderSize), size.sub(borderSize * 2), buttonCol.r, buttonCol.g, buttonCol.b);
		}
		if(font != null)
		{
			if(!enabled) Render.drawCenteredString(pos.sub(offset), size, txt, new Color(115, 115, 115), font, shadow);
			else Render.drawCenteredString(pos.sub(offset), size, txt, new Color(255, 255, 255), font, shadow);
		}
		Render.popMatrix();
	}

	private void nextChoice()
	{
		if(choice + 1 < choices) choice++;
		else choice = 0;
	}

	public int getChoice()
	{
		return choice;
	}

	public int getChoiceAmount()
	{
		return choices;
	}

	public Vector2f getPos()
	{
		return pos;
	}

	public String getText()
	{
		return txt;
	}

	public Vector2f getCenter(Vector2f size)
	{
		return pos.add(this.size.div(2)).sub(size.div(2));
	}

	public boolean isClicked()
	{
		return isHover && Input.getMouseDown(0);
	}

	public Vector2f getSize() 
	{
		return size;
	}

	public void setEnabled(boolean b)
	{
		this.enabled = b;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}

	public boolean isHover()
	{
		return isHover;
	}
}
