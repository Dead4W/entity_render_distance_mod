package dev.dead4w.renderdistancemod;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RenderDistanceConfig {
    private static final int DEFAULT_CHUNK_CAP = 64;
    public static final ModConfigSpec SPEC;
    private static final ModConfigSpec.IntValue CHUNK_CAP;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        CHUNK_CAP = builder
            .comment("Maximum chunk distance cap used by the mod for render distance and server tracking.")
            .defineInRange("chunkCap", DEFAULT_CHUNK_CAP, 32, 256);
        SPEC = builder.build();
    }

    private RenderDistanceConfig() {
    }

    public static int chunkCap() {
        try {
            return CHUNK_CAP.get();
        } catch (IllegalStateException ignored) {
            // Client options can initialize before config values are loaded.
            return DEFAULT_CHUNK_CAP;
        }
    }
}
