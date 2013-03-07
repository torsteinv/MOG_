package MISC;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;

public class PotionEffects extends Potion {

	public static final Potion Knockback = (new PotionEffects(
			mod_MOG.KnockbackID, false, 0xFFFFFF)).setPotionName(
			"potion.knockback").setIconIndex(0, 0);
	public static final Potion FieryBlade = (new PotionEffects(
			mod_MOG.FieryBladeID, false, 0xFF0000)).setPotionName(
			"potion.fireblade").setIconIndex(0, 4);
	public static final Potion Marker = (new PotionEffects(mod_MOG.MarkerID,
			false, 0xFF0000)).setPotionName("potion.marker").setIconIndex(0, 5);
	public static final Potion Marked = (new PotionEffects(mod_MOG.MarkedID,
			false, 0xFF0000)).setPotionName("potion.marked").setIconIndex(1, 4);
	public static final Potion Slower = (new PotionEffects(mod_MOG.SlowerID,
			false, 0x0000FF)).setPotionName("potion.slower").setIconIndex(1, 0);

	/**
	 * @param ID
	 * @param isBad
	 * @param color
	 */
	public PotionEffects(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
	}

	@Override
	public PotionEffects setIconIndex(int par1, int par2) {
		return (PotionEffects) super.setIconIndex(par1, par2);
	}

	@Override
	public PotionEffects setPotionName(String par1Str) {
		return (PotionEffects) super.setPotionName(par1Str);
	}

	@Override
	public void performEffect(EntityLiving par1EntityLiving, int par2) {
		if (this.id == Marked.id) {

		}
	}

	@Override
	public boolean isInstant() {
		return false;
	}

}
