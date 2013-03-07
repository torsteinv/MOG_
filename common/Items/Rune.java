package Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import MISC.TileEntityMagicCauldron;
import MISC.mod_MOG;

public class Rune extends Item {

	public Rune(int par1, boolean b) {
		super(par1);
		this.setTextureFile(b ? "/MOG_resources/Items.png"
				: "/MOG_resources/Aspects.png");
		this.setCreativeTab(mod_MOG.runesTab);
	}

	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!item.getItemName().endsWith(".none.none")) {
			return true;
		}
		TileEntityMagicCauldron te = (TileEntityMagicCauldron) world
				.getBlockTileEntity(x, y, z);
		int clicked = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		String name = item.getItemName();
		if (clicked == Block.cauldron.blockID && metadata == 3) {
			world.setBlockAndMetadataWithNotify(x, y, z,
					mod_MOG.MagicCauldronID, metadata);

			return onItemUseFirst(item, player, world, x, y, z, side, hitX,
					hitY, hitZ);

		} else if (clicked == mod_MOG.MagicCauldronID && metadata == 3) {

			int aspectIndex = name.contains("fire") ? 1 : name
					.contains("earth") ? 2 : name.contains("air") ? 3 : 4;
			te.write(aspectIndex);

			item.stackSize--;
			world.notifyBlockChange(x, y, z, mod_MOG.MagicCauldronID);
		}
		return false;
	}

	@Override
	public String getItemDisplayName(ItemStack is) {
		String name = is.getItemName().replace("item.", "");
		String afterKeyset = mod_MOG.RuneKeys.get(name);
		return afterKeyset;
	}

	public static Item create(int i) {
		Rune c = (Rune) new Rune(mod_MOG.RuneID, false).setItemName("rune")
				.setIconIndex(i);
		c.setTextureFile("/Tutorial/Items.png");
		return c;
	}

	public static String nameByItemIndex(int ind) {
		switch (ind) {
		case 0:
			return "none";
		case 1:
			return "fire";
		case 2:
			return "earth";
		case 3:
			return "air";
		case 4:
			return "ice";
		default:
			return "none";
		}
	}
}
