package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import dev.dead4w.renderdistancemod.RenderDistanceConfig;

@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder", remap = false)
public abstract class SodiumConfigBuilderDistanceCapMixin {
    @ModifyConstant(method = "buildGeneralPage", constant = @Constant(intValue = 32), remap = false)
    private int renderDistanceMod$replaceSodiumDistanceCap(int originalCap) {
        return Math.max(5, RenderDistanceConfig.chunkCap());
    }
}
