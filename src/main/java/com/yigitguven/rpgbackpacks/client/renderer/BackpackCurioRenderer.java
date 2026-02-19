package com.yigitguven.rpgbackpacks.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yigitguven.rpgbackpacks.client.ClientSetup;
import com.yigitguven.rpgbackpacks.client.model.BackpackModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
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
    private BackpackModel<LivingEntity> model;
    private static final ResourceLocation TEXTURE = ResourceLocation
            .withDefaultNamespace("textures/models/armor/iron_layer_1.png");
    // using iron armor texture for now as placeholder, ideally use
    // "rpgbackpacks:textures/models/armor/backpack.png"

    @Override
    public <T extends LivingEntity, M extends net.minecraft.client.model.EntityModel<T>> void render(ItemStack stack,
            SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.model == null) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            this.model = new BackpackModel<>(models.bakeLayer(ClientSetup.BACKPACK_LAYER));
        }

        // Sync model with entity
        this.model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
        ICurioRenderer.followBodyRotations(slotContext.entity(), this.model);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer,
                RenderType.armorCutoutNoCull(TEXTURE), stack.hasFoil());
        this.model.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
    }
}
