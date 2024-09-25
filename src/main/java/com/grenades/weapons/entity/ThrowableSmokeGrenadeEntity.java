package com.grenades.weapons.entity;

import com.grenades.weapons.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ThrowableSmokeGrenadeEntity extends ThrowableGrenadeEntity {
    private static final int MAX_TICK_COUNT = 20 * 20;
    private static final double R = 3.0D;

    public ThrowableSmokeGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public ThrowableSmokeGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world, LivingEntity entity) {
        super(entityType, world, entity);
    }

    public ThrowableSmokeGrenadeEntity(Level world, LivingEntity player, int maxCookTime) {
        super(ModEntities.THROWABLE_SMOKE_GRENADE.get(), world, player);
        this.setMaxLife(maxCookTime);
    }

    private void smoke() {
        double xCenter = this.getX();
        double yCenter = this.getY();
        double zCenter = this.getZ();
        BlockPos blockPos = new BlockPos((int)xCenter, (int)yCenter, (int)zCenter);
        for (double x = xCenter - R; x < xCenter + R; x += 0.5D) {
            for (double y = yCenter - 0.5D; y < yCenter + R; y += 0.5D) {
                for (double z = zCenter - R; z < zCenter + R; z += 0.5D) {
                    if (Math.pow(x - xCenter, 2) + Math.pow(y + 0.5D - yCenter, 2) + Math.pow(z - zCenter, 2) <= R * R) {
                        this.level().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x, y + 0.5D, z, 0.0D, 0.0D, 0.0D);

                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        boolean isStationary = this.xOld == this.getX() && this.yOld == this.getY() && this.zOld == this.getZ();

        if (isStationary) {
            this.tickCount++;
        } else {
            this.tickCount = 0;
        }

        if (this.tickCount == 2) {
            this.onDeath();
        } else if (this.tickCount >= 2 && this.tickCount <= MAX_TICK_COUNT) {
            if (this.level().isClientSide && this.tickCount % 6 == 0) {
                this.smoke();
            }
        } else if (this.tickCount > MAX_TICK_COUNT) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void onDeath() {
        super.onDeath();
    }
}
