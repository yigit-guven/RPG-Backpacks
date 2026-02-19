package com.yigitguven.rpgbackpacks.client;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.client.model.BackpackModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = RpgBackpacks.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    public static final ModelLayerLocation BACKPACK_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(RpgBackpacks.MODID, "backpack"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BACKPACK_LAYER, BackpackModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onClientSetup(net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        if (net.neoforged.fml.ModList.get().isLoaded("curios")) {
            event.enqueueWork(() -> com.yigitguven.rpgbackpacks.compat.CuriosClientCompat.registerRenderers());
        }
    }
}
