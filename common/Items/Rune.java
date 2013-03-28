package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import Entities.TileEntityMagicCauldron;
import MISC.mod_MOG;

public class Rune extends Item {

	public Rune(int par1, boolean b) {
		super(par1);
		this.setTextureFile(b ? "/MOG_resources/Items.png"
				: "/MOG_resources/Aspects.png");
		this.setCreativeTab(mod_MOG.runesTab);
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world,
			int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		int clicked = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		if (world.isRemote) {
			System.out.println("Registered 4");
			return false;
		}
		if (!item.getItemName().endsWith(".none.none")) {
			System.out.println("Registered 5");
			return true;
		}
		if (!(world.getBlockTileEntity(x, y, z) instanceof TileEntityMagicCauldron)) {
			System.out.println("Registered 6");
			return true;
		}
		TileEntityMagicCauldron te = (TileEntityMagicCauldron) world
				.getBlockTileEntity(x, y, z);
		System.out.println("Registered 3");

		System.out.println("Registered 3");
		String name = item.getItemName();
		if (clicked == mod_MOG.MagicCauldronID && metadata == 3) {

			int aspectIndex = name.contains("fire") ? 1 : name
					.contains("earth") ? 2 : name.contains("air") ? 3 : 4;
			te.write(aspectIndex);

			item.stackSize--;
			world.setBlock(x, y, z, mod_MOG.MagicCauldronID);
			System.out.println("Registered 2");
		}
		return true;
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
