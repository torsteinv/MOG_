package Items;

import MISC.PotionEffects;
import MISC.mod_lawl;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Godsword extends ItemSword {

	public Godsword(int itemID, EnumToolMaterial material) {
		super(itemID, material);
		this.setCreativeTab(null);
		// if (itemID == mod_lawl.GodswordID)
		this.setCreativeTab(mod_lawl.staffTab);
	}

	@Override
	public String getTextureFile() {
		return "/Tutorial/Items.png";
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if (par3EntityPlayer.isSneaking()) {
			if (par1ItemStack.itemID == mod_lawl.GodswordID + 4 + 256) {
				par1ItemStack.itemID = mod_lawl.GodswordID + 256;
			} else {
				par1ItemStack.itemID++;
			}
		} else {
			switch (par1ItemStack.itemID - 256 - 500) {
			case 1:
				if (par3EntityPlayer.getMaxHealth()
						- par3EntityPlayer.getHealth() >= 2
						&& takePowder(par3EntityPlayer)) {
					par3EntityPlayer.heal(2);
				}
				break;
			case 2:
				if (takeMultiplePowder(16, par3EntityPlayer) == -1) {
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							PotionEffects.FieryBlade.id, 200, 1));
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							PotionEffects.Marker.id, 200, 10));
				}
				break;
			case 3:
				if (takeMultiplePowder(2, par3EntityPlayer) == -1) {
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							Potion.resistance.id, 100, 2));
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							PotionEffects.Slower.id, 300, 1));
				}
				break;
			case 4:
				if (takeMultiplePowder(2, par3EntityPlayer) == -1) {
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							PotionEffects.Knockback.id, 100, 1));
					par3EntityPlayer.addPotionEffect(new PotionEffect(
							PotionEffects.jump.id, 100, 10));
				}
				break;
			case 5:
				if (par1ItemStack.isItemDamaged()
						&& takeMultiplePowder(1, par3EntityPlayer) == -1) {
					par1ItemStack
							.setItemDamage(par1ItemStack.getItemDamage() - 10);
				}
				break;
			}
		}
		return super.onItemRightClick(par1ItemStack, par2World,
				par3EntityPlayer);
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
		return p.inventory.consumeInventoryItem(mod_lawl.MagicpowderID + 256);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		int var3 = 50;
		if (par1ItemStack.itemID == mod_lawl.GodswordID + 3 + 256
				&& par3EntityLiving.isPotionActive(PotionEffects.Knockback)) {
			par2EntityLiving.addVelocity(
					-MathHelper.sin(par3EntityLiving.rotationYaw
							* (float) Math.PI / 180.0F)
							* var3 * 0.5F,
					0.1D,
					MathHelper.cos(par3EntityLiving.rotationYaw
							* (float) Math.PI / 180.0F)
							* var3 * 0.5F);
		}
		if (par1ItemStack.itemID == mod_lawl.GodswordID + 1 + 256
				&& par2EntityLiving.isPotionActive(PotionEffects.Marked)) {
			par2EntityLiving.attackEntityFrom(DamageSource.magic, 50);
			par3EntityLiving.worldObj.spawnParticle("hugeexplosion",
					par2EntityLiving.posX, par2EntityLiving.posY,
					par2EntityLiving.posZ, 0, 0, 0);
			par2EntityLiving.worldObj
					.playSoundEffect(
							par2EntityLiving.posX,
							par2EntityLiving.posY,
							par2EntityLiving.posZ,
							"random.explode",
							4.0F,
							(1.0F + (par2EntityLiving.worldObj.rand.nextFloat() - par2EntityLiving.worldObj.rand
									.nextFloat()) * 0.2F) * 0.7F);
		}
		if (par1ItemStack.itemID == mod_lawl.GodswordID + 1 + 256
				&& par3EntityLiving.isPotionActive(PotionEffects.FieryBlade)) {
			par2EntityLiving.setFire(10);
		}
		if (par1ItemStack.itemID == mod_lawl.GodswordID + 1 + 256
				&& par3EntityLiving.isPotionActive(PotionEffects.Marker)) {
			par2EntityLiving.addPotionEffect(new PotionEffect(
					PotionEffects.Marked.id, 100, 1));
			par3EntityLiving.removePotionEffect(PotionEffects.Marker.id);
		}
		if (par1ItemStack.itemID == mod_lawl.GodswordID + 2 + 256
				&& par3EntityLiving.isPotionActive(PotionEffects.Slower)) {
			par2EntityLiving.addPotionEffect(new PotionEffect(
					Potion.moveSlowdown.id, 100, 2));
		}

		return super.hitEntity(par1ItemStack, par2EntityLiving,
				par3EntityLiving);
	}

}
