package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import MISC.mod_MOG;

public abstract class PowerItem extends Item {

	public PowerItem(int par1) {
		super(par1);
	}

	public boolean hasItem(int type, EntityPlayer player, int amount) {
		return player.inventory.hasItemStack(new ItemStack(type, amount, 0))
				|| player.capabilities.isCreativeMode;
	}

	public boolean hasPowder(EntityPlayer player, int amount) {
		return hasItem(mod_MOG.MagicpowderID, player, amount)
				|| player.capabilities.isCreativeMode;
	}

	public boolean takeItem(int type, EntityPlayer player, int amount) {
		for (int i = 0; i < amount; i++) {
			if (!takeItemSingle(type, player)) {
				return false;
			}
		}
		return true;
	}

	public boolean takeItemSingle(int type, EntityPlayer player) {
		if (player.capabilities.isCreativeMode) {
			return true;
		}
		return player.inventory.consumeInventoryItem(type);
	}

	public int takeMultiplePowder(int amount, EntityPlayer p) {
		for (int i = 0; i < amount; i++) {
			if (!takePowder(p)) {
				return amount - i;
			}
		}
		return -1;
	}

	public boolean takePowder(EntityPlayer p) {
		if (p.capabilities.isCreativeMode) {
			return true;
		}
		return p.inventory.consumeInventoryItem(mod_MOG.MagicpowderID + 256);
	}

}
