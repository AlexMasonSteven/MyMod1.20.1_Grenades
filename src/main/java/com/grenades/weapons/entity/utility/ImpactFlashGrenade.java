package com.grenades.weapons.entity.utility;

import com.grenades.weapons.Config;
import com.grenades.weapons.entity.ThrowableStunGrenadeEntity;
import com.grenades.weapons.init.ModEffects;
import com.grenades.weapons.init.ModItems;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

public class ImpactFlashGrenade extends ThrowableStunGrenadeEntity {
    public ImpactFlashGrenade(Level world, LivingEntity player, int maxCookTime) {
        super(world, player, maxCookTime);
        this.setShouldBounce(false);
        this.setItem(new ItemStack(ModItems.STANDARD_FLASH_GRENADE.get()));
        this.setGravityVelocity(0.01F);
    }
    protected boolean calculateAndApplyEffect(MobEffect effect, Config.EffectCriteria criteria, LivingEntity entity, Vec3 grenade, Vec3 eyes, double distance, double angle)
    {
        double angleMax = criteria.angleEffect.get() * 0.5;
        //TODO: Apply a more cohesive config so other grenade types can be customized, for example impact flash should have a shorter time of flash and smaller radius
        if(distance <= criteria.radius.get()/1.25f && angleMax > 0 && angle <= angleMax)
        {
            // Verify that light can pass through all blocks obstructing the entity's line of sight to the grenade
            if(effect != ModEffects.BLINDED.get() || !Config.SERVER.stunGrenades.blind.criteria.raytraceOpaqueBlocks.get() || rayTraceOpaqueBlocks(this.level(), eyes, grenade, false, false, false) == null)
            {
                // Duration attenuated by distance
                int durationBlinded = (int) Math.round(criteria.durationMax.get() - (criteria.durationMax.get() - criteria.durationMin.get()) * (distance / criteria.radius.get()));

                // Duration further attenuated by angle
                durationBlinded *= 1 - (angle * (1 - criteria.angleAttenuationMax.get())) / angleMax;

                entity.addEffect(new MobEffectInstance(effect, durationBlinded/2, 0, false, false));

                return !(entity instanceof Player);
            }
        }
        return false;
    }
}
