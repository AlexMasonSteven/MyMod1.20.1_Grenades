package com.grenades.weapons.item.transition.grenades.utility;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.utility.ImpactFlashGrenade;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class ImpactFlashGrenadeItem extends FlashGrenadeItem
{
    public ImpactFlashGrenadeItem(Item.Properties properties, int maxCookTime, float speed)
    {
        super(properties, maxCookTime, speed);
    }
    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new ImpactFlashGrenade(world, entity, super.maxCookTime);
    }
    @Override
    public boolean canCook()
    {
        return false;
    }
}
