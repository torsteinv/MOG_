package Blocks;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Entities.TileEntityRuneFocus;
import MISC.mod_MOG;

public class BlockRuneFocus extends BlockContainer {

	public static HashMap<Integer, Integer> ItemAssosiations = null;

	/*
	 * Item id, Rune type 1 fire 2 earth 3 air 4 ice
	 */
	public static void define() {
		if (ItemAssosiations == null) {
			ItemAssosiations = new HashMap<Integer, Integer>();
			ItemAssosiations.put(Item.redstone.itemID, 1);
			ItemAssosiations.put(Item.bucketLava.itemID, 1);
			ItemAssosiations.put(Block.netherrack.blockID, 1);
			ItemAssosiations.put(Item.flintAndSteel.itemID, 1);
			ItemAssosiations.put(Item.dyePowder.itemID + 10000 * 4, 4);// lapiz
			ItemAssosiations.put(Item.bucketWater.itemID, 4);
			ItemAssosiations.put(Block.ice.blockID, 4);
			ItemAssosiations.put(Item.snowball.itemID, 4);
			ItemAssosiations.put(Item.feather.itemID, 3);
			ItemAssosiations.put(Item.bucketEmpty.itemID, 3);
			ItemAssosiations.put(Block.stone.blockID, 3);
			ItemAssosiations.put(Item.arrow.itemID, 3);
			ItemAssosiations.put(Item.seeds.itemID, 2);
			ItemAssosiations.put(Item.appleRed.itemID, 2);
			ItemAssosiations.put(Block.dirt.blockID, 2);
			ItemAssosiations.put(Block.grass.blockID, 2);
		}
	}

	public BlockRuneFocus(int par1) {
		super(par1, 2, Material.rock);
		this.setCreativeTab(mod_MOG.machinesTab);
		define();
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 == 1 || par1 == 0 ? 2 : 1;
	}

	@Override
	public String getTextureFile() {
		return "/MOG_resources/Blocks.png";
	}

	/*
	 * @Override public boolean onBlockActivated(World world, int x, int y, int
	 * z, EntityPlayer player, int i, float f, float g, float t) { ItemStack is
	 * = player.inventory.getCurrentItem(); int item = is.itemID ==
	 * Item.dyePowder.itemID ? is.itemID + 10000 is.getItemDamage() : is.itemID;
	 * define(); int aspect = ItemAssosiations.get(item) == null ? 5 :
	 * ItemAssosiations .get(item); if (aspect == 5) { return true; } Item it =
	 * mod_MOG.RuneItems.get(aspect); if (it == null) { return true; } ItemStack
	 * is3 = new ItemStack(it); player.inventory.addItemStackToInventory(is3);
	 * return true; }
	 */

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float f, float g, float t) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity == null || player.isSneaking()) {

			return false;

		}

		player.openGui(mod_MOG.instance, 0, world, x, y, z);

		return true;

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {

		dropItems(world, x, y, z);

		super.breakBlock(world, x, y, z, i, j);

	}

	private void dropItems(World world, int x, int y, int z) {

		Random rand = new Random();

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (!(tile_entity instanceof IInventory)) {

			return;

		}

		IInventory inventory = (IInventory) tile_entity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {

				float rx = rand.nextFloat() * 0.6F + 0.1F;

				float ry = rand.nextFloat() * 0.6F + 0.1F;

				float rz = rand.nextFloat() * 0.6F + 0.1F;
				ItemStack n = new ItemStack(item.itemID, item.stackSize,
						item.getItemDamage());

				if (item.hasTagCompound()) {

					n.setTagCompound((NBTTagCompound) item.getTagCompound()
							.copy());

				}

				EntityItem entity_item = new EntityItem(world, x + rx, y + ry,
						z + rz, n);

				float factor = 0.5F;

				entity_item.motionX = rand.nextGaussian() * factor;

				entity_item.motionY = rand.nextGaussian() * factor + 0.2F;

				entity_item.motionZ = rand.nextGaussian() * factor;

				world.spawnEntityInWorld(entity_item);

				item.stackSize = 0;

			}

		}

	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityRuneFocus();
	}
}
