package net.jeb.ohmygah.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.jeb.ohmygah.Ohmygah;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AltarScreenHandler> ALTAR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Ohmygah.MOD_ID,"altar"),
                    new ExtendedScreenHandlerType<>(AltarScreenHandler::new));

    public static void registerScreenHandlers() {
        Ohmygah.LOGGER.info("Registering screen handlers for " + Ohmygah.MOD_ID + " uwu");
    }
}
