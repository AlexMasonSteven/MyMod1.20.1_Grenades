package com.grenades.weapons.init;

import com.grenades.weapons.Reference;
import com.grenades.weapons.item.transition.grenades.BaseballGrenadeItem;
import com.grenades.weapons.item.transition.grenades.LightGrenadeItem;
import com.grenades.weapons.item.transition.grenades.StunGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.FlashGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.ImpactFlashGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.IncendiaryGrenadeItem;
import com.grenades.weapons.item.transition.grenades.utility.WhiteSmokeGrenadeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    //public static final RegistryObject<Item> STUN_GRENADE = REGISTER.register("stun_grenade", () ->  new StunGrenadeItem(new Item.Properties().stacksTo(4), 20 * 9, 1.135f));
    public static final RegistryObject<Item> STANDARD_FLASH_GRENADE = REGISTER.register("flash_grenade", () -> new FlashGrenadeItem(new Item.Properties().stacksTo(4),20 * 2, 0.95f));
    public static final RegistryObject<Item> IMPACT_FLASH_GRENADE = REGISTER.register("impact_flash_grenade", () -> new ImpactFlashGrenadeItem(new Item.Properties().stacksTo(4),20 * 9, 0.95f));
    public static final RegistryObject<Item> LIGHT_GRENADE = REGISTER.register("light_grenade", () ->  new LightGrenadeItem(new Item.Properties().stacksTo(8), 25 * 4, 0.95f, 1.35f));
    public static final RegistryObject<Item> BASEBALL_GRENADE = REGISTER.register("baseball_grenade", () ->  new BaseballGrenadeItem(new Item.Properties().stacksTo(4), 20 * 5, 1.425f, 1.135f));
    public static final RegistryObject<Item> INCENDIARY_GRENADE = REGISTER.register("incendiary_grenade", () -> new IncendiaryGrenadeItem(new Item.Properties().stacksTo(4),20 * 9, 1.35f));
    public static final RegistryObject<Item> WHITE_SMOKE_GRENADE = REGISTER.register("white_smoke_grenade", () -> new WhiteSmokeGrenadeItem(new Item.Properties().stacksTo(4),20 * 3000, 1.35f));

    public ModItems(){
        REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }



}
