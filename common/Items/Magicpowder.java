package Items;

import net.minecraft.item.Item;
import MISC.mod_MOG;

public class Magicpowder extends Item {

	public Magicpowder(int par1) {
		super(par1);
		maxStackSize = 64;
		this.setCreativeTab(mod_MOG.staffTab);
	}

	@Override
	public String getTextureFile() {
		return "/MOG_resources/Items.png";
	}
}
