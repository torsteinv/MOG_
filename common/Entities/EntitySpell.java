package Entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.world.World;

public abstract class EntitySpell extends EntityFireball {

	public EntitySpell(World par1World) {
		super(par1World);
	}

	public EntitySpell(World par1World, double par2, double par4, double par6,
			double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
	}

	public EntitySpell(World par1World, EntityLiving par2EntityLiving,
			double par3, double par5, double par7) {
		super(par1World, par2EntityLiving, par3, par5, par7);
	}

}
