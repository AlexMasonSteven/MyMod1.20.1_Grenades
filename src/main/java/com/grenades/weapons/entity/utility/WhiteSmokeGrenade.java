package com.grenades.weapons.entity.utility;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.ThrowableItemEntity;
import com.grenades.weapons.entity.ThrowableSmokeGrenadeEntity;
import com.grenades.weapons.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WhiteSmokeGrenade extends ThrowableSmokeGrenadeEntity {
    public WhiteSmokeGrenade(EntityType<? extends ThrowableGrenadeEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public WhiteSmokeGrenade(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world, LivingEntity entity) {
        super(entityType, world, entity);
    }

    public WhiteSmokeGrenade(Level world, LivingEntity player, int maxCookTime) {
        super(world, player, maxCookTime);
        this.setItem(new ItemStack(ModItems.WHITE_SMOKE_GRENADE.get()));
        this.setShouldBounce(true);
        this.setGravityVelocity(0.03F);
    }
}
