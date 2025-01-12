package net.jeb.ohmygah.entity.custom.nuclearbomb;

import com.google.common.collect.ImmutableMap;
import net.jeb.ohmygah.block.ModBlocks;
import net.jeb.ohmygah.block.advanced.PlasticExplosive;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.entity.custom.plasticexplosive.PlasticExplosiveEntity;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BlockColumnFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class NuclearBombEntity extends Entity implements Ownable {
    private static final TrackedData<Integer> FUSE = DataTracker.registerData(NuclearBombEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int DEFAULT_FUSE = 240;
    @Nullable
    private LivingEntity causingEntity;

    public NuclearBombEntity(EntityType<? extends NuclearBombEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public NuclearBombEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntities.NUCLEAR_BOMB_ENTITY, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setVelocity(-Math.sin(d) * 0.02, 0.2F, -Math.cos(d) * 0.02);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 240);
    }

    @Override
    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
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
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        boolean canCheckForTnt = true;
        int f = 60;
        getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, f/9, 1);
        for (int xD = -f; xD <= f+1; xD++){
            for (int yD = -f; yD <= f+1; yD++){
                for (int zD = -f; zD <= f+1; zD++){
                    double dist = Math.sqrt(xD * xD + yD * yD + zD * zD);
                    if (dist <= f) {
                        for (float intensity = f - (float) dist; intensity >= 0; intensity = intensity - 0.6f){
                            if (Math.random() < 0.65f) {
                                BlockHitResult hitResult = getWorld().raycast(new RaycastContext(this.getPos(),new Vec3d(this.getX()+xD,this.getY()+yD,this.getZ()+zD), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.WATER,this));
                                if (getWorld().getBlockState(hitResult.getBlockPos()).getBlock().getBlastResistance()/600 < 1) {
                                    checkForTnt(hitResult.getBlockPos(),getWorld(),causingEntity);
                                    getWorld().setBlockState(hitResult.getBlockPos(), Blocks.AIR.getDefaultState());
                                    if (getWorld().getBlockState(hitResult.getBlockPos()).isAir()) {
                                        BlockPos newBlockPos = hitResult.getBlockPos().add(0,-1,0);
                                        if (!getWorld().getBlockState(newBlockPos).isAir()) {
                                            if (Math.random() < 0.2/intensity) {
                                                if (Math.random() < 0.3) {
                                                    double rng = Math.random();
                                                    if (rng > 0.6) {
                                                        getWorld().setBlockState(hitResult.getBlockPos(),ModBlocks.NUCLEAR_FALLOUT.getDefaultState().with(Properties.LAYERS,4));
                                                    } else if (rng < 0.6) {
                                                        getWorld().setBlockState(hitResult.getBlockPos(),ModBlocks.NUCLEAR_FALLOUT.getDefaultState().with(Properties.LAYERS,3));
                                                    }
                                                } else {
                                                    getWorld().setBlockState(hitResult.getBlockPos(), FireBlock.getState(getWorld(),hitResult.getBlockPos()));
                                                }
                                            }
                                        }
                                    }
                                }
                                Box box = new Box(hitResult.getPos().x-1,hitResult.getPos().y-1,hitResult.getPos().z-1,hitResult.getPos().x+1,hitResult.getPos().y+1,hitResult.getPos().z+1);
                                List<Entity> list = getWorld().getOtherEntities(null,box);
                                if (!list.isEmpty()){
                                    for (int i = 0; i <= list.size()-1; i++){
                                        Entity entity = list.get(i);
                                        if (entity instanceof LivingEntity) {
                                            if (entity instanceof PlayerEntity player) {
                                                if (!player.isCreative()){
                                                    entity.damage(this.getDamageSources().explosion(this,this),(f - (float)dist)*3);
                                                }
                                            }else {
                                                entity.damage(this.getDamageSources().explosion(this,this),(f - (float)dist)*3);
                                            }
                                        }
                                        double dx = entity.getX() - this.getX();
                                        double dz = entity.getZ() - this.getZ();
                                        double dy = entity.getY() - this.getY();

                                        double dist2 = Math.sqrt(dx*dx+dz*dz+dy*dy);
                                        if (dist2 != 0){
                                            dx /= dist2;
                                            dz /= dist2;
                                            dy /= dist2;
                                        }

                                        double knockbackStrength = 0.08;

                                        entity.addVelocity(dx*knockbackStrength,dy*knockbackStrength,dz*knockbackStrength);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkForTnt(BlockPos blockPos, World world, LivingEntity entity){
        if (getWorld().getBlockState(blockPos) == Blocks.TNT.getDefaultState()) {
            TntEntity tntEntity = new TntEntity(world,blockPos.getX()+0.5,blockPos.getY()+0.5,blockPos.getZ()+0.5,entity);
            world.spawnEntity(tntEntity);
            tntEntity.setFuse((short)(world.random.nextInt(80 / 4) + 80 / 8));
            return true;
        } else if (getWorld().getBlockState(blockPos) == ModBlocks.NUCLEAR_BOMB.getDefaultState()){
            NuclearBombEntity nuclearBombEntity = new NuclearBombEntity(world,blockPos.getX()+0.5,blockPos.getY()+0.5,blockPos.getZ()+0.5,entity);
            world.spawnEntity(nuclearBombEntity);
            nuclearBombEntity.setFuse((short)(world.random.nextInt(120 / 4) + 120 / 8));
            return true;
        } else if (getWorld().getBlockState(blockPos) == ModBlocks.PLASTIC_EXPLOSIVE.getDefaultState()) {
            PlasticExplosiveEntity plasticExplosiveEntity = new PlasticExplosiveEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, entity);
            world.spawnEntity(plasticExplosiveEntity);
            plasticExplosiveEntity.setFuse((short) (world.random.nextInt(60 / 4) + 60 / 8));
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("Fuse", (short)this.getFuse());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    @Override
    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.dataTracker.get(FUSE);
    }
}
