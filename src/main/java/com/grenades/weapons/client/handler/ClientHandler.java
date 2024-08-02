package com.grenades.weapons.client.handler;

import com.grenades.weapons.Reference;
import com.grenades.weapons.client.render.entity.ThrowableGrenadeRenderer;
import com.grenades.weapons.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWABLE_GRENADE.get(), ThrowableGrenadeRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWABLE_STUN_GRENADE.get(), ThrowableGrenadeRenderer::new);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.THROWABLE_GRENADE.get(), context -> new ThrowableGrenadeRenderer(context));
        EntityRenderers.register(ModEntities.THROWABLE_STUN_GRENADE.get(), context -> new ThrowableGrenadeRenderer(context));
        MinecraftForge.EVENT_BUS.register(SoundHandler.get());
    }

}
