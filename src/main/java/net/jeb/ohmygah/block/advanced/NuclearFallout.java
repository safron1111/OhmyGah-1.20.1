package net.jeb.ohmygah.block.advanced;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class NuclearFallout extends SnowBlock {
    public NuclearFallout(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (Math.random() < 0.2) {
            world.playSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS,0.7f,1f,true);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,400,1,false,true,true));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,120,1,false,true,true));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,100,1,false,true,true));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
