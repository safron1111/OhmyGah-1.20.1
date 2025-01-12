package net.jeb.ohmygah.item.advanced;

import net.jeb.ohmygah.Ohmygah;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SlimeBoots extends ArmorItem {

    public SlimeBoots(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    boolean falling = false;
    float veloY = 0.0f;
    float veloX = 0.0f;
    float veloZ = 0.0f;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if (!world.isClient() && slot == 0) {
            entity.fallDistance = entity.getSafeFallDistance()-1;
            if (!entity.isOnGround()) {
                falling = true;
                veloY = (float)entity.getVelocity().y;
                veloX = (float)entity.getVelocity().x;
                veloZ = (float)entity.getVelocity().z;
            } else if (entity.isOnGround() && falling == true) {
                falling = false;
                if (entity.isOnGround()) {
                    bounce(entity,stack);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public void bounce(Entity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity playerEntity){
            if (!playerEntity.isCreative()){
                stack.setDamage(stack.getDamage() + 1);
            }
        } else {
            stack.setDamage(stack.getDamage() + 1);
        }
        Ohmygah.LOGGER.info(String.valueOf(Math.abs(veloY)));
        if (Math.abs(veloY) > 0.55) {
            entity.addVelocity(veloX/1.1f,-veloY/1.1f,veloZ/1.1f);
            veloY = 0.0f;
            entity.velocityModified = true;
        }
    }
}
