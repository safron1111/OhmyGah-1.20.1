package net.jeb.ohmygah.item;

import net.jeb.ohmygah.Ohmygah;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    SAPPHIRE_ARMOR("sapphire",25, new int[] {3,8,6,3},13,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1, 0.1f,
            () -> Ingredient.ofItems(ModItems.SAPPHIRE)),
    SAPPHIRE_CROWN("sapphire_crown",22, new int[] {3,1,1,1},13,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1, 0.0f,
            () -> Ingredient.ofItems(ModItems.SAPPHIRE, Items.GOLD_INGOT)),
    BLUESLIME_BOOTS("blueslime_boots",15, new int[] {2,4,3,2},6,
    SoundEvents.BLOCK_SLIME_BLOCK_STEP, 1, 0.0f,
            () -> Ingredient.ofItems(ModItems.BLUE_SLIMEBALL, Items.IRON_INGOT))
    ;

    private final String name;
    private final int durabilityMult;
    private final int[] protAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackRes;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11,16,15,13};

    ModArmorMaterial(String name, int durabilityMult, int[] protAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMult = durabilityMult;
        this.protAmounts = protAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackRes = knockbackRes;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMult;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Ohmygah.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackRes;
    }
}
