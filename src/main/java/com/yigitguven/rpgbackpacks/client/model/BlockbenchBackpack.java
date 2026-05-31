package com.yigitguven.rpgbackpacks.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yigitguven.rpgbackpacks.RpgBackpacks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BlockbenchBackpack<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RpgBackpacks.MODID, "backpack"), "main");
    private final ModelPart backpack;

    public BlockbenchBackpack(ModelPart root) {
        this.backpack = root.getChild("backpack");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition backpack = partdefinition.addOrReplaceChild("backpack", CubeListBuilder.create().texOffs(32, 38).addBox(-6.0F, -12.0F, 15.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(38, 38).addBox(-12.0F, -12.0F, 15.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(0, 29).addBox(-6.0F, -12.0F, 11.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(0, 34).addBox(-12.0F, -12.0F, 11.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(34, 28).addBox(-6.0F, -6.0F, 11.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(34, 33).addBox(-12.0F, -6.0F, 11.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(0, 0).addBox(-12.0F, -12.0F, 6.0F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
        .texOffs(8, 39).addBox(-4.0F, -10.25F, 6.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(42, 0).addBox(-4.0F, -10.25F, 6.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
        .texOffs(38, 18).addBox(-4.5F, -11.0F, 6.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
        .texOffs(26, 42).addBox(-4.0F, -11.25F, 6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(6, 43).addBox(-4.0F, -9.25F, 6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
        .texOffs(26, 42).addBox(-4.0F, -11.25F, 9.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(38, 18).addBox(-4.5F, -11.0F, 8.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
        .texOffs(42, 0).addBox(-4.0F, -10.25F, 9.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
        .texOffs(42, 4).addBox(-4.0F, -10.25F, 9.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(44, 21).addBox(-4.0F, -9.25F, 9.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
        .texOffs(26, 0).addBox(-11.0F, -9.0F, 4.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(22, 28).addBox(-14.0F, -8.0F, 6.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.1F))
        .texOffs(38, 13).addBox(-4.0F, -6.5F, 7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.1F))
        .texOffs(26, 8).addBox(-11.0F, -9.2F, 4.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
        .texOffs(0, 22).addBox(-11.5F, -15.0F, 7.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(12, 36).addBox(-4.0F, -6.5F, 7.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(22, 22).addBox(-11.5F, -15.0F, 7.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
        .texOffs(12, 29).addBox(-10.5F, -15.0F, 7.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.21F))
        .texOffs(22, 35).addBox(-6.5F, -15.0F, 7.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.21F))
        .texOffs(0, 14).addBox(-12.0F, -12.0F, 6.0F, 8.0F, 3.0F, 5.0F, new CubeDeformation(0.1F))
        .texOffs(12, 42).addBox(-6.5F, -12.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
        .texOffs(16, 42).addBox(-10.5F, -12.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
        .texOffs(0, 42).addBox(-8.5F, -6.0F, 4.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.1F))
        .texOffs(26, 13).addBox(-14.0F, -8.0F, 6.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(20, 42).addBox(-9.0F, -6.5F, 3.75F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition cube_r1 = backpack.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.0F, -5.5F, 8.5F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r2 = backpack.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-6.0F, -13.5F, 7.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r3 = backpack.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-10.0F, -13.5F, 7.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r4 = backpack.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(4, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-6.0F, -10.5F, 6.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r5 = backpack.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-10.0F, -10.5F, 6.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r6 = backpack.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 42).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -6.0F, 8.5F, 0.0F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void renderBackpack(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        backpack.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int unknown) {
        // Delegate to backpack part render without tint
        backpack.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}