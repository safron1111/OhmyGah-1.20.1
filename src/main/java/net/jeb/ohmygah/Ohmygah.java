package net.jeb.ohmygah;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.jeb.ohmygah.block.ModBlocks;
import net.jeb.ohmygah.block.entity.ModBlockEntities;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.bouncy_slime.FriendlyBouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.friendly_slime.FriendlySlime;
import net.jeb.ohmygah.entity.custom.kingslime.KingSlimeEntity;
import net.jeb.ohmygah.item.ModItemGroups;
import net.jeb.ohmygah.item.ModItems;
import net.jeb.ohmygah.particle.ModParticles;
import net.jeb.ohmygah.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ohmygah implements ModInitializer {
	public static final String MOD_ID = "ohmygah";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModScreenHandlers.registerScreenHandlers();
		ModBlockEntities.registerModBlockEntities();
		ModBlocks.registerModBlocks();
		ModParticles.registerModParticles();
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.BOUNCYSLIME, BouncySlimeEntity.createBouncySlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.FRIENDLY_SLIME, FriendlySlime.createSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.FRIENDLY_BOUNCYSLIME, FriendlyBouncySlimeEntity.createBouncySlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.KINGSLIME, KingSlimeEntity.createBouncySlimeAttributes());
	}
}