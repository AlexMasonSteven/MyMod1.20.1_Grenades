package com.grenades.weapons.init;

import com.grenades.weapons.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    public static RegistryObject<CreativeModeTab> GRENADES_TAB = TABS.register("other", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.Grenades_by_Mason"))
            .icon(() -> ModItems.STANDARD_FLASH_GRENADE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.STANDARD_FLASH_GRENADE.get());
                output.accept(ModItems.STUN_GRENADE.get());
                output.accept(ModItems.IMPACT_FLASH_GRENADE.get());
                output.accept(ModItems.LIGHT_GRENADE.get());
                output.accept(ModItems.BASEBALL_GRENADE.get());
            }).build());
}
