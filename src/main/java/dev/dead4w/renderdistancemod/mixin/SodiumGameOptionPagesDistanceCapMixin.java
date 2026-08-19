package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import dev.dead4w.renderdistancemod.RenderDistanceConfig;

@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages", remap = false)
public abstract class SodiumGameOptionPagesDistanceCapMixin {
    @ModifyConstant(method = "lambda$general$0", constant = @Constant(intValue = 32), remap = false)
    private static int renderDistanceMod$replaceSodiumRenderDistanceCap(int originalCap) {
        return Math.max(5, RenderDistanceConfig.chunkCap());
    }

    @ModifyConstant(method = "lambda$general$3", constant = @Constant(intValue = 32), remap = false)
    private static int renderDistanceMod$replaceSodiumSimulationDistanceCap(int originalCap) {
        return Math.max(5, RenderDistanceConfig.chunkCap());
    }
}
