package net.jeb.ohmygah.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup OHMYGAH_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Ohmygah.MOD_ID, "ohmygah"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ohmygah"))
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.RAW_SAPPHIRE);
                        entries.add(ModBlocks.SAPPHIRE_BLOCK);
                    }).build());

    public static void registerItemGroups(){
        Ohmygah.LOGGER.info("Registering item groups for" + Ohmygah.MOD_ID + " uwu");
    }
}
