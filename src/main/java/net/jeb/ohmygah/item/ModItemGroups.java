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
                        entries.add(ModBlocks.SAPPHIRE_BLOCK);
                        entries.add(ModBlocks.SAPPHIRE_ORE);
                        entries.add(ModItems.SAPPHIRE_SWORD);
                        entries.add(ModItems.SAPPHIRE_AXE);
                        entries.add(ModItems.SAPPHIRE_HOE);
                        entries.add(ModItems.SAPPHIRE_SHOVEL);
                        entries.add(ModItems.SAPPHIRE_PICKAXE);
                        entries.add(ModItems.SAPPHIRE_CROWN);
                        entries.add(ModItems.IRON_CHISEL);
                        entries.add(ModItems.SLIME_COLLAR);
                        entries.add(ModBlocks.SPRING_BLOCK);
                        entries.add(ModBlocks.SUPERSPRING_BLOCK);
                        entries.add(ModItems.BOUNCYSLIME_SPAWNEGG);
                        entries.add(ModItems.KINGSLIME_SPAWNER);
                        entries.add(ModItems.BLUE_SLIMEBALL);
                        entries.add(ModBlocks.BLUE_SLIME_BLOCK);
                        entries.add(ModItems.BLUESLIME_BOOTS);
                        entries.add(ModItems.BLUE_SLIMESLING);
                        entries.add(ModItems.SLIMESLING);
                        entries.add(ModItems.SLIME_BOOTS);
                        entries.add(ModBlocks.SLIRT);
                        entries.add(ModBlocks.SLRASS);
                        entries.add(ModBlocks.SLRASS_BLOCK);
                        entries.add(ModBlocks.BLUE_SLRASS);
                        entries.add(ModBlocks.BLUE_SLRASS_BLOCK);
                        entries.add(ModBlocks.PLASTIC_EXPLOSIVE);
                        entries.add(ModBlocks.NUCLEAR_BOMB);
                    }).build());

    public static void registerItemGroups(){
        Ohmygah.LOGGER.info("Registering item groups for" + Ohmygah.MOD_ID + " uwu");
    }
}
