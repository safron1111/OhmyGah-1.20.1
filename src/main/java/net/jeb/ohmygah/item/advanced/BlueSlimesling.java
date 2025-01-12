package net.jeb.ohmygah.item.advanced;

import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.blueslimeball_entity.BlueSlimeballEntity;
import net.jeb.ohmygah.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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

public class BlueSlimesling extends RangedWeaponItem {
    public static final Predicate<ItemStack> SLIMESLING_PROJECTILES = stack -> stack.isOf(ModItems.BLUE_SLIMEBALL);

    public BlueSlimesling(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode;
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(ModItems.BLUE_SLIMEBALL);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(ModItems.BLUE_SLIMEBALL);
                    if (itemStack.getCount() > 0) {
                        if (!world.isClient) {
                            BlueSlimeballItem slimeballItem = (BlueSlimeballItem) (itemStack.getItem() instanceof BlueSlimeballItem ? itemStack.getItem() : ModItems.BLUE_SLIMEBALL);
                            if (!playerEntity.isCreative()){
                                itemStack.decrement(1);
                            }
                            BlueSlimeballEntity slimeballEntity = new BlueSlimeballEntity(playerEntity,world);
                            slimeballEntity.setItem(new ItemStack(slimeballItem));
                            slimeballEntity.setVelocity(playerEntity,playerEntity.getPitch(),playerEntity.getYaw(),0.0f,2.0F*f,1.0F);
                            world.spawnEntity(slimeballEntity);
                        }

                        world.playSound(
                                null,
                                playerEntity.getX(),
                                playerEntity.getY(),
                                playerEntity.getZ(),
                                SoundEvents.ENTITY_SLIME_JUMP,
                                SoundCategory.PLAYERS,
                                1.0F,
                                1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                        );

                        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    }
                }
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
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
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
