package net.jeb.ohmygah;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.jeb.ohmygah.block.ModBlocks;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.jeb.ohmygah.item.ModItemGroups;
import net.jeb.ohmygah.item.ModItems;
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
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.BOUNCYSLIME, BouncySlimeEntity.createBouncySlimeAttributes());
	}
}