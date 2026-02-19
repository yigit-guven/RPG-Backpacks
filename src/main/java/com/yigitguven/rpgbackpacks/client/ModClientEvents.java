package com.yigitguven.rpgbackpacks.client;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.client.screen.BackpackScreen;
import com.yigitguven.rpgbackpacks.registry.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = RpgBackpacks.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.BACKPACK_MENU.get(), BackpackScreen::new);
    }
}
