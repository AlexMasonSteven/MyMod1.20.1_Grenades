package com.grenades.weapons.item.transition.grenades.utility;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.init.ModSounds;
import com.grenades.weapons.item.GrenadeItem;
import com.grenades.weapons.entity.utility.StandardFlashGrenade;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FlashGrenadeItem extends GrenadeItem
{
    public FlashGrenadeItem(Properties properties, int maxCookTime, float speed)
    {
        super(properties, maxCookTime, 0,  speed);
    }

    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new StandardFlashGrenade(world, entity, super.maxCookTime);
    }

    @Override
    public boolean canCook()
    {
        return true;
    }

    @Override
    protected void onThrown(Level world, ThrowableGrenadeEntity entity)
    {
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.ITEM_GRENADE_PIN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}