package com.yigitguven.rpgbackpacks.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yigitguven.rpgbackpacks.RpgBackpacks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class BackpackCustomModel<T extends LivingEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(RpgBackpacks.MODID, "backpack"), "main");

    private final ModelPart backpack;

    public BackpackCustomModel(ModelPart root) {
        this.backpack = root.getChild("backpack");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition backpack = partdefinition.addOrReplaceChild("backpack", CubeListBuilder.create().texOffs(32, 38).addBox(2.0F, -9.0F, 7.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 38).addBox(-4.0F, -9.0F, 7.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(2.0F, -9.0F, 3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-4.0F, -9.0F, 3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 28).addBox(2.0F, -3.0F, 3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 33).addBox(-4.0F, -3.0F, 3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(8, 39).addBox(4.0F, -7.25F, -1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(4.0F, -7.25F, -1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(38, 18).addBox(3.5F, -8.0F, -1.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(26, 42).addBox(4.0F, -8.25F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 43).addBox(4.0F, -6.25F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(26, 42).addBox(4.0F, -8.25F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 18).addBox(3.5F, -8.0F, 0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(42, 0).addBox(4.0F, -7.25F, 1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(42, 4).addBox(4.0F, -7.25F, 1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 21).addBox(4.0F, -6.25F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(26, 0).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 28).addBox(-6.0F, -5.0F, -1.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(38, 13).addBox(4.0F, -3.5F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.1F))
                .texOffs(26, 8).addBox(-3.0F, -6.2F, -4.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(0, 22).addBox(-3.5F, -12.0F, -1.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 36).addBox(4.0F, -3.5F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(22, 22).addBox(-3.5F, -12.0F, -1.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(12, 29).addBox(-2.5F, -12.0F, -1.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.21F))
                .texOffs(22, 35).addBox(1.5F, -12.0F, -1.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.21F))
                .texOffs(0, 14).addBox(-4.0F, -9.0F, -2.0F, 8.0F, 3.0F, 5.0F, new CubeDeformation(0.1F))
                .texOffs(12, 42).addBox(1.5F, -9.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(16, 42).addBox(-2.5F, -9.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(0, 42).addBox(-0.5F, -3.0F, -4.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(26, 13).addBox(-6.0F, -5.0F, -1.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 42).addBox(-1.0F, -3.5F, -4.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.75F, 5.0F, 0.0F, 3.1416F, 0.0F));

        backpack.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(6.0F, -1.5F, 0.5F, 0.7854F, 0.0F, 0.0F));
        backpack.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 47).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.0F, -9.5F, -1.0F, 0.0F, 0.0F, 0.7854F));
        backpack.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 47).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.0F, -9.5F, -1.0F, 0.0F, 0.0F, 0.7854F));
        backpack.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(4, 47).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.0F, -6.5F, -2.0F, 0.0F, 0.0F, 0.7854F));
        backpack.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.0F, -6.5F, -2.0F, 0.0F, 0.0F, 0.7854F));
        backpack.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 42).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -2.0F, 0.5F, 0.0F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
            int color) {
        this.backpack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    public void renderBackpack(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
            int color) {
        this.backpack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
