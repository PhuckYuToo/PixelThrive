package com.base.pixelthrive;

import java.awt.Rectangle;

import com.base.engine.Render;
import com.base.engine.Vector2f;

public class Tile extends Rectangle
{
	private static final long serialVersionUID = 1L;
	public int id = -1;

	public Tile(Rectangle size, int id)
	{
		setBounds(size);
		this.id = id;
	}

	public void render()
	{
		Render.pushMatrix();
		Render.setTextures(true);
		if(getBlock().getTexture() != null) Render.drawTexturedRectangle(new Vector2f(getBounds().x, getBounds().y), getBlock().getTexture());
		Render.setTextures(false);
		Render.popMatrix();
	}

	public Block getBlock()
	{
		return Block.blocks.get(id);
	}
}
