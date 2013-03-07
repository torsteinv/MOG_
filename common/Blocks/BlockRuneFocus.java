package Blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import MISC.mod_MOG;

public class BlockRuneFocus extends Block {

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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float f, float g, float t) {
		ItemStack is = player.inventory.getCurrentItem();
		int item = is.itemID == Item.dyePowder.itemID ? is.itemID + 10000
				* is.getItemDamage() : is.itemID;
		define();
		int aspect = ItemAssosiations.get(item) == null ? 5 : ItemAssosiations
				.get(item);
		if (aspect == 5) {
			return true;
		}
		Item it = mod_MOG.RuneItems.get(aspect);
		if (it == null) {
			return true;
		}
		ItemStack is3 = new ItemStack(it);
		player.inventory.addItemStackToInventory(is3);
		return true;
	}
}
