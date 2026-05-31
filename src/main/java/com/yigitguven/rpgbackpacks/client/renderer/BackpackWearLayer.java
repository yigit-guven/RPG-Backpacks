package com.yigitguven.rpgbackpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yigitguven.rpgbackpacks.client.ClientSetup;
import com.yigitguven.rpgbackpacks.item.BackpackItem;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class BackpackWearLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final com.yigitguven.rpgbackpacks.client.model.BackpackCustomModel<AbstractClientPlayer> backpackModel;

    public BackpackWearLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
            net.minecraft.client.model.geom.EntityModelSet entityModels) {
        super(renderer);
        this.backpackModel = new com.yigitguven.rpgbackpacks.client.model.BackpackCustomModel<>(entityModels.bakeLayer(ClientSetup.BACKPACK_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player,
            float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw,
            float headPitch) {
        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!(chestStack.getItem() instanceof BackpackItem)) {
            return;
        }

        this.getParentModel().copyPropertiesTo(this.backpackModel);
        this.backpackModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        ResourceLocation texture = getBackpackTexture(chestStack);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(buffer,
            RenderType.armorCutoutNoCull(texture), chestStack.hasFoil());

        poseStack.pushPose();
        if (player.isCrouching()) {
            poseStack.mulPose(Axis.XP.rotation(this.getParentModel().body.xRot));
            poseStack.translate(0.0D, 0.05D, -0.14D);
        }
        this.backpackModel.renderBackpack(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
            0xFFFFFFFF);
        poseStack.popPose();
    }

        private static ResourceLocation getBackpackTexture(ItemStack stack) {
            ResourceLocation itemId = ResourceLocation.parse(stack.getItemHolder().getRegisteredName());
        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "textures/item/" + itemId.getPath() + ".png");
        }
}
