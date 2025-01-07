package net.jeb.ohmygah.entity.custom.plasticexplosive;

import net.jeb.ohmygah.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class PlasticExplosiveEntity extends TntEntity {
    public PlasticExplosiveEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
    }

    public PlasticExplosiveEntity(EntityType<PlasticExplosiveEntity> plasticExplosiveEntityEntityType, World world) {
        super(plasticExplosiveEntityEntityType,world);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("block_state")){
            this.saveNbt(nbt.getCompound("block_state"));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("fuse",(short)this.getFuse());
        nbt.put("block_state", NbtHelper.fromBlockState(ModBlocks.PLASTIC_EXPLOSIVE.getDefaultState()));
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }



        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            if (!this.getWorld().isClient) {
                int radius = 7;
                for (int vD = -radius; vD <= radius; vD++) {
                    for (int hD = -radius; hD <= radius; hD++) {
                        for (int zD = -radius; zD <= radius; zD++){
                            double dist = Math.sqrt(hD * hD + vD * vD + zD * zD);
                            if (dist <= radius) {
                                BlockPos pos = new BlockPos(hD,vD,zD);
                                BlockState state = this.getWorld().getBlockState(pos);
                                this.getWorld().breakBlock(pos,false);
                            }
                        }
                    }
                }
            }
            this.discard();
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
}
