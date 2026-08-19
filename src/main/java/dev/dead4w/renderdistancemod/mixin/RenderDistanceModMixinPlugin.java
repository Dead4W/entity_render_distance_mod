package dev.dead4w.renderdistancemod.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class RenderDistanceModMixinPlugin implements IMixinConfigPlugin {
    private static final String SODIUM_CONFIG_BUILDER_CLASS = "net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder";
    private static final String SODIUM_MIXIN = "dev.dead4w.renderdistancemod.mixin.SodiumConfigBuilderDistanceCapMixin";

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (SODIUM_MIXIN.equals(mixinClassName)) {
            return classExists(SODIUM_CONFIG_BUILDER_CLASS);
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className, false, RenderDistanceModMixinPlugin.class.getClassLoader());
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
