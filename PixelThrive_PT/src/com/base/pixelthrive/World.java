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

	public World(MainMenu menu, Vector2f size)
	{
		this.menu = menu;
		worldW = size.getXInt();
		worldH = size.getYInt();
		tiles = new Tile[worldW][worldH];
		for(int x = 0; x < tiles.length; x++) for(int y = 0; y < tiles[0].length; y++) tiles[x][y] = new Tile(new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE), Block.air.blockID);
		generate();
	}

	protected void generate()
	{
		for(int x = 0; x < worldW; x++) for(int y = 10; y < worldH; y++) setTile(x, y, Block.dirt.blockID);
	}

	public Tile getTile(int x, int y)
	{
		return tiles[x][y];
	}

	public void setTile(int x, int y, int id)
	{
		tiles[x][y].id = id;
	}

	public void update(float delta)
	{
		for(int x = 0; x < worldW; x++) for(int y = 0; y < worldH; y++) tiles[x][y].getBlock().blockTick(this, x, y);
	}

	public void render()
	{
		Render.pushMatrix();
		for(int i = 0; i < Window.getHeight() / 10 + 1; i++) Render.drawRectangle(new Vector2f(0, i * 10), new Vector2f(Window.getWidth(), 10), new Color((i + 20) + 158, (i + 20) + 246, 255));
		for(int x = 0; x < worldW; x++) for(int y = 0; y < worldH; y++) tiles[x][y].render();
		Render.popMatrix();
	}
}