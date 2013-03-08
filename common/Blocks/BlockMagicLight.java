package Blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import MISC.mod_MOG;

public class BlockMagicLight extends Block {
	public BlockMagicLight(int par1, int par2) {
		super(par1, par2, Material.rock);
		this.setCreativeTab(mod_MOG.staffTab);
	}

	@Override
	public String getTextureFile() {
		return "/MOG_resources/Entities.png";
	}

	@Override
	public int idDropped(int i, Random r, int i2) {
		return mod_MOG.MagicpowderID;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void addCollidingBlockToList(World par1World, int par2, int par3,
			int par4, AxisAlignedBB par5AxisAlignedBB, List par6List,
			Entity par7Entity) {
		this.setBlockBounds(0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F);
		super.addCollidingBlockToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBoundsForItemRender();
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return mod_MOG.MagicLightRenderingID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

}
