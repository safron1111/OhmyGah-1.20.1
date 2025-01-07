package net.jeb.ohmygah;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jeb.ohmygah.entity.client.BouncySlimeRenderer;
import net.jeb.ohmygah.entity.client.PlasticExplosiveRenderer;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class OhmygahClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BOUNCYSLIME,BouncySlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLUE_SLIMEBALL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PLASTIC_EXPLOSIVE_ENTITY, PlasticExplosiveRenderer::new);
    }
}
