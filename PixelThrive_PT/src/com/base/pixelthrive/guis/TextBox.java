package com.base.pixelthrive.guis;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.base.engine.Font;
import com.base.engine.Render;
import com.base.engine.Vector2f;

public class TextBox
{
	private String txt;
	private Vector2f pos = new Vector2f(0);
	private Font font;

	private int lines = 1;
	private int limit = 200;

	private String[] words;
	private Vector2f[] wordPos;

	public TextBox(String txt, float fontSize)
	{
		this.txt = txt;
		this.font = Render.getFont(fontSize);
		int lineWidth = Render.getStringSize(txt, font).getXInt();
		while(lineWidth > limit)
		{
			lineWidth /= 2;
			lines++;
		}
		words = txt.split(" ");
		wordPos = new Vector2f[words.length];
	}

	public TextBox setPos(Vector2f pos)
	{
		this.pos = pos;
		return this;
	}

	public void input(float delta)
	{

	}

	public void update(float delta)
	{

	}

	public void render()
	{
		Render.pushMatrix();
		ArrayList<Float> lineEnds = new ArrayList<Float>();
		int line = 0;
		for(int l = 0; l < words.length; l++)
		{
			if(l == 0) wordPos[l] = new Vector2f(6);
			else
			{
				float length = wordPos[l - 1].getX() + Render.getStringSize(words[l - 1], font).getX() + Render.getStringSize(words[l], font).getX() + 4;
				float resX = wordPos[l - 1].getX() + Render.getStringSize(words[l - 1], font).getX();
				if(length >= limit)
				{
					lineEnds.add(resX + 4);
					line++;
					lines = line + 1;
					resX = 2;
				}
				wordPos[l] = new Vector2f(resX + 4, ((line * Render.getStringSize(words[l - 1], font).getY()) + 6));
			}
		}
		
		float resLength = 0;
		for(int i = 0; i < lineEnds.size(); i++)
		{
			if(i == 0) resLength = lineEnds.get(i);
			else resLength = Math.max(resLength, lineEnds.get(i));
		}

		Render.drawRectangle(pos, new Vector2f(resLength + 2, (Render.getStringSize(txt, font).getY() + 2) * lines), new Color(0x000000));
		Render.drawRectangle(pos.add(2), new Vector2f(resLength + 2, (Render.getStringSize(txt, font).getY() + 2) * lines).sub(4), new Color(100, 100, 100));

		for(int l = 0; l < words.length; l++) Render.drawString(wordPos[l].add(pos), words[l], new Color(0xffffff), font, new Vector2f(2));

		Render.popMatrix();
	}
}
