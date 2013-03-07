package CreativeTabs;
import MISC.mod_MOG;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RuneTab extends CreativeTabs {

	public RuneTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(mod_MOG.BasicRune);
	}

}
