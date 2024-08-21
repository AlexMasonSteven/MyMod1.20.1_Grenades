package com.grenades.weapons.entity;

import com.grenades.weapons.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrowableFireGrenadeEntity extends ThrowableGrenadeEntity {

    public ThrowableFireGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public ThrowableFireGrenadeEntity(EntityType<? extends ThrowableGrenadeEntity> entityType, Level world, LivingEntity player)
    {
        super(entityType, world, player);
    }

    public ThrowableFireGrenadeEntity(Level world, LivingEntity player, int maxCookTime) {
        super(ModEntities.THROWABLE_FIRE_GRENADE.get(), world, player);
        //this.setItem(new ItemStack(ModItems.STUN_GRENADE.get()));
        this.setMaxLife(maxCookTime);
    }

    @Override
    protected void onHit(HitResult result)
    {
        switch(result.getType())
        {
            case BLOCK:
                BlockHitResult blockResult = (BlockHitResult) result;
                if(this.shouldBounce && blockResult.getDirection() != Direction.UP)
                {
                    BlockPos resultPos = blockResult.getBlockPos();
                    BlockState state = this.level().getBlockState(resultPos);
                    SoundEvent event = state.getBlock().getSoundType(state, this.level(), resultPos, this).getStepSound();
                    double speed = this.getDeltaMovement().length();
                    if(speed > 0.1)
                    {
                        this.level().playSound(null, result.getLocation().x, result.getLocation().y, result.getLocation().z, event, SoundSource.AMBIENT, 1.0F, 1.0F);
                    }
                    Direction direction = blockResult.getDirection();
                    switch(direction.getAxis())
                    {
                        case X:
                            this.setDeltaMovement(this.getDeltaMovement().multiply(-0.5, 0.75, 0.75));
                            break;
                        case Y:
                            this.setDeltaMovement(this.getDeltaMovement().multiply(0.75, -0.25, 0.75));
                            if(this.getDeltaMovement().y() < this.getGravity())
                            {
                                this.setDeltaMovement(this.getDeltaMovement().multiply(1, 0, 1));
                            }
                            break;
                        case Z:
                            this.setDeltaMovement(this.getDeltaMovement().multiply(0.75, 0.75, -0.5));
                            break;
                    }
                }
                else
                {
                    this.remove(RemovalReason.DISCARDED);
                    this.onDeath();
                }
                break;
            case ENTITY:
                EntityHitResult entityResult = (EntityHitResult) result;
                Entity entity = entityResult.getEntity();
                if(entity != null)
                {

                }
                break;
            default:
                break;
        }

    }
    @Override
    public void onDeath(){
        int fireSpread = 4;
        super.onDeath();
        /*
        double Y = this.getY() + this.getType().getDimensions().height * 0.5;
        this.level().playSound(null, this.getX(), Y, this.getZ(), ModSounds.FIRE.get(), SoundSource.BLOCKS, 4, (1 + (level().random.nextFloat() - level().random.nextFloat()) * 0.2F) * 0.7F);
        this.level().addParticle(ParticleTypes.EXPLOSION, this.getX(), Y, this.getZ(), 1.0D, 0.0D, 0.0D);
        this.level().addParticle(ParticleTypes.FLAME, this.getX(), Y, this.getZ(), 1.0D, 0.0D, 0.0D);
         */
        for (int x = (int) this.getX() - fireSpread; x < (int) this.getX() + fireSpread - 1; x++) {
            for (int y = (int) this.getY() - fireSpread + 1; y < (int) this.getY() + fireSpread - 2; y++) {
                for (int z = (int) this.getZ() - fireSpread + 1; z < (int) this.getZ() + fireSpread; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    if (this.random.nextInt(3) != 1 && this.level().getBlockState(blockPos).isAir()) {
                        this.level().setBlockAndUpdate(blockPos, BaseFireBlock.getState(this.level(), blockPos));
                    }
                }
            }
        }
    }


}
