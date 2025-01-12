package net.jeb.ohmygah.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.block.advanced.*;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)));

    public static final Block PLASTIC_EXPLOSIVE = registerBlock("plastic_explosive",
            new PlasticExplosive(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block NUCLEAR_BOMB = registerBlock("nuclear_bomb",
            new NuclearBomb(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block SPRING_BLOCK = registerBlock("spring_block",
            new SpringBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block SUPERSPRING_BLOCK = registerBlock("superspring_block",
            new SuperSpringBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.EMERALD_ORE),UniformIntProvider.create(2,4)));
    public static final Block BLUE_SLIME_BLOCK = registerBlock("blue_slime_block",
            new BlueSlimeBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).nonOpaque()));

    public static final Block SLIRT = registerBlock("slirt",
            new Block(FabricBlockSettings.copyOf(Blocks.DIRT).ticksRandomly()));
    public static final Block SLRASS = registerBlock("slrass",
            new PlantBlock(FabricBlockSettings.copyOf(Blocks.GRASS)));
    public static final Block SLRASS_BLOCK = registerBlock("slrass_block",
            new SlimeAffectedGroundBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).ticksRandomly()));
    public static final Block BLUE_SLRASS = registerBlock("blue_slrass",
            new PlantBlock(FabricBlockSettings.copyOf(Blocks.GRASS)));
    public static final Block BLUE_SLRASS_BLOCK = registerBlock("blue_slrass_block",
            new BlueslimeAffectedGroundBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).ticksRandomly()));

    public static final Block NUCLEAR_FALLOUT = registerBlock("nuclear_fallout",
            new NuclearFallout(FabricBlockSettings.copyOf(Blocks.SNOW).ticksRandomly()));
    public static final Block NUCLEAR_FALLOUT_BLOCK = registerBlock("nuclear_fallout_block",
            new NuclearFalloutBlock(FabricBlockSettings.copyOf(Blocks.SNOW_BLOCK).ticksRandomly()));

    public static final Block ALTAR_BLOCK = registerBlock("altar",
            new Altar(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(Ohmygah.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Ohmygah.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Ohmygah.LOGGER.info("Registering blocks for " + Ohmygah.MOD_ID + " uwu");
    }
}
