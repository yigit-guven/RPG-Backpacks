package com.yigitguven.rpgbackpacks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(RpgBackpacks.MODID)
public class RpgBackpacks {
    public static final String MODID = "rpgbackpacks";

    public RpgBackpacks(IEventBus modEventBus, ModContainer modContainer) {
        com.yigitguven.rpgbackpacks.registry.ModItems.register(modEventBus);
        com.yigitguven.rpgbackpacks.registry.ModMenus.register(modEventBus);
        com.yigitguven.rpgbackpacks.registry.ModDataComponents.register(modEventBus);
        com.yigitguven.rpgbackpacks.registry.ModCreativeTabs.register(modEventBus);
        com.yigitguven.rpgbackpacks.registry.ModRecipes.register(modEventBus);

        modEventBus.addListener(this::onInterModEnqueue);
        modEventBus.addListener(this::registerNetworking);
    }

    private void onInterModEnqueue(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("curios")) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                    () -> SlotTypePreset.BACK.getMessageBuilder().size(1).build());
        }
    }

    private void registerNetworking(final net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent event) {
        final net.neoforged.neoforge.network.registration.PayloadRegistrar registrar = event.registrar(MODID);
        registrar.playToServer(
                com.yigitguven.rpgbackpacks.network.OpenBackpackPayload.TYPE,
                com.yigitguven.rpgbackpacks.network.OpenBackpackPayload.STREAM_CODEC,
                com.yigitguven.rpgbackpacks.network.OpenBackpackPayload::handle);
    }
}
