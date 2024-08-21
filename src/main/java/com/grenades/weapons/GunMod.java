package com.grenades.weapons;

import com.grenades.weapons.client.handler.ClientHandler;
import com.grenades.weapons.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class GunMod {
    public GunMod() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            // 只有在客户端环境中才注册客户端专属的东西
            // 客户端事件总线
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
            bus.addListener(ClientHandler::onClientSetup);
        }
        // 服务器端通用的初始化代码
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModEffects.REGISTER.register(bus);
        ModSounds.REGISTER.register(bus);
        ModCreativeTabs.TABS.register(bus);
    }
}
