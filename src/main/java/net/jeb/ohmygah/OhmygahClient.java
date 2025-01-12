package net.jeb.ohmygah;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jeb.ohmygah.block.ModBlocks;
import net.jeb.ohmygah.entity.client.*;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.particle.ModParticles;
import net.jeb.ohmygah.particle.factories.BlueSlimeballCrackParticle;
import net.jeb.ohmygah.screen.AltarScreen;
import net.jeb.ohmygah.screen.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.CrackParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;

public class OhmygahClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BOUNCYSLIME,BouncySlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.FRIENDLY_BOUNCYSLIME,FriendlyBouncySlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.FRIENDLY_SLIME, SlimeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.KINGSLIME, KingSlimeRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLUE_SLIMEBALL_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PLASTIC_EXPLOSIVE_ENTITY, PlasticExplosiveRenderer::new);
        EntityRendererRegistry.register(ModEntities.NUCLEAR_BOMB_ENTITY, NuclearBombRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.KINGSLIME, SlimeKingOuter::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_SLIME_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_SLRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALTAR_BLOCK, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.ALTAR_SCREEN_HANDLER, AltarScreen::new);
    }
}
