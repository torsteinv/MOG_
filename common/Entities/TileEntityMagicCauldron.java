package Entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagicCauldron extends TileEntity {
	public int iteration = 0;
	public int el[] = new int[] { 0, 0, 0 };

	public void write(int element) {
		el[iteration] = element;
		iteration = iteration == 2 ? 0 : iteration + 1;
	}

	@Override
	public void updateEntity() {
		System.out.println(el);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		iteration = nbt.getInteger("iteration");
		el = nbt.getIntArray("elements");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("iteration", iteration);
		nbt.setIntArray("elements", el);
	}

	public void clear() {
		el = new int[] { 0, 0, 0 };
	}
}
