package com.grenades.weapons.item.transition.grenades;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.specifics.BaseballGrenadeEntity;
import com.grenades.weapons.item.GrenadeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class BaseballGrenadeItem extends GrenadeItem
{
    private float power;
    public BaseballGrenadeItem(Properties properties, int maxCookTime, float power, float speed)
    {
        super(properties, maxCookTime, power, speed);
        this.power = power;
    }

    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new BaseballGrenadeEntity(world, entity, timeLeft, this.power); // Current ThrowableGrenadeEntity is perfect for impact 1/31/2022
    }

    public boolean canCook()
    {
        return true;
    }

    protected void onThrown(Level world, ThrowableGrenadeEntity entity)
    {
    }
}
