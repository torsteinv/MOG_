package Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import Entities.EntityLightball;
import MISC.mod_MOG;

public class StaffLightball extends PowerItem {

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
		if (!world.isRemote
				&& takeItemSingle(Item.lightStoneDust.itemID, player)) {
			if (!takePowder(player)) {
				player.inventory.addItemStackToInventory(new ItemStack(
						Item.lightStoneDust, 1));
				return itemStack;
			}
			EntityLightball fb = new EntityLightball(world, player, 1, 1, 1);
			Vec3 look = player.getLookVec();
			fb.setPosition(player.posX + look.xCoord * 1.5, player.posY
					+ look.yCoord * 1.5 + 1.5, player.posZ + look.zCoord * 1.5);
			fb.accelerationX = look.xCoord * 0.1;
			fb.accelerationY = look.yCoord * 0.1;
			fb.accelerationZ = look.zCoord * 0.1;
			fb.index = 0;
			world.spawnEntityInWorld(fb);
		}
		return itemStack;
	}

}
