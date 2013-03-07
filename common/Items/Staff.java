package Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import MISC.TileEntityMagicCauldron;
import MISC.mod_MOG;

public class Staff extends Item {

	public Staff(int par1) {
		super(par1);
		this.setCreativeTab(mod_MOG.staffTab);
	}

	@Override
	public String getTextureFile() {
		return "/MOG_resources/Items.png";
	}

	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		int clicked = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);

		if (clicked == Block.cauldron.blockID) {
			world.setBlockAndMetadataWithNotify(x, y, z,
					mod_MOG.MagicCauldronID, metadata);
		} else if (clicked == mod_MOG.MagicCauldronID) {
			int[] el = ((TileEntityMagicCauldron) world.getBlockTileEntity(x,
					y, z)).el;

			if (el[0] == 0) {
				return true;
			}
			Item i = mod_MOG.RuneItems.get(el[0] + el[1] * 10 + el[2] * 100);
			ItemStack is = new ItemStack(i == null ? mod_MOG.BasicRune : i);
			((TileEntityMagicCauldron) world.getBlockTileEntity(x, y, z))
					.clear();
			player.inventory.addItemStackToInventory(is);

			world.notifyBlockChange(x, y, z, mod_MOG.MagicCauldronID);

		}
		return true;
	}

}
