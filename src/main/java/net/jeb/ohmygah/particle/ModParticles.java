package net.jeb.ohmygah.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jeb.ohmygah.Ohmygah;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType BLUESLIME_PARTICLE = registerParticle("blueslime_particle",FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String texName, DefaultParticleType particle){
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Ohmygah.MOD_ID,texName),particle);
    }

    public static void registerModParticles(){
        Ohmygah.LOGGER.info("Registering particles in " + Ohmygah.MOD_ID + " uwu");
    }
}
