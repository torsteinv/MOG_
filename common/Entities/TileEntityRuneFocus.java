package Entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import Blocks.BlockRuneFocus;
import MISC.mod_MOG;

public class TileEntityRuneFocus extends TileEntity implements IInventory {

	public static final int totalTime = 100;

	public int timeInfuse = 0; // +1 every tick, maximizes at totalTime(100)

	public final ItemStack[] inventory;

	public TileEntityRuneFocus() {

		this.inventory = new ItemStack[27];

	}

	@Override
	public int getSizeInventory() {

		return this.inventory.length;

	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {

		return this.inventory[slotIndex];

	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		this.inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {

			stack.stackSize = getInventoryStackLimit();

		}

	}

	@Override
	public void updateEntity() {
		boolean shouldBurn = inventory[0] != null && inventory[1] != null
				&& canInfuse();

		if (shouldBurn) {
			timeInfuse++;
		}

		if (!this.worldObj.isRemote) {

			if (inventory[0] == null || inventory[1] == null) {
				timeInfuse = 0;
			}

			if (timeInfuse >= totalTime && canInfuse()) {

				if (inventory[1] != null && inventory[0] != null) {
					timeInfuse = 0;
					--inventory[1].stackSize;
					--inventory[0].stackSize;
					inventory[2] = new ItemStack(
							mod_MOG.RuneItems
									.get(BlockRuneFocus.ItemAssosiations
											.get(inventory[1].itemID
													+ inventory[1]
															.getItemDamage()
													* 10000)));
					if (inventory[1].stackSize == 0) {
						inventory[1] = null;
					}
					if (inventory[0].stackSize == 0) {
						inventory[0] = null;
					}

					this.onInventoryChanged();
				}
			}
		}
	}

	private boolean canInfuse() {
		if (this.inventory[1] == null) {
			return false;
		} else {
			ItemStack var1 = new ItemStack(
					mod_MOG.RuneItems.get(BlockRuneFocus.ItemAssosiations
							.get(inventory[1].itemID
									+ inventory[1].getItemDamage() * 10000)));
			if (inventory[2] == null) {
				return true;
			}
			if (!inventory[2].isItemEqual(var1)) {
				return false;
			}
			int result = inventory[2].stackSize + var1.stackSize;
			return (result <= getInventoryStackLimit() && result <= var1
					.getMaxStackSize());
		}
	}

	public int getStartInventorySide(ForgeDirection side) {
		if (side == ForgeDirection.DOWN) {
			return 1;
		}
		if (side == ForgeDirection.UP) {
			return 0;
		}
		return 2;
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {

		ItemStack stack = getStackInSlot(slotIndex);

		if (stack != null) {

			if (stack.stackSize <= amount) {

				setInventorySlotContents(slotIndex, null);

			}

			else {

				stack = stack.splitStack(amount);

				if (stack.stackSize == 0) {

					setInventorySlotContents(slotIndex, null);

				}

			}

		}

		return stack;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {

		ItemStack stack = getStackInSlot(slotIndex);

		if (stack != null) {

			setInventorySlotContents(slotIndex, null);

		}

		return stack;

	}

	@Override
	public int getInventoryStackLimit() {

		return 64;

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {

		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;

	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {

		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");

		for (int i = 0; i < tagList.tagCount(); i++) {

			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

			byte slot = tag.getByte("Slot");

			if (slot >= 0 && slot < inventory.length) {

				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);

			}

		}

	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {

		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < inventory.length; i++) {

			ItemStack stack = inventory[i];

			if (stack != null) {

				NBTTagCompound tag = new NBTTagCompound();

				tag.setByte("Slot", (byte) i);

				stack.writeToNBT(tag);

				itemList.appendTag(tag);

			}

		}

		tagCompound.setTag("Inventory", itemList);

	}

	@Override
	public String getInvName() {

		return "TileEntityRuneFocus";

	}

	public boolean isInfusing() {
		return inventory[0] != null && inventory[1] != null && canInfuse();
	}

}
