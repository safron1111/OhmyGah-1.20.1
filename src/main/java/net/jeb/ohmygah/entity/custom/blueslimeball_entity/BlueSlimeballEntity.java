package net.jeb.ohmygah.entity.custom.blueslimeball_entity;

import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.item.ModItems;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlueSlimeballEntity extends ThrownItemEntity {
    public BlueSlimeballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlueSlimeballEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.BLUE_SLIMEBALL_ENTITY,livingEntity,world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BLUE_SLIMEBALL;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()){
            if (Math.abs(this.getVelocity().x)+Math.abs(this.getVelocity().y)+Math.abs(this.getVelocity().z) < 0.135){
                dropStack(new ItemStack(ModItems.BLUE_SLIMEBALL,1));
                this.discard();
            }

            this.getWorld().sendEntityStatus(this,(byte) 3);

            float velocityDivider = 1.3f;

            if ((blockHitResult.getSide() == Direction.UP) || (blockHitResult.getSide() == Direction.DOWN)) {
                this.setVelocity(this.getVelocity().x/velocityDivider,-this.getVelocity().y/velocityDivider,this.getVelocity().z/velocityDivider);
            } else if ((blockHitResult.getSide() == Direction.WEST) || (blockHitResult.getSide() == Direction.EAST)) {
                this.setVelocity(-this.getVelocity().x/velocityDivider,this.getVelocity().y/velocityDivider,this.getVelocity().z/velocityDivider);
            } else if ((blockHitResult.getSide() == Direction.NORTH) || (blockHitResult.getSide() == Direction.SOUTH)) {
                this.setVelocity(this.getVelocity().x/velocityDivider,this.getVelocity().y/velocityDivider,-this.getVelocity().z/velocityDivider);
            }
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient()){
            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this,this.getOwner()),1.3f*((float)Math.abs(this.getVelocity().x)+(float)Math.abs(this.getVelocity().y)+(float)Math.abs(this.getVelocity().z)));
            Ohmygah.LOGGER.info(String.valueOf(1.3f*((float)Math.abs(this.getVelocity().x)+(float)Math.abs(this.getVelocity().y)+(float)Math.abs(this.getVelocity().z))));
            dropStack(new ItemStack(ModItems.BLUE_SLIMEBALL,1));
            this.discard();
        }
        super.onEntityHit(entityHitResult);
    }
}
