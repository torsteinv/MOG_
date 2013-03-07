package MISC;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class TutorialFuel implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.itemID == mod_lawl.Magicpowder.itemID) {
			return 1000;
		}
		return 0;
	}

}
