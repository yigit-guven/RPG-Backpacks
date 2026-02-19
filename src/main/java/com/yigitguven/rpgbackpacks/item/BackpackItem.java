package com.yigitguven.rpgbackpacks.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;

public class BackpackItem extends Item implements Equipable {
    public final int rows;

    public BackpackItem(Properties properties, int rows) {
        super(properties.stacksTo(1));
        this.rows = rows;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
            java.util.List<net.minecraft.network.chat.Component> tooltipComponents,
            net.minecraft.world.item.TooltipFlag tooltipFlag) {
        // Slot count header
        tooltipComponents
                .add(net.minecraft.network.chat.Component.translatable("item.rpgbackpacks.backpack.slots", rows * 9)
                        .withStyle(net.minecraft.ChatFormatting.GRAY));

        // Inventory Preview
        net.minecraft.world.item.component.ItemContainerContents contents = stack
                .get(com.yigitguven.rpgbackpacks.registry.ModDataComponents.BACKPACK_CONTENTS.get());
        if (contents != null) {
            int count = 0;
            int maxLines = 5;
            int totalItems = 0;

            // We need to iterate streams or copy to list
            // ItemContainerContents doesn't expose a simple list directly without copy or
            // stream
            for (ItemStack item : contents.stream().toList()) {
                if (item.isEmpty())
                    continue;
                totalItems++;
                if (count < maxLines) {
                    tooltipComponents.add(net.minecraft.network.chat.Component.literal(" " + item.getCount() + "x ")
                            .append(item.getHoverName())
                            .withStyle(net.minecraft.ChatFormatting.GRAY));
                    count++;
                }
            }

            if (totalItems > maxLines) {
                tooltipComponents.add(net.minecraft.network.chat.Component
                        .literal(" + " + (totalItems - maxLines) + " more...")
                        .withStyle(net.minecraft.ChatFormatting.ITALIC, net.minecraft.ChatFormatting.DARK_GRAY));
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (!level.isClientSide) {
            player.openMenu(new net.minecraft.world.SimpleMenuProvider(
                    (id, inv, p) -> new com.yigitguven.rpgbackpacks.menu.BackpackMenu(id, inv, stack),
                    stack.getHoverName()), buffer -> {
                        buffer.writeInt(usedHand == InteractionHand.MAIN_HAND ? 0 : 1);
                    });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void initializeClient(
            java.util.function.Consumer<net.neoforged.neoforge.client.extensions.common.IClientItemExtensions> consumer) {
        consumer.accept(new net.neoforged.neoforge.client.extensions.common.IClientItemExtensions() {
            private com.yigitguven.rpgbackpacks.client.model.BackpackModel<net.minecraft.world.entity.LivingEntity> model;

            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(
                    net.minecraft.world.entity.LivingEntity livingEntity, ItemStack itemStack,
                    EquipmentSlot equipmentSlot, net.minecraft.client.model.HumanoidModel<?> original) {
                if (this.model == null) {
                    net.minecraft.client.model.geom.EntityModelSet models = net.minecraft.client.Minecraft.getInstance()
                            .getEntityModels();
                    net.minecraft.client.model.geom.ModelPart root = models
                            .bakeLayer(com.yigitguven.rpgbackpacks.client.ClientSetup.BACKPACK_LAYER);
                    this.model = new com.yigitguven.rpgbackpacks.client.model.BackpackModel<>(root);
                }

                // Copy properties from original armor model to sync animations
                this.model.crouching = original.crouching;
                this.model.riding = original.riding;
                this.model.young = original.young;
                this.model.rightArmPose = original.rightArmPose;
                this.model.leftArmPose = original.leftArmPose;

                return this.model;
            }
        });
    }
}
