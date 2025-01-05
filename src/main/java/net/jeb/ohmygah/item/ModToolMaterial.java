package net.jeb.ohmygah.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.jeb.ohmygah.Ohmygah;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    RUBY(MiningLevels.DIAMOND,7.5f,1478,15,1.0f, () -> Ingredient.ofItems(ModItems.SAPPHIRE))

    ;

    private final int miningLevel;
    private final float miningSpeed;
    private final int toolDurability;
    private final int toolEnchantability;
    private final float toolAttackDamage;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, float miningSpeed, int toolDurability, int toolEnchantability, float toolAttackDamage, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.miningSpeed = miningSpeed;
        this.toolDurability = toolDurability;
        this.toolEnchantability = toolEnchantability;
        this.toolAttackDamage = toolAttackDamage;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.toolDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.toolAttackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.toolEnchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
