package GUI;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import Blocks.BlockRuneFocus;
import MISC.mod_MOG;

public class SlotRuneFoucs extends Slot {

	public Type t = Type.Result;

	public enum Type {
		Rune, Item, Result;
	}

	public SlotRuneFoucs(IInventory par1iInventory, int par2, int par3,
			int par4, Type Type) {
		super(par1iInventory, par2, par3, par4);
		t = Type;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		int var1 = t == Type.Rune ? mod_MOG.RuneID + 256 : t == Type.Item ? -2
				: -1;
		return var1 == -2 ? BlockRuneFocus.ItemAssosiations
				.containsKey(par1ItemStack.getItemDamage() != 0 ? par1ItemStack.itemID
						+ 10000 * par1ItemStack.getItemDamage()
						: par1ItemStack.itemID)
				: par1ItemStack.itemID == var1;
	}
}
