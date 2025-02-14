package net.jeb.ohmygah.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.item.advanced.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new FabricItemSettings()));

    public static final Item BLUE_SLIMEBALL = registerItem("blue_slimeball", new BlueSlimeballItem(new FabricItemSettings()));
    public static final Item BLUE_SLIMESLING = registerItem("blue_slimesling", new BlueSlimesling(new FabricItemSettings()));
    public static final Item BOUNCYSLIME_SPAWNEGG = registerItem("bouncyslime_spawnegg",
            new SpawnEggItem(ModEntities.BOUNCYSLIME,0x87C8C2,0xA2D2CD, new FabricItemSettings()));
    public static final Item KINGSLIME_SPAWNER = registerItem("kingslime_spawner",
            new SpawnEggItem(ModEntities.KINGSLIME,0xFFFFFF,0xFFFFFF, new FabricItemSettings()));

    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword",
            new SwordItem(ModToolMaterial.SAPPHIRE, 5, -2.4f, new FabricItemSettings()));
    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe",
            new AxeItem(ModToolMaterial.SAPPHIRE,7,-3.0f,new FabricItemSettings()));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe",
            new HoeItem(ModToolMaterial.SAPPHIRE,0,-1.0f,new FabricItemSettings()));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel",
            new ShovelItem(ModToolMaterial.SAPPHIRE,3,-3.0f,new FabricItemSettings()));
    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe",
            new PickaxeItem(ModToolMaterial.SAPPHIRE,3,-2.8f,new FabricItemSettings()));

    public static final Item IRON_CHISEL = registerItem("iron_chisel",
            new ToolItem(ModToolMaterial.CHISEL,new FabricItemSettings()));
    public static final Item SLIME_COLLAR = registerItem("slime_collar",
            new SlimeCollar(new FabricItemSettings().maxCount(1)));

    public static final Item SAPPHIRE_CROWN = registerItem("sapphire_crown",
            new ArmorItem(ModArmorMaterial.SAPPHIRE_CROWN,ArmorItem.Type.HELMET,new FabricItemSettings()));
    public static final Item BLUESLIME_BOOTS = registerItem("blueslime_boots",
            new BlueslimeBoots(ModArmorMaterial.BLUESLIME_BOOTS,ArmorItem.Type.BOOTS,new FabricItemSettings()));

    public static final Item SLIMESLING = registerItem("slimesling", new Slimesling(new FabricItemSettings()));
    public static final Item SLIME_BOOTS = registerItem("slime_boots",
            new SlimeBoots(ModArmorMaterial.SLIME_BOOTS,ArmorItem.Type.BOOTS,new FabricItemSettings()));

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(SAPPHIRE_SWORD);
        entries.add(SAPPHIRE_AXE);
    }
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(SAPPHIRE_AXE);
        entries.add(SAPPHIRE_HOE);
        entries.add(SAPPHIRE_SHOVEL);
        entries.add(SAPPHIRE_PICKAXE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Ohmygah.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Ohmygah.LOGGER.info("Registering items in " + Ohmygah.MOD_ID + " uwu");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
    }
}
