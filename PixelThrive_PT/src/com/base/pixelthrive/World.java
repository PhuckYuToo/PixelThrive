package com.base.pixelthrive;

import java.awt.Rectangle;

import org.newdawn.slick.Color;

import com.base.engine.Render;
import com.base.engine.Vector2f;
import com.base.engine.Window;
import com.base.pixelthrive.guis.MainMenu;

public class World
{
	protected MainMenu menu;
	public final int worldW, worldH;

	protected Tile[][] tiles;

	protected Vector2f camera = new Vector2f(0);

	public boolean isDay = true;

	public World(MainMenu menu, Vector2f size)
	{
		this.menu = menu;
		worldW = size.getXInt();
		worldH = size.getYInt();
		tiles = new Tile[worldW][worldH];
		for(int x = 0; x < tiles.length; x++) for(int y = 0; y < tiles[0].length; y++) tiles[x][y] = new Tile(new Rectangle(x * Block.SCALED_SIZE, y * Block.SCALED_SIZE, Block.SCALED_SIZE, Block.SCALED_SIZE), Block.air.blockID);
		generate();
	}

	protected void generate()
	{
		for(int x = 0; x < worldW; x++) for(int y = 10; y < worldH; y++) setTile(x, y, Block.dirt.blockID);
	}

	public Tile getTile(int x, int y)
	{
		try
		{
			return tiles[x][y];
		}
		catch(Exception e){return null;}
	}

	public void setTile(int x, int y, int id)
	{
		tiles[x][y].id = id;
	}

	public Block getBlock(int x, int y)
	{
		return getTile(x, y).getBlock();
	}

	public void setBlock(int x, int y, int id)
	{
		if(!insideWorld(x, y)) return;
		tiles[x][y].id = id;
	}

	public boolean insideWorld(int x, int y)
	{
		return x >= 0 && y >= 0 && x < worldW && y < worldH;
	}

	public boolean canBlockSeeSky(int x, int y)
	{
		int block = 0;
		for(int m = y - 1; m > 1; m--)
		{
			if(getBlock(x, m) == Block.air) block++;
		}
		if(block >= y - 2) return true;
		return false;
	}

	public void update(float delta)
	{
		for(int x = camera.getXInt(); x < Block.SCREEN_BLOCK.getX() + camera.getX() - 1; x++) for(int y = camera.getYInt(); y < Block.SCREEN_BLOCK.getY() + camera.getY() - 1; y++) 
		{
			try
			{
				tiles[x][y].getBlock().blockTick(this, x, y);
			}
			catch(Exception e){}
		}
	}

	public void render()
	{
		Render.pushMatrix();
		for(int i = 0; i < Window.getHeight() / 10 + 1; i++) Render.drawRectangle(new Vector2f(0, i * 10), new Vector2f(Window.getWidth(), 10), new Color((i + 20) + 158, (i + 20) + 246, 255));
		for(int x = 0; x < worldW; x++) for(int y = 0; y < worldH; y++) tiles[x][y].render();
		Render.popMatrix();
	}
}