package com.grenades.weapons.init;

import com.grenades.weapons.Reference;
import com.grenades.weapons.effect.IncurableEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class ModEffects
{
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MOD_ID);

    public static final RegistryObject<IncurableEffect> BLINDED = REGISTER.register("blinded", () -> new IncurableEffect(MobEffectCategory.HARMFUL, 0));
    public static final RegistryObject<IncurableEffect> DEAFENED = REGISTER.register("deafened", () -> new IncurableEffect(MobEffectCategory.HARMFUL, 0));

    static {
        if (BLINDED == null) {
            throw new IllegalStateException("BLINDED effect is not initialized!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        if (DEAFENED == null) {
            throw new IllegalStateException("DEAFENED effect is not initialized!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

}
