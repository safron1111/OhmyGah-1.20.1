package net.jeb.ohmygah.particle.factories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jeb.ohmygah.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;

public class BlueSlimeballCrackParticle extends SpriteBillboardParticle {
    private final float sampleU;
    private final float sampleV;

    @Override
    public ParticleTextureSheet getType() {
        return null;
    }

    @Override
    protected float getMinU() {
        return this.sprite.getFrameU((double)((this.sampleU + 1.0F) / 4.0F * 16.0F));
    }

    @Override
    protected float getMaxU() {
        return this.sprite.getFrameU((double)(this.sampleU / 4.0F * 16.0F));
    }

    @Override
    protected float getMinV() {
        return this.sprite.getFrameV((double)(this.sampleV / 4.0F * 16.0F));
    }

    @Override
    protected float getMaxV() {
        return this.sprite.getFrameV((double)((this.sampleV + 1.0F) / 4.0F * 16.0F));
    }

    public BlueSlimeballCrackParticle(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.setSprite(MinecraftClient.getInstance().getItemRenderer().getModel(stack, world, null, 0).getParticleSprite());
        this.gravityStrength = 1.0F;
        this.scale /= 2.0F;
        this.sampleU = this.random.nextFloat() * 3.0F;
        this.sampleV = this.random.nextFloat() * 3.0F;
    }

    @Environment(EnvType.CLIENT)
    public static class BlueSlimeballFactory implements ParticleFactory<DefaultParticleType> {
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new BlueSlimeballCrackParticle(clientWorld, d, e, f, new ItemStack(ModItems.BLUE_SLIMEBALL));
        }
    }
}
