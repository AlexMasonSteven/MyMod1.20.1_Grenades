package com.grenades.weapons.init;

import com.grenades.weapons.Reference;
import com.grenades.weapons.item.transition.grenades.BaseballGrenadeItem;
import com.grenades.weapons.item.transition.grenades.LightGrenadeItem;
import com.grenades.weapons.item.transition.grenades.StunGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.FlashGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.ImpactFlashGrenadeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final RegistryObject<Item> STUN_GRENADE = REGISTER.register("stun_grenade", () ->  new StunGrenadeItem(new Item.Properties().stacksTo(4), 20 * 7, 1.135f));
    public static final RegistryObject<Item> STANDARD_FLASH_GRENADE = REGISTER.register("flash_grenade", () -> new FlashGrenadeItem(new Item.Properties().stacksTo(4),20 * 5, 0.95f));
    public static final RegistryObject<Item> IMPACT_FLASH_GRENADE = REGISTER.register("impact_flash_grenade", () -> new ImpactFlashGrenadeItem(new Item.Properties().stacksTo(4),20 * 5, 0.95f));
    public static final RegistryObject<Item> LIGHT_GRENADE = REGISTER.register("light_grenade", () ->  new LightGrenadeItem(new Item.Properties().stacksTo(8), 25 * 4, 0.95f, 1.35f));
    public static final RegistryObject<Item> BASEBALL_GRENADE = REGISTER.register("baseball_grenade", () ->  new BaseballGrenadeItem(new Item.Properties().stacksTo(4), 20 * 7, 1.425f, 1.135f));

    public ModItems(){
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }



}
