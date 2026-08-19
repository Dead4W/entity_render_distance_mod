package dev.dead4w.renderdistancemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(RenderDistanceMod.MOD_ID)
public final class RenderDistanceMod {
    public static final String MOD_ID = "renderdistancemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RenderDistanceMod(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, RenderDistanceConfig.SPEC);
        LOGGER.info("Render Distance Mod loaded.");
    }
}
