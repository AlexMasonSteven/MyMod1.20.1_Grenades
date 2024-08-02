package com.grenades.weapons.mixin.client;
import com.mojang.blaze3d.platform.Window;
import com.grenades.weapons.Config;
import com.grenades.weapons.init.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin
{
    RenderBuffers renderBuffers = Minecraft.getInstance().renderBuffers(); // 假设这是正确的初始化方式

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    public void render(float partialTicks, long nanoTime, boolean renderWorldIn, CallbackInfo ci)
    {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) {
            return;
        }
        Window window = Minecraft.getInstance().getWindow();
        MobEffectInstance blindedEffect = player.getEffect(ModEffects.BLINDED.get());
        if (blindedEffect != null) {
            float percent = Math.min((blindedEffect.getDuration() / (float) Config.SERVER.alphaFadeThreshold.get()), 1);
            GuiGraphics guigraphics = new GuiGraphics(minecraft, renderBuffers.bufferSource());
            // 使用 GuiGraphics 进行渲染
            guigraphics.fill(0, 0, window.getWidth(), window.getHeight(), ((int) (percent * Config.SERVER.alphaOverlay.get() + 0.5) << 24) | 16777215);
        }
    }
}
