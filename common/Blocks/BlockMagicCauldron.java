package Blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import MISC.TileEntityMagicCauldron;
import MISC.mod_MOG;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicCauldron extends BlockContainer {
	public BlockMagicCauldron(int par1) {
		super(par1, Material.iron);
		this.blockIndexInTexture = 154;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 == 1 ? 138 : (par1 == 0 ? 155 : 154);
	}

	/**
	 * if the specified block is in the given AABB, add its collision bounding
	 * box to the given list
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void addCollidingBlockToList(World par1World, int par2, int par3,
			int par4, AxisAlignedBB par5AxisAlignedBB, List par6List,
			Entity par7Entity) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		float var8 = 0.125F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, var8, 1.0F, 1.0F);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var8);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBounds(1.0F - var8, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBounds(0.0F, 0.0F, 1.0F - var8, 1.0F, 1.0F, 1.0F);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBoundsForItemRender();
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return mod_MOG.MagicCauldronRenderingID;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		} else {
			ItemStack var10 = par5EntityPlayer.inventory.getCurrentItem();

			if (var10 == null) {
				return true;
			} else {
				int var11 = par1World.getBlockMetadata(par2, par3, par4);

				if (var10.itemID == Item.bucketWater.itemID) {
					if (var11 < 3) {
						if (!par5EntityPlayer.capabilities.isCreativeMode) {
							par5EntityPlayer.inventory
									.setInventorySlotContents(
											par5EntityPlayer.inventory.currentItem,
											new ItemStack(Item.bucketEmpty));
						}

						par1World.setBlockMetadata(par2, par3, par4, 3);
					}

					return true;
				} else {
					if (var10.itemID == Item.glassBottle.itemID) {
						if (var11 > 0) {
							ItemStack var12 = new ItemStack(Item.potion, 1, 0);

							if (!par5EntityPlayer.inventory
									.addItemStackToInventory(var12)) {
								par1World.spawnEntityInWorld(new EntityItem(
										par1World, par2 + 0.5D, par3 + 1.5D,
										par4 + 0.5D, var12));
							} else if (par5EntityPlayer instanceof EntityPlayerMP) {
								((EntityPlayerMP) par5EntityPlayer)
										.sendContainerToPlayer(par5EntityPlayer.inventoryContainer);
							}

							--var10.stackSize;

							if (var10.stackSize <= 0) {
								par5EntityPlayer.inventory
										.setInventorySlotContents(
												par5EntityPlayer.inventory.currentItem,
												(ItemStack) null);
							}

							par1World.setBlockMetadataWithNotify(par2, par3,
									par4, var11 - 1);
						}
					}

					return true;
				}
			}
		}
	}

	// Returns the ID of the items to drop on destruction.

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.cauldron.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return Item.cauldron.itemID;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		try {
			return new TileEntityMagicCauldron();
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}
}
