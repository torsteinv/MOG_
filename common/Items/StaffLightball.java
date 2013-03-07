package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import Entities.EntityLightball;
import MISC.mod_MOG;

public class StaffLightball extends Item {

	public StaffLightball(int par1) {
		super(par1);
		this.setCreativeTab(mod_MOG.staffTab);
	}

	@Override
	public String getTextureFile() {
		return "/MOG_resources/Items.png";
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world,
			EntityPlayer player) {
		if (hasPowder(player, 1)
				&& hasItem(Item.lightStoneDust.itemID, player, 1)) {
			takePowder(player);
			takeItemSingle(Item.lightStoneDust.itemID, player);
			EntityLightball fb = new EntityLightball(world, player, 1, 1, 1);
			Vec3 look = player.getLookVec();
			fb.setPosition(player.posX + look.xCoord * 1.5, player.posY
					+ look.yCoord * 1.5 + 1, player.posZ + look.zCoord * 1.5);
			fb.accelerationX = look.xCoord * 0.1;
			fb.accelerationY = look.yCoord * 0.1;
			fb.accelerationZ = look.zCoord * 0.1;
			world.spawnEntityInWorld(fb);
		}
		return itemStack;
	}

	public boolean hasItem(int type, EntityPlayer player, int amount) {
		return player.inventory.hasItemStack(new ItemStack(type, amount, 0));
	}

	public boolean hasPowder(EntityPlayer player, int amount) {
		return hasItem(mod_MOG.MagicpowderID, player, amount);
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
