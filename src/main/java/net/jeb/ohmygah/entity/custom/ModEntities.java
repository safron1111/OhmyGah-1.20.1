package net.jeb.ohmygah.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.blueslimeball_entity.BlueSlimeballEntity;
import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.plasticexplosive.PlasticExplosiveEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BouncySlimeEntity> BOUNCYSLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Ohmygah.MOD_ID, "bouncy_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BouncySlimeEntity::new)
                    .dimensions(EntityDimensions.changing(1f,1f)).build());

    public static final EntityType<BlueSlimeballEntity> BLUE_SLIMEBALL_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Ohmygah.MOD_ID, "blue_slimeball_entity"),
            FabricEntityTypeBuilder.<BlueSlimeballEntity>create(SpawnGroup.MISC, BlueSlimeballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f,0.25f)).build());

    public static final EntityType<PlasticExplosiveEntity> PLASTIC_EXPLOSIVE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Ohmygah.MOD_ID, "plastic_explosive_entity"),
            FabricEntityTypeBuilder.<PlasticExplosiveEntity>create(SpawnGroup.MISC, PlasticExplosiveEntity::new)
                    .dimensions(EntityDimensions.changing(1f,1f)).build());

    public static void registerModEntities(){
        Ohmygah.LOGGER.info("Registering entities for " + Ohmygah.MOD_ID + " uwu");
    }
}

