// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class redfin<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "redfin"), "main");
	private final ModelPart body;
	private final ModelPart body_flipped;
	private final ModelPart body_sub_1;
	private final ModelPart eye;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart spine1;
	private final ModelPart spine1_rotation;
	private final ModelPart spine2;
	private final ModelPart spine2_rotation;
	private final ModelPart spine3;
	private final ModelPart spine3_rotation;
	private final ModelPart spine4;
	private final ModelPart spine4_rotation;
	private final ModelPart spine5;
	private final ModelPart spine5_rotation;
	private final ModelPart spine6;
	private final ModelPart spine6_rotation;
	private final ModelPart spine7;
	private final ModelPart spine7_rotation;
	private final ModelPart spine8;
	private final ModelPart spine8_rotation;
	private final ModelPart spine9;
	private final ModelPart spine9_rotation;
	private final ModelPart spine10;
	private final ModelPart spine10_rotation;
	private final ModelPart spine11;
	private final ModelPart spine11_rotation;
	private final ModelPart spine12;
	private final ModelPart spine12_rotation;

	public redfin(ModelPart root) {
		this.body = root.getChild("body");
		this.body_flipped = this.body.getChild("body_flipped");
		this.body_sub_1 = this.body_flipped.getChild("body_sub_1");
		this.eye = root.getChild("eye");
		this.tail1 = root.getChild("tail1");
		this.tail2 = root.getChild("tail2");
		this.tail3 = root.getChild("tail3");
		this.spine1 = root.getChild("spine1");
		this.spine1_rotation = this.spine1.getChild("spine1_rotation");
		this.spine2 = root.getChild("spine2");
		this.spine2_rotation = this.spine2.getChild("spine2_rotation");
		this.spine3 = root.getChild("spine3");
		this.spine3_rotation = this.spine3.getChild("spine3_rotation");
		this.spine4 = root.getChild("spine4");
		this.spine4_rotation = this.spine4.getChild("spine4_rotation");
		this.spine5 = root.getChild("spine5");
		this.spine5_rotation = this.spine5.getChild("spine5_rotation");
		this.spine6 = root.getChild("spine6");
		this.spine6_rotation = this.spine6.getChild("spine6_rotation");
		this.spine7 = root.getChild("spine7");
		this.spine7_rotation = this.spine7.getChild("spine7_rotation");
		this.spine8 = root.getChild("spine8");
		this.spine8_rotation = this.spine8.getChild("spine8_rotation");
		this.spine9 = root.getChild("spine9");
		this.spine9_rotation = this.spine9.getChild("spine9_rotation");
		this.spine10 = root.getChild("spine10");
		this.spine10_rotation = this.spine10.getChild("spine10_rotation");
		this.spine11 = root.getChild("spine11");
		this.spine11_rotation = this.spine11.getChild("spine11_rotation");
		this.spine12 = root.getChild("spine12");
		this.spine12_rotation = this.spine12.getChild("spine12_rotation");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 13.0F, -8.0F, 12.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(16, 40).addBox(-6.0F, 12.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(16, 40).addBox(-6.0F, 18.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-8.0F, 13.0F, -6.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_flipped = body.addOrReplaceChild("body_flipped", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_sub_1 = body_flipped.addOrReplaceChild("body_sub_1", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(6.0F, -11.0F, -6.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye = partdefinition.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, 15.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -8.25F));

		PartDefinition tail1 = partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, 14.0F, 5.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail2 = partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 54).addBox(0.0F, 14.0F, -2.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.5F, 14.0F));

		PartDefinition tail3 = partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(41, 32).addBox(0.0F, 14.0F, -4.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(25, 19).addBox(1.0F, 10.5F, -1.0F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, 20.0F));

		PartDefinition spine1 = partdefinition.addOrReplaceChild("spine1", CubeListBuilder.create(), PartPose.offset(0.0F, 11.5F, 7.0F));

		PartDefinition spine1_rotation = spine1.addOrReplaceChild("spine1_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition spine2 = partdefinition.addOrReplaceChild("spine2", CubeListBuilder.create(), PartPose.offset(0.0F, 11.5F, -7.0F));

		PartDefinition spine2_rotation = spine2.addOrReplaceChild("spine2_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition spine3 = partdefinition.addOrReplaceChild("spine3", CubeListBuilder.create(), PartPose.offset(7.0F, 11.5F, 0.0F));

		PartDefinition spine3_rotation = spine3.addOrReplaceChild("spine3_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition spine4 = partdefinition.addOrReplaceChild("spine4", CubeListBuilder.create(), PartPose.offset(-7.0F, 11.5F, 0.0F));

		PartDefinition spine4_rotation = spine4.addOrReplaceChild("spine4_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition spine5 = partdefinition.addOrReplaceChild("spine5", CubeListBuilder.create(), PartPose.offset(-7.0F, 18.5F, -7.0F));

		PartDefinition spine5_rotation = spine5.addOrReplaceChild("spine5_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 1.5708F, 0.7854F, 0.0F));

		PartDefinition spine6 = partdefinition.addOrReplaceChild("spine6", CubeListBuilder.create(), PartPose.offset(7.0F, 18.5F, -7.0F));

		PartDefinition spine6_rotation = spine6.addOrReplaceChild("spine6_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 1.5708F, -0.7854F, 0.0F));

		PartDefinition spine7 = partdefinition.addOrReplaceChild("spine7", CubeListBuilder.create(), PartPose.offset(7.0F, 18.5F, 7.0F));

		PartDefinition spine7_rotation = spine7.addOrReplaceChild("spine7_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, -1.5708F, 0.7854F, 0.0F));

		PartDefinition spine8 = partdefinition.addOrReplaceChild("spine8", CubeListBuilder.create(), PartPose.offset(-7.0F, 18.5F, 7.0F));

		PartDefinition spine8_rotation = spine8.addOrReplaceChild("spine8_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, -1.5708F, -0.7854F, 0.0F));

		PartDefinition spine9 = partdefinition.addOrReplaceChild("spine9", CubeListBuilder.create(), PartPose.offset(0.0F, 25.5F, 7.0F));

		PartDefinition spine9_rotation = spine9.addOrReplaceChild("spine9_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition spine10 = partdefinition.addOrReplaceChild("spine10", CubeListBuilder.create(), PartPose.offset(0.0F, 25.5F, -7.0F));

		PartDefinition spine10_rotation = spine10.addOrReplaceChild("spine10_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition spine11 = partdefinition.addOrReplaceChild("spine11", CubeListBuilder.create(), PartPose.offset(7.0F, 25.5F, 0.0F));

		PartDefinition spine11_rotation = spine11.addOrReplaceChild("spine11_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition spine12 = partdefinition.addOrReplaceChild("spine12", CubeListBuilder.create(), PartPose.offset(-7.0F, 25.5F, 0.0F));

		PartDefinition spine12_rotation = spine12.addOrReplaceChild("spine12_rotation", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, -2.3562F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		eye.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine11.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spine12.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}