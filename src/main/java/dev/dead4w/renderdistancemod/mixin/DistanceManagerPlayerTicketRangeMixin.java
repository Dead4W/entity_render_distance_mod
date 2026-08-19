package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import dev.dead4w.renderdistancemod.RenderDistanceConfig;
import net.minecraft.server.level.DistanceManager;

@Mixin(DistanceManager.class)
public abstract class DistanceManagerPlayerTicketRangeMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 32))
    private int renderDistanceMod$increasePlayerTicketTrackerRange(int ignoredOriginalValue) {
        return RenderDistanceConfig.chunkCap();
    }
}
