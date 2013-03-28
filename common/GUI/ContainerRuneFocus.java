package GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import Blocks.BlockRuneFocus;
import Entities.TileEntityRuneFocus;
import MISC.mod_MOG;

public class ContainerRuneFocus extends Container {

	protected TileEntityRuneFocus tileEntity;

	public ContainerRuneFocus(TileEntityRuneFocus tile,
			InventoryPlayer player_inventory) {

		tileEntity = tile;

		addSlotToContainer(new SlotRuneFoucs(tile, 0, 56, 17,
				SlotRuneFoucs.Type.Rune));
		addSlotToContainer(new SlotRuneFoucs(tile, 1, 56, 53,
				SlotRuneFoucs.Type.Item));
		addSlotToContainer(new SlotRuneFoucs(tile, 2, 116, 35,
				SlotRuneFoucs.Type.Result));
		bindPlayerInventory(player_inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {

		return tileEntity.isUseableByPlayer(player);

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if (var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 == 2) {
				if (!this.mergeItemStack(var5, 3, 39, true)) {
					return null;
				}

				var4.onSlotChange(var5, var3);
			} else if (par2 != 1 && par2 != 0) {
				if (BlockRuneFocus.ItemAssosiations.containsKey(var5
						.getItemDamage() != 0 ? var5.itemID
						+ var5.getItemDamage() * 10000 : var5.itemID)) {
					if (!this.mergeItemStack(var5, 1, 2, false)) {
						return null;
					}
				} else if (var5.itemID == mod_MOG.RuneID + 256) {
					if (!this.mergeItemStack(var5, 0, 1, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(var5, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39
						&& !this.mergeItemStack(var5, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(var5, 3, 39, false)) {
				return null;
			}

			if (var5.stackSize == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}

			if (var5.stackSize == var3.stackSize) {
				return null;
			}

			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}

		return var3;
	}

	protected void bindPlayerInventory(InventoryPlayer player_inventory) {

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 9; j++) {

				addSlotToContainer(new Slot(player_inventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));

			}

		}

		for (int i = 0; i < 9; i++) {

			addSlotToContainer(new Slot(player_inventory, i, 8 + i * 18, 142));

		}

	}

}
