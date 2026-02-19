package com.yigitguven.rpgbackpacks.client;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.network.OpenBackpackPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = RpgBackpacks.MODID, value = Dist.CLIENT)
public class ClientInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (ModKeyMappings.OPEN_BACKPACK.consumeClick()) {
            if (Minecraft.getInstance().player != null) {
                PacketDistributor.sendToServer(new OpenBackpackPayload());
            }
        }
    }
}
