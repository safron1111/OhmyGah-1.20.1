package net.jeb.ohmygah.entity.client;

import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.kingslime.KingSlimeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class KingSlimeRenderer extends MobEntityRenderer<KingSlimeEntity, SlimeEntityModel<KingSlimeEntity>> {
    private static final Identifier TEXTURE = new Identifier("ohmygah:textures/entity/bouncy_slime/bouncy_slime_king.png");

    public KingSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new SlimeEntityModel<>(context.getPart(EntityModelLayers.SLIME)), 0.25F);
        this.addFeature(new KingSlimeOverlayRenderer<>(this, context.getModelLoader()));
    }

    public void render(KingSlimeEntity slimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25F * (float)slimeEntity.getSize();
        super.render(slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(KingSlimeEntity slimeEntity) {
        return TEXTURE;
    }

    protected void scale(KingSlimeEntity slimeEntity, MatrixStack matrixStack, float f) {
        float g = 0.999F;
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0F, 0.001F, 0.0F);
        float h = (float)slimeEntity.getSize()-1+(slimeEntity.getHealth()/90);
        float i = MathHelper.lerp(f, slimeEntity.lastStretch, slimeEntity.stretch) / (h * 0.5F + 1.0F);
        float j = 1.0F / (i + 1.0F);
        matrixStack.scale(j * h, 1.0F / j * h, j * h);
    }
}
