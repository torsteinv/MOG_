package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import MISC.mod_MOG;

public class StaffExplosion extends Item {

	public StaffExplosion(int par1) {
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
		if (takePowder(player)) {
			EntityLargeFireball fb = new EntityLargeFireball(world, player, 1,
					1, 1);
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
