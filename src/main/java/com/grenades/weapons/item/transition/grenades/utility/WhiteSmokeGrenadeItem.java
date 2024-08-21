package com.grenades.weapons.item.transition.grenades.utility;

import com.grenades.weapons.entity.ThrowableGrenadeEntity;
import com.grenades.weapons.entity.utility.IncendiaryGrenade;
import com.grenades.weapons.entity.utility.WhiteSmokeGrenade;
import com.grenades.weapons.item.GrenadeItem;
import com.grenades.weapons.item.transition.grenades.SmokeGrenadeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class WhiteSmokeGrenadeItem extends SmokeGrenadeItem {
    public WhiteSmokeGrenadeItem(Properties properties, int maxCookTime, float speed) {
        super(properties, maxCookTime, speed);
    }
    @Override
    public ThrowableGrenadeEntity create(Level world, LivingEntity entity, int timeLeft){
        return new WhiteSmokeGrenade(world, entity, super.maxCookTime);
    }

}

