package com.grenades.weapons.item.transition.grenades;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.ThrowableStunGrenadeEntity;
import com.grenades.weapons.item.GrenadeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class StunGrenadeItem extends GrenadeItem
{
    public StunGrenadeItem(Properties properties, int maxCookTime, float speed)
    {
        super(properties, maxCookTime, 1, speed);
    }

    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new ThrowableStunGrenadeEntity(world, entity, 20 * 2);
    }

    @Override
    public boolean canCook()
    {
        return false;
    }

    @Override
    protected void onThrown(Level world, ThrowableGrenadeEntity entity)
    {
        //world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.ITEM_GRENADE_PIN.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}

