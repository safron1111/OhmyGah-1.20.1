package net.jeb.ohmygah.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AltarEntity> ALTAR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE,new Identifier(Ohmygah.MOD_ID,"altar_block_entity"),
                    FabricBlockEntityTypeBuilder.create(AltarEntity::new, ModBlocks.ALTAR_BLOCK).build());

    public static void registerModBlockEntities(){
        Ohmygah.LOGGER.info("Registering block entities for " + Ohmygah.MOD_ID + " uwu");
    }
}
