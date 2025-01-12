package net.jeb.ohmygah.item.advanced;

import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.blueslimeball_entity.BlueSlimeballEntity;
import net.jeb.ohmygah.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class Slimesling extends RangedWeaponItem{
    public static final Predicate<ItemStack> SLIMESLING_PROJECTILES = stack -> stack.isOf(ModItems.BLUE_SLIMEBALL);

    public Slimesling(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            float f = getPullProgress(i);
            if (!((double)f < 0.1)) {
                if (!world.isClient) {
                    playerEntity.addVelocity(playerEntity.getRotationVec(1).normalize().multiply(f*1.75));
                    playerEntity.velocityModified = true;
                }

                world.playSound(
                        null,
                        playerEntity.getX(),
                        playerEntity.getY(),
                        playerEntity.getZ(),
                        SoundEvents.ENTITY_SLIME_JUMP,
                        SoundCategory.PLAYERS,
                        f*1.5f,
                        1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                );

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 8000;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return SLIMESLING_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 8;
    }
}
