package net.jeb.ohmygah.item.advanced;

import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.bouncy_slime.FriendlyBouncySlimeEntity;
import net.jeb.ohmygah.entity.custom.friendly_slime.FriendlySlime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SlimeCollar extends Item {
    public SlimeCollar(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!entity.getWorld().isClient()) {
            if (entity instanceof BouncySlimeEntity bouncySlimeEntity){
                setAsFriendly(entity,entity.getWorld(),bouncySlimeEntity.getSize(),user);
                if (!user.isCreative()){
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            } else if (entity instanceof SlimeEntity slimeEntity) {
                setAsFriendly(entity,entity.getWorld(),slimeEntity.getSize(),user);
                if (!user.isCreative()){
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    public void setAsFriendly(LivingEntity entity, World world, int size, PlayerEntity user){
        if (entity.getType() == ModEntities.BOUNCYSLIME){
            FriendlyBouncySlimeEntity friendlyBouncySlimeEntity = new FriendlyBouncySlimeEntity(ModEntities.FRIENDLY_BOUNCYSLIME, world);
            friendlyBouncySlimeEntity.setSize(size,true);
            friendlyBouncySlimeEntity.setHealth(entity.getHealth());
            friendlyBouncySlimeEntity.setPos(entity.getX(),entity.getY(),entity.getZ());
            friendlyBouncySlimeEntity.setYaw(entity.getYaw());
            friendlyBouncySlimeEntity.setVelocity(entity.getVelocity());
            if (entity.hasCustomName()){
                friendlyBouncySlimeEntity.setCustomName(entity.getCustomName());
            } else {
                friendlyBouncySlimeEntity.setCustomName(Text.of("Bouncy Slime Pet"));
            }
            friendlyBouncySlimeEntity.setOwnerUuid(user.getUuid());
            friendlyBouncySlimeEntity.setPersistent();
            world.spawnEntity(friendlyBouncySlimeEntity);
            entity.discard();
        } else if (entity.getType() == EntityType.SLIME) {
            FriendlySlime friendlySlime = new FriendlySlime(ModEntities.FRIENDLY_SLIME, world);
            friendlySlime.setSize(size,true);
            friendlySlime.setHealth(entity.getHealth());
            friendlySlime.setPos(entity.getX(),entity.getY(),entity.getZ());
            friendlySlime.setYaw(entity.getYaw());
            friendlySlime.setVelocity(entity.getVelocity());
            if (entity.hasCustomName()){
                friendlySlime.setCustomName(entity.getCustomName());
            } else {
                friendlySlime.setCustomName(Text.of("Slime Pet"));
            }
            friendlySlime.setOwnerUuid(user.getUuid());
            friendlySlime.setPersistent();
            world.spawnEntity(friendlySlime);
            entity.discard();
        }
    }
}
