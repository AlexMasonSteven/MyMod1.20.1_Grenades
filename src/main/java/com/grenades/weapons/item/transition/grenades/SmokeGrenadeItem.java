package com.grenades.weapons.item.transition.grenades;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.ThrowableSmokeGrenadeEntity;
import com.grenades.weapons.init.ModSounds;
import com.grenades.weapons.item.GrenadeItem;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class  SmokeGrenadeItem extends GrenadeItem {
    public SmokeGrenadeItem(Properties properties, int maxCookTime, float speed)
    {
        super(properties, maxCookTime, 0,  speed);
    }

    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft)
    {
        return new ThrowableSmokeGrenadeEntity(world, entity, super.maxCookTime);
    }

    @Override
    public boolean canCook()
    {
        return false;
    }

    @Override
    protected void onThrown(Level world, ThrowableGrenadeEntity entity)
    {
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSounds.ITEM_GRENADE_PIN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}