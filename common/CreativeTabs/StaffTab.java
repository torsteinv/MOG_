package CreativeTabs;
import MISC.mod_MOG;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StaffTab extends CreativeTabs {

	public StaffTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return mod_MOG.BasicStaff;
	}

}
