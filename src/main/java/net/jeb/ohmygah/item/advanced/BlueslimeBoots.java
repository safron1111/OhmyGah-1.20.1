package net.jeb.ohmygah.item.advanced;

import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class BlueslimeBoots extends ArmorItem {

    public BlueslimeBoots(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    boolean decending = false;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if (!world.isClient() && slot == 0) {

            entity.fallDistance = entity.getSafeFallDistance();
            if (entity.isDescending()) {
                decending = true;
            } else if (!entity.isDescending() && decending == true) {
                decending = false;
                if (entity.isOnGround()) {
                    bounce(entity,stack);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public void bounce(Entity entity, ItemStack stack) {
        stack.setDamage(stack.getDamage() + 1);
        entity.addVelocity(0.0f,1.0f,0.0f);
        entity.velocityModified = true;
    }
}
