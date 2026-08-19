package dev.dead4w.renderdistancemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

@Pseudo
@Mixin(targets = "dev.ryanhcode.sable.sublevel.system.ticket.PhysicsChunkTicketManager")
public abstract class SablePhysicsChunkLoadedEnoughMixin {
    private static final int FORCE_LOADED_CHUNK_RADIUS = 96;

    @Inject(method = "isChunkLoadedEnough", at = @At("HEAD"), cancellable = true)
    private static void renderDistanceMod$forceLoadedEnoughForNearbyPlayers(
        ServerLevel level,
        int x,
        int z,
        CallbackInfoReturnable<Boolean> cir
    ) {
        for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
            int playerChunkX = player.chunkPosition().x;
            int playerChunkZ = player.chunkPosition().z;
            int dx = Math.abs(playerChunkX - x);
            int dz = Math.abs(playerChunkZ - z);
            int maxDelta = Math.max(dx, dz);

            if (maxDelta <= FORCE_LOADED_CHUNK_RADIUS) {
                cir.setReturnValue(true);
                return;
            }
        }
    }
}
