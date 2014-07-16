package com.base.pixelthrive;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.base.engine.CoreEngine;
import com.base.engine.Render;
import com.base.engine.Texture;
import com.base.engine.Vector2f;

public class Block
{
	public static final int SIZE = 16;

	public static int[] IDs = new int[4096];
	public int blockID;

	protected Texture texture;
	protected String name;

	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static final Block air = new Block(0).setName("air");//.setUnbreakable().setTransparent(true).setFunctions(null).setMaterial(BlockMaterial.AIR);
	public static final Block dirt = new GrassBlock(1, 1, false).setName("dirt");//.setResistance(20).setMaterial(BlockMaterial.GROUND).setTool(Item.shovel, 0).setQuantityDropped(1).setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Don't touch, it's dirty.");
	//	public static final Block grass = new GrassBlock(2, 0, true).setWalkingSound("grass").setTurnable(false).setResistance(40).setMaterial(BlockMaterial.GROUND).setTool(Item.shovel, 0).setDrop(dirt.blockID).setQuantityDropped(1).setName("grass").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Creepy crawlers...");	
	//	public static final Block stone = new Block(3, 2).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("stone").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Pretty hard to mine without tools..");
	//	public static final Block bedrock = new Block(4, 3).setUnbreakable().setMaterial(BlockMaterial.STONE).setName("bedrock").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Mine this. Challenge accepted?");
	//	public static final Block basalt = new Block(5, 4).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("basalt").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("No, not bathsalts.");
	//	public static final Block lavaStone = new Block(6, 5).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("lavastone").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Is it hot here or is it me?");
	//	public static final Block obsidian = new Block(7, 6).setResistance(500).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 3).setName("obsidian").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("So purple :>");
	//	public static final Block mossyStone = new Block(8, 7).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("mossy Stone").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Overgrown rock. Cool eh?");
	//	public static final Block glass = new Block(9, 8).setResistance(10).setMaterial(BlockMaterial.GLASS).setName("glass").setTransparent(true).setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("See-through o_o");
	//	public static final Block zenGlass = new Block(10, 9).setResistance(10).setMaterial(BlockMaterial.GLASS).setTransparent(true).setName("zen Glass").setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("Straight from the far east.");
	//	public static final Block stoneBricks = new Block(11, 10).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("stone Bricks").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Cheap to make, looks rich. ;D");
	//	public static final Block ironFence = new Block(12, 11).setResistance(300).setMaterial(BlockMaterial.METAL).setTransparent(true).setTool(Item.pickaxe, 2).setName("iron Fence").setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("Tresspassers will be shot twice.");
	//	public static final Block woodPlanks = new Block(13, 12).setResistance(25).setMaterial(BlockMaterial.WOOD).setTool(Item.axe, 0).setQuantityDropped(1).setName("planks").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Majestic planks.");
	//	public static final Block log = new Block(14, 13).setResistance(150).setMaterial(BlockMaterial.WOOD).setTool(Item.axe, 0).setQuantityDropped(1).setName("log").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Bark at the moon");
	//	public static final Block leaves = new LeafBlock(15, 14).setWalkingSound("grass").setResistance(10).setTool(Item.shears, 0).setHasRandomDrop(true).setDrops(new CraftableStack[]{new CraftableStack(Item.leaf, 3), new CraftableStack(Item.branch, 2)}).setMaterial(BlockMaterial.PLANTS).setName("leaves").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Leaves 'n shit");
	//	public static final Block sand = new GravityBlock(16, 16).setResistance(19).setMaterial(BlockMaterial.GROUND).setTool(Item.shovel, 0).setQuantityDropped(1).setName("sand").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Keep digging and you'll find pyramids");
	//	public static final Block grit = new GravityBlock(17, 18).setResistance(24).setMaterial(BlockMaterial.GROUND).setTool(Item.shovel, 0).setQuantityDropped(1).setName("grit").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Gotta love grit");
	//	public static final Block clay = new Block(18, 17).setResistance(25).setDrop(Item.clay.itemID).setQuantityDropped(6).setTool(Item.shovel, 0).setMaterial(BlockMaterial.GROUND).setName("clay").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Not for human consumption");
	//	public static final Block sandStone = new Block(19, 19).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 0).setName("sand Stone").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("HARD SAND O_O");
	//	public static final Block water = new LiquidBlock(20, 240, 0).setTurnable(false).setSemiBlock(true).setUnbreakable().setMaterial(BlockMaterial.WATER).setRenderInWorld(false).setName("water").setCreativeTab(CreativeTabs.LIQUIDS).setHelpDescription("Don't feed the sharks");
	//	public static final Block chest = new ChestBlock(21).setTurnable(false).setSemiBlock(true).setMaterial(BlockMaterial.WOOD).setName("wooden Chest").setQuantityDropped(1).setTool(Item.axe, 0).setResistance(55).setRenderInWorld(false).setDifferentDroppedRender().setCreativeTab(CreativeTabs.STORAGE).setHelpDescription("Open me :D");
	//	public static final Block bricks = new Block(22, 22).setMaterial(BlockMaterial.STONE).setName("bricks").setQuantityDropped(1).setTool(Item.pickaxe, 1).setResistance(300).setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("Let's build the ghetto :3");
	//	public static final Block furnace = new TileEntityBlock(23, Furnace.class).setCropInHand(false).setSemiBlock(true).setTexture(24).setMaterial(BlockMaterial.STONE).setName("furnace").setQuantityDropped(1).setTool(Item.pickaxe, 0).setResistance(300).setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("Burn baby! BURN!");
	//	public static final Block craftingBench = new TileEntityBlock(24, CraftingBench.class).setCropInHand(false).setSemiBlock(true).setTexture(23).setMaterial(BlockMaterial.WOOD).setName("crafting Bench").setQuantityDropped(1).setTool(Item.axe, 0).setResistance(100).setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("I can create... PLANKS! :3");
	//	public static final Block marble = new Block(25).setMaterial(BlockMaterial.STONE).setName("marble").setTexture(25).setTool(Item.pickaxe, 1).setResistance(250).setHelpDescription("SHINY! :O").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block jade = new Block(26).setMaterial(BlockMaterial.STONE).setName("jade").setTexture(26).setTool(Item.pickaxe, 1).setResistance(240).setHelpDescription("Smells like mint.").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block dacite = new Block(27).setMaterial(BlockMaterial.STONE).setName("dacite").setTexture(27).setTool(Item.pickaxe, 1).setResistance(275).setHelpDescription("Not advised to stare for too long.").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block mudstone = new Block(28).setMaterial(BlockMaterial.STONE).setName("mudstone").setTexture(28).setTool(Item.pickaxe, 0).setResistance(300).setHelpDescription("You should Play-Do").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block swampLog = new Block(29).setMaterial(BlockMaterial.WOOD).setQuantityDropped(1).setName("swamp Log").setTexture(29).setTool(Item.axe, 0).setResistance(55).setHelpDescription("Looks cursed.").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block swampPlanks = new Block(30).setMaterial(BlockMaterial.WOOD).setQuantityDropped(1).setName("swamp Planks").setTexture(30).setTool(Item.axe, 0).setResistance(30).setHelpDescription("Looks haunted.").setCreativeTab(CreativeTabs.BUILDING);
	//	/**/ public static final Block tnt = new ExplosiveBlock(31, 3F).setMaterial(BlockMaterial.GROUND).setQuantityDropped(1).setName("tnt").setTexture(31).setTool(Item.axe, 0).setResistance(20).setHelpDescription("Don't cut the wrong wire").setCreativeTab(CreativeTabs.EXPLOSIVE);
	//	public static final Block goldBricks = new Block(32).setMaterial(BlockMaterial.STONE).setName("gold Bricks").setTexture(32).setTool(Item.pickaxe, 2).setResistance(500).setHelpDescription("Only for rich people $_$").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block jadeBricks = new Block(33).setMaterial(BlockMaterial.STONE).setName("jade Bricks").setTexture(33).setTool(Item.pickaxe, 2).setResistance(500).setHelpDescription("Jade walls FTW!").setCreativeTab(CreativeTabs.BUILDING);
	//	/**/ public static final Block sunRelic = new Block(34).setMaterial(BlockMaterial.STONE).setName("sun Relic").setTexture(34).setTool(Item.pickaxe, 3).setResistance(500).setHelpDescription("CLEAN UP THE GHETTO!").setCreativeTab(CreativeTabs.BUILDING);
	//	/**/ public static final Block moonRelic = new Block(35).setMaterial(BlockMaterial.STONE).setName("moon Relic").setTexture(35).setTool(Item.pickaxe, 3).setResistance(500).setHelpDescription("BIG DICK MYSTIC!").setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block barrier = new Block(36).setMiddleMouse(false).setTurnable(false).setMaterial(BlockMaterial.VOID).setName("barrier").setTool(Item.pickaxe, 100).setResistance(1).setRenderInWorld(false).setTexture(0).setHelpDescription("Invisibru");
	//	public static final Block woodenDoor = new TileEntityBlock(37, net.PixelThrive.Client.entities.tileentities.Door.class).setCropInHand(false).setMaterial(BlockMaterial.WOOD).setName("wooden Door").setTool(Item.axe, 0).setTexture(37).setCreativeTab(CreativeTabs.BUILDING);
	//	public static final Block mysticWorktable = new TileEntityBlock(38, MysticWorktable.class).setCropInHand(false).setTexture(36).setMaterial(BlockMaterial.WOOD).setName("mystic Worktable").setQuantityDropped(1).setTool(Item.axe, 0).setResistance(200).setCreativeTab(CreativeTabs.DECORATION).setHelpDescription("Black magic!");
	//	/**/ public static final Block ice = new Block(39).setMaterial(BlockMaterial.GLASS).setName("ice").setTool(Item.pickaxe, 0).setResistance(80).setHelpDescription("Caution! Slippery!").setCreativeTab(CreativeTabs.BUILDING).setTexture(38).setQuantityDropped(1);
	//	public static final Block snow = new GravityBlock(40).setMaterial(BlockMaterial.GROUND).setName("snow").setTool(Item.shovel, 0).setResistance(60).setHelpDescription("Better than clay.").setCreativeTab(CreativeTabs.BUILDING).setTexture(39).setQuantityDropped(1);
	//	public static final Block permaFrost = new Block(41).setMaterial(BlockMaterial.GLASS).setName("permafrost").setTool(Item.pickaxe, 3).setResistance(400).setHelpDescription("Hard to break.").setCreativeTab(CreativeTabs.BUILDING).setTexture(40).setQuantityDropped(1);
	//	public static final Block woodenTorch = new TorchBlock(42).setCropInHand(false).setSemiBlock(true).setTurnable(false).setCollidable(false).setMaterial(BlockMaterial.WOOD).setName("wooden Torch").setTool(Item.axe, 0).setResistance(20).setHelpDescription("Darkness Preventinator 2000 v0.1").setCreativeTab(CreativeTabs.DECORATION).setQuantityDropped(1).setTexture(41);
	//	public static final Block ironOre = new OreBlock(43, 50, 3).setTexture(42).setResistance(200).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 1).setName("iron Ore").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("It's shiny, so it must be valuable");
	//	public static final Block goldOre = new OreBlock(44, 200, 2).setTexture(43).setResistance(400).setMaterial(BlockMaterial.STONE).setTool(Item.pickaxe, 2).setName("gold Ore").setCreativeTab(CreativeTabs.BUILDING).setHelpDescription("This is true bling.");
	//	public static final Block cobweb = new CobwebBlock(45).setTransparent(true).setCollidable(false).setSemiBlock(true).setName("cobweb").setTool(Item.sword, 0).setResistance(100).setHelpDescription("Sticky situation.").setCreativeTab(CreativeTabs.BUILDING).setTexture(44).setDrops(new CraftableStack[]{new CraftableStack(Item.string, 2)});

