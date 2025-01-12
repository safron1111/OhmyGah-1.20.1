package net.jeb.ohmygah.entity.client;

import net.jeb.ohmygah.Ohmygah;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer KINGSLIME =
            new EntityModelLayer(new Identifier(Ohmygah.MOD_ID, "body"),"body");
}
