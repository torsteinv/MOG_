package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import MISC.mod_MOG;

public class StaffExplosion extends PowerItem {

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
		if (!world.isRemote && takePowder(player)) {
			EntityLargeFireball fb = new EntityLargeFireball(world, player, 1,
					1, 1);
			Vec3 look = player.getLookVec();
			fb.setPosition(player.posX + look.xCoord * 1.5, player.posY
					+ look.yCoord * 1.5 + 1.5, player.posZ + look.zCoord * 1.5);
			fb.accelerationX = look.xCoord * 0.1;
			fb.accelerationY = look.yCoord * 0.1;
			fb.accelerationZ = look.zCoord * 0.1;
			world.spawnEntityInWorld(fb);
		}
		return itemStack;
	}
}
