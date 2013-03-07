package Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Magicpowder extends Item {

	public Magicpowder(int par1) {
		super(par1);
		maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public String getTextureFile() {
		return "/Tutorial/Items.png";
	}
}
