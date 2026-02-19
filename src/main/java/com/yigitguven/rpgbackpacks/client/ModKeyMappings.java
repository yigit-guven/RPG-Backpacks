package com.yigitguven.rpgbackpacks.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.yigitguven.rpgbackpacks.RpgBackpacks;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = RpgBackpacks.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModKeyMappings {
    public static final KeyMapping OPEN_BACKPACK = new KeyMapping(
            "key.rpgbackpacks.open_backpack",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.categories.rpgbackpacks");

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(OPEN_BACKPACK);
    }
}
