package com.base.pixelthrive;

import java.awt.Rectangle;

import com.base.engine.Render;
import com.base.engine.Vector2f;

public class Tile extends Rectangle
{
	private static final long serialVersionUID = 1L;
	public static final float SCALE = 2.4f;
	public int id = -1;
	
	public Tile(Rectangle size, int id)
	{
		setBounds((int)(size.x * SCALE), (int)(size.y * SCALE), size.width, size.height);
		this.id = id;
	}

	public void render()
	{
		Render.pushMatrix();
		Render.setTextures(true);
		if(Block.textures[id] != null) Render.drawScaledTexturedRectangle(new Vector2f(getBounds().x, getBounds().y), new Vector2f(SCALE), Block.textures[id]);
		Render.setTextures(false);
		Render.popMatrix();
	}

	public Block getBlock()
	{
		return Block.blocks.get(id);
	}
}
