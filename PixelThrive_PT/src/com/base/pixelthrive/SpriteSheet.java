package com.base.pixelthrive;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.base.engine.Vector2f;

public class SpriteSheet
{
	public static final SpriteSheet Terrain = new SpriteSheet("Terrain.png");
	public static final SpriteSheet Entity = new SpriteSheet("Entity.png");
	public static final SpriteSheet TileEntity = new SpriteSheet("TileEntity.png");
	public static final SpriteSheet Item = new SpriteSheet("Item.png");
	public static final SpriteSheet Buff = new SpriteSheet("Buff.png");
	public static final SpriteSheet Particle = new SpriteSheet("Particle.png");
	public static final SpriteSheet GUI = new SpriteSheet("Gui.png");
	public static final SpriteSheet Skill = new SpriteSheet("Skills.png");
	
	private Image img;
	private String path;
	
	public SpriteSheet(String path)
	{
		try
		{
			img = new Image(path);
		}
		catch(SlickException e)
		{
			
		}
		this.path = path;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	public Image getSprite(int x, int y, Vector2f size)
	{
		return img.getSubImage(x, y, size.getXInt(), size.getYInt());
	}
}
