package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import dev.dead4w.renderdistancemod.RenderDistanceConfig;
import net.minecraft.server.level.ChunkMap;

@Mixin(ChunkMap.class)
public abstract class ChunkMapViewDistanceMixin {
    @ModifyConstant(method = "setServerViewDistance", constant = @Constant(intValue = 32))
    private int renderDistanceMod$increaseServerViewDistanceCap(int ignoredOriginalValue) {
        return RenderDistanceConfig.chunkCap();
    }
}
