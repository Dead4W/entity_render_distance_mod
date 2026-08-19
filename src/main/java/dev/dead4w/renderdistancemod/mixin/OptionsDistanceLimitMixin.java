package dev.dead4w.renderdistancemod.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dead4w.renderdistancemod.RenderDistanceConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

@Mixin(Options.class)
public abstract class OptionsDistanceLimitMixin {
    @Shadow
    @Final
    @Mutable
    private OptionInstance<Integer> renderDistance;

    @Shadow
    @Final
    @Mutable
    private OptionInstance<Integer> simulationDistance;

    // Before load(): vanilla codec max is 32; values above that fail to parse and stay at default 12.
    @Inject(
        method = "<init>",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;load()V", shift = At.Shift.BEFORE)
    )
    private void renderDistanceMod$expandDistanceLimits(Minecraft minecraft, File gameDirectory, CallbackInfo ci) {
        int configuredCap = Math.max(5, RenderDistanceConfig.chunkCap());
        this.renderDistance = new OptionInstance<>(
            "options.renderDistance",
            OptionInstance.noTooltip(),
            (label, value) -> Options.genericValueLabel(label, Component.translatable("options.chunks", value)),
            new OptionInstance.IntRange(2, configuredCap, false),
            this.renderDistance.get(),
            value -> Minecraft.getInstance().levelRenderer.needsUpdate()
        );
        this.simulationDistance = new OptionInstance<>(
            "options.simulationDistance",
            OptionInstance.noTooltip(),
            (label, value) -> Options.genericValueLabel(label, Component.translatable("options.chunks", value)),
            new OptionInstance.IntRange(5, configuredCap, false),
            this.simulationDistance.get(),
            value -> {
            }
        );
    }
}
