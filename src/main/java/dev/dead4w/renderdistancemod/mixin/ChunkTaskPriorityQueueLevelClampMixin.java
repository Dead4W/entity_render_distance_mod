package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.server.level.ChunkTaskPriorityQueue;
import net.minecraft.util.Mth;

@Mixin(ChunkTaskPriorityQueue.class)
public abstract class ChunkTaskPriorityQueueLevelClampMixin {
    @ModifyVariable(
        method = "submit(Ljava/util/Optional;JI)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    private int renderDistanceMod$clampSubmitLevel(int level) {
        return Mth.clamp(level, 0, ChunkTaskPriorityQueue.PRIORITY_LEVEL_COUNT - 1);
    }

    @ModifyVariable(
        method = "resortChunkTasks(ILnet/minecraft/world/level/ChunkPos;I)V",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private int renderDistanceMod$clampResortFromLevel(int level) {
        return Mth.clamp(level, 0, ChunkTaskPriorityQueue.PRIORITY_LEVEL_COUNT - 1);
    }

    @ModifyVariable(
        method = "resortChunkTasks(ILnet/minecraft/world/level/ChunkPos;I)V",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 1
    )
    private int renderDistanceMod$clampResortToLevel(int level) {
        return Mth.clamp(level, 0, ChunkTaskPriorityQueue.PRIORITY_LEVEL_COUNT - 1);
    }
}
