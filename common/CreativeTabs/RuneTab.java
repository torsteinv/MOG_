package CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import MISC.mod_MOG;

public class RuneTab extends CreativeTabs {

	public RuneTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(mod_MOG.RuneItems.get(001));
	}

}
