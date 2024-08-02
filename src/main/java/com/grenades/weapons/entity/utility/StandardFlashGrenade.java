package com.grenades.weapons.entity.utility;

import com.grenades.weapons.entity.ThrowableStunGrenadeEntity;
import com.grenades.weapons.init.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StandardFlashGrenade extends ThrowableStunGrenadeEntity {

    public StandardFlashGrenade(Level world, LivingEntity player, int maxCookTime) {
        super(world, player, maxCookTime);
        this.setItem(new ItemStack(ModItems.STANDARD_FLASH_GRENADE.get()));
        this.setShouldBounce(true);
        this.setGravityVelocity(0.03F);
    }
}
