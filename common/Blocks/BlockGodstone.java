package Blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockGodstone extends Block{
	public BlockGodstone(int par1,int par2){
		super(par1,par2,Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile(){
		return "/Tutorial/Blocks.png";
	}
	
	
}
