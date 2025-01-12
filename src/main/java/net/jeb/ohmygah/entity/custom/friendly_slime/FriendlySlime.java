package net.jeb.ohmygah.entity.custom.friendly_slime;

import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.bouncy_slime.FriendlyBouncySlimeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class FriendlySlime extends SlimeEntity {
    public FriendlySlime(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createSlimeAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    @Override
    protected boolean canAttack() {
        return false;
    }

    /* i hate you */

    private static final TrackedData<Optional<UUID>> OWNER_UUID = DataTracker.registerData(FriendlySlime.class,TrackedDataHandlerRegistry.OPTIONAL_UUID);
    private static final TrackedData<Boolean> STAYING_TRACKED = DataTracker.registerData(FriendlySlime.class,TrackedDataHandlerRegistry.BOOLEAN);

    public void tick() {
        super.tick();
        if (!getWorld().isClient()){
            if (this.getOwnerUUID() != null) {
                PlayerEntity player = getWorld().getPlayerByUuid(this.getOwnerUUID());
                if (player != null){
                    if (player.squaredDistanceTo(this) > 48){
                        BlockPos blockPos = new BlockPos(player.getBlockPos().getX()+Random.create().nextBetween(-5,5),player.getBlockPos().getY()+Random.create().nextBetween(-2,2),player.getBlockPos().getZ()+Random.create().nextBetween(-5,5));
                        if (getWorld().getBlockState(blockPos).isSolidBlock(getWorld(),blockPos)) {
                            BlockPos newPos = new BlockPos(blockPos.getX(),blockPos.getY()+1,blockPos.getZ());
                            if (!(getWorld().getBlockState(newPos).isSolidBlock(getWorld(),newPos))) {
                                this.teleport(newPos.getX(),newPos.getY(),newPos.getZ(),false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(STAYING_TRACKED,nbt.getBoolean("isSitting"));
        this.dataTracker.set(OWNER_UUID,Optional.ofNullable(nbt.getUuid("Owner")));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isSitting",this.dataTracker.get(STAYING_TRACKED));
        if (this.getOwnerUUID() != null) {
            nbt.putUuid("Owner",this.getOwnerUUID());
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
            if (!isStaying()) {
                setStayingTracked(true);
                return ActionResult.SUCCESS;
            } else if (isStaying()) {
                setStayingTracked(false);
                return ActionResult.SUCCESS;
            } else {
                setStayingTracked(false);
                return ActionResult.FAIL;
            }
    }

    public void setStayingTracked(boolean staying){
        this.dataTracker.set(STAYING_TRACKED,staying);
    }

    public void setOwnerUuid(UUID uuid){
        this.dataTracker.set(OWNER_UUID,Optional.ofNullable(uuid));
    }

    public boolean isStaying(){
        return this.dataTracker.get(STAYING_TRACKED);
    }

    public UUID getOwnerUUID(){
        return this.dataTracker.get(OWNER_UUID).orElse(null);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(STAYING_TRACKED,false);
        this.dataTracker.startTracking(OWNER_UUID,Optional.empty());
    }
}
