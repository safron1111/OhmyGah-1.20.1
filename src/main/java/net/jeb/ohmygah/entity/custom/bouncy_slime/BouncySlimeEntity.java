package net.jeb.ohmygah.entity.custom.bouncy_slime;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BouncySlimeEntity extends SlimeEntity {
    private double fallback;

    public BouncySlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ParticleEffect getParticles() {
        return ParticleTypes.ITEM_SLIME;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);
        if (this.canAttack()) {
            this.damage(player);

            double dx = player.getX() - this.getX();
            double dz = player.getZ() - this.getZ();

            double dist = Math.sqrt(dx*dx+dz*dz);
            if (dist != 0){
                dx /= dist;
                dz /= dist;
            }

            double knockbackStrength = this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK) * this.getSize() * 0.05;
            player.addVelocity(dx*knockbackStrength,0.06f*this.getSize(),dz*knockbackStrength);
        }
    }

    public static DefaultAttributeContainer.Builder createBouncySlimeAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,1.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_ARMOR, 3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    @Override
    public void setSize(int size, boolean heal) {
        super.setSize(size, heal);
        int i = MathHelper.clamp(size, 1, 127);

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.0*i);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue((double)(0.7F + 0.1F * (float)i));
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue((double) (i * 2));
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue((double)(i));
        if (heal) {
            this.setHealth(this.getMaxHealth());
        }
    }


}
