package CreativeTabs;
import MISC.mod_lawl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StaffTab extends CreativeTabs {

	public StaffTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return mod_lawl.BasicStaff;
	}

}
