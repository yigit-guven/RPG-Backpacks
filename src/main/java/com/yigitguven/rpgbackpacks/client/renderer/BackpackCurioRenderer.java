package com.yigitguven.rpgbackpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yigitguven.rpgbackpacks.client.ClientSetup;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BackpackCurioRenderer implements ICurioRenderer {
    private com.yigitguven.rpgbackpacks.client.model.BackpackCustomModel<LivingEntity> model;

    @Override
    public <T extends LivingEntity, M extends net.minecraft.client.model.EntityModel<T>> void render(ItemStack stack,
            SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.model == null) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            this.model = new com.yigitguven.rpgbackpacks.client.model.BackpackCustomModel<>(models.bakeLayer(ClientSetup.BACKPACK_LAYER));
        }

        // Sync model with entity
        this.model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        ResourceLocation texture = getBackpackTexture(stack);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer,
            RenderType.armorCutoutNoCull(texture), stack.hasFoil());

        matrixStack.pushPose();
        if (slotContext.entity().isCrouching()) {
            if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
                matrixStack.mulPose(Axis.XP.rotation(humanoidModel.body.xRot));
            }
            matrixStack.translate(0.0D, 0.13D, -0.14D);
        }
        this.model.renderBackpack(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        matrixStack.popPose();
    }

    private static ResourceLocation getBackpackTexture(ItemStack stack) {
        ResourceLocation itemId = ResourceLocation.parse(stack.getItemHolder().getRegisteredName());
        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "textures/item/" + itemId.getPath() + ".png");
    }
}
