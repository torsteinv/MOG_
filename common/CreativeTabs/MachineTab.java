package CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import MISC.mod_MOG;

public class MachineTab extends CreativeTabs {

	public MachineTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(mod_MOG.RuneFocus);
	}

}
