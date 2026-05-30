package com.yigitguven.rpgbackpacks.network;

import com.yigitguven.rpgbackpacks.RpgBackpacks;
import com.yigitguven.rpgbackpacks.menu.BackpackMenu;
import com.yigitguven.rpgbackpacks.registry.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
// import top.theillusivec4.curios.api.CuriosApi; 

public record OpenBackpackPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenBackpackPayload> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(RpgBackpacks.MODID, "open_backpack"));

    public static final StreamCodec<FriendlyByteBuf, OpenBackpackPayload> STREAM_CODEC = StreamCodec
            .unit(new OpenBackpackPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(OpenBackpackPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            // Priority: Chestplate > Curios > MainHand > OffHand
            ItemStack backpackStack = ItemStack.EMPTY;
            int sourceId = -1;

            if (player.getItemBySlot(EquipmentSlot.CHEST)
                    .getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem) {
                backpackStack = player.getItemBySlot(EquipmentSlot.CHEST);
                sourceId = 2;
            } else {
                // Check Curios
                ItemStack curiosStack = com.yigitguven.rpgbackpacks.compat.CuriosCompat.findBackpack(player);
                if (!curiosStack.isEmpty()) {
                    backpackStack = curiosStack;
                    sourceId = 3;
                } else if (player.getMainHandItem()
                        .getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem) {
                    backpackStack = player.getMainHandItem();
                    sourceId = 0;
                } else if (player.getOffhandItem().getItem() instanceof com.yigitguven.rpgbackpacks.item.BackpackItem) {
                    backpackStack = player.getOffhandItem();
                    sourceId = 1;
                }
            }

            if (!backpackStack.isEmpty() && sourceId != -1) {
                final ItemStack finalStack = backpackStack;
                final int finalSourceId = sourceId;

                player.openMenu(new net.minecraft.world.SimpleMenuProvider(
                        (id, inv, p) -> new com.yigitguven.rpgbackpacks.menu.BackpackMenu(id, inv, finalStack, finalSourceId),
                        finalStack.getHoverName()), buffer -> {
                            buffer.writeInt(finalSourceId);
                        });
            }
        });
    }
}
