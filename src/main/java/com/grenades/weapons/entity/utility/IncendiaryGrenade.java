package com.grenades.weapons.entity.utility;

import com.grenades.weapons.entity.ThrowableFireGrenadeEntity;
import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IncendiaryGrenade extends ThrowableFireGrenadeEntity {
    public IncendiaryGrenade(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public IncendiaryGrenade(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world, LivingEntity player) {
        super(entityType, world, player);
    }

    public IncendiaryGrenade(Level world, LivingEntity player, int maxCookTime) {
        super(world, player, maxCookTime);
        this.setItem(new ItemStack(ModItems.INCENDIARY_GRENADE.get()));
        this.setShouldBounce(true);
        this.setGravityVelocity(0.03F);
    }
}
