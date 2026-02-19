package com.yigitguven.rpgbackpacks.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BackpackModel<T extends LivingEntity> extends HumanoidModel<T> {

    public BackpackModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.getChild("body");

        body.addOrReplaceChild("backpack_body", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, 0.0F, 2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.75F)), // Scaled down slightly and
                                                                                           // pushed out
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