	public Block(int id)
	{
		if(id < IDs.length)
		{
			blockID = id;
			blocks.add(this);
		}
		else new Exception("Ran out of Block IDs!").printStackTrace();
	}

	public Block(int id, int texture)
	{
		this(id);
		setTexture(texture);
	}

	public Block(int id, int texture, SpriteSheet s)
	{
		this(id);
		setTexture(texture, s);
	}

	public void blockTick(World world, int x, int y)
	{
	}

	public void renderOverlay(World world, int x, int y)
	{
	}

	public Block setTexture(int id)
	{
		int y = id / SIZE;
		this.texture = new Texture(SpriteSheet.Terrain, new Vector2f(id - SIZE * y, y));
		return this;
	}

	public Block setTexture(int texture, SpriteSheet s)
	{
		int y = texture / SIZE;
		this.texture = new Texture(s, new Vector2f(texture - SIZE * y, y));
		return this;
	}

	public Texture getTexture()
	{
		return texture;
	}
	
	public Block setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public String getName()
	{
		return name;
	}

	public int[] getSheetCoordinates()
	{
		return new int[]{getTexture().x, getTexture().y};
	}

	public static class GrassBlock extends Block
	{
		protected boolean canSpread;
		Random rand = new Random();
		private BufferedImage img = SpriteSheet.Terrain.getImage();
		public boolean[][] grassLeft, grassRight;

		public GrassBlock(int id)
		{
			super(id);
		}

		public GrassBlock(int id, int texture, boolean canSpread)
		{
			super(id, texture);
			this.canSpread = canSpread;
			//			if(canSpread) img = img.getSubimage(getSheetCoordinates()[0], getSheetCoordinates()[1], Tile.tileSize, 4);
		}

		public void renderOverlay(int x, int y)
		{
			try
			{
				if(canSpread)
				{
					//					if(grassLeft[x][y]) Render.drawImage(Render.rotate(img, -90), (x * Tile.tileSize) - (int)Main.sX, (y * Tile.tileSize) - (int)Main.sY);
					//					if(grassRight[x][y]) Render.drawImage(Render.rotate(img, 90), (x * Tile.tileSize) + 12 - (int)Main.sX, (y * Tile.tileSize) - (int)Main.sY);
					//					Render.drawImage(texture.getImageIcon().getSubimage(0, 0, Tile.tileSize, 1), (x * Tile.tileSize) - (int)Main.sX, (y * Tile.tileSize) - (int)Main.sY);
				}
			}
			catch(Exception e){}
		}

		public void blockTick(World world, int x, int y)
		{	
			if(grassLeft == null) grassLeft = new boolean[world.worldW][world.worldH];
			if(grassRight == null) grassRight = new boolean[world.worldW][world.worldH];
			if(x >= 0 && y >= 0 && x < world.worldW && y < world.worldH)
			{
				//				if(world.getBlock(x, y) == Block.dirt && world.canBlockSeeSky(x, y) && rand.nextInt(700) == 0 && world.isDay) world.setBlock(x, y, Block.grass.blockID);
				//				for(int i = -1; i <= 1; i++)
				//					for(int j = -1; j <= 1; j++)
				//						if(world.getBlock(x, y) == Block.dirt && world.getBlock(x, y - 1) == Block.air && world.getBlock(x + i, y + j) == Block.grass && rand.nextInt(700) == 0 && world.isDay)
				//							world.setBlock(x, y, Block.grass.blockID);
				//				for(int i = -1; i <= 1; i++)
				//					for(int j = -1; j <= 1; j++)
				//						if(world.getBlock(x, y) == Block.grass && world.getBlock(x, y - 1) != Block.air && world.getBlock(x + i, y + j) != Block.grass && rand.nextInt(700) == 0)
				//							world.setBlock(x, y, Block.dirt.blockID);
				//				try
				//				{
				//					if(world.getBlock(x, y) == Block.grass)
				//					{
				//						if(world.getBlock(x - 1, y + 1) == Block.grass) grassLeft[x][y] = true;
				//						else grassLeft[x][y] = false;
				//						if(world.getBlock(x + 1, y + 1) == Block.grass) grassRight[x][y] = true;
				//						else grassRight[x][y] = false;
				//					}
				//				}
				//				catch(Exception e){}
			}
		}
	}
}
