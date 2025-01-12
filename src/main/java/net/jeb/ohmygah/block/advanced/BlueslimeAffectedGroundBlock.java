package net.jeb.ohmygah.block.advanced;

import net.jeb.ohmygah.Ohmygah;
import net.jeb.ohmygah.entity.custom.ModEntities;
import net.jeb.ohmygah.entity.custom.bouncy_slime.BouncySlimeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class BlueslimeAffectedGroundBlock extends Block {
    public BlueslimeAffectedGroundBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        BlockPos newPos = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
        if (!world.isClient) {
            if (!world.getBlockState(newPos).isSolidBlock(world,newPos)){
                if (Random.create().nextFloat() < world.getMoonSize() && this.playerNearby(world,pos,16)){
                    BouncySlimeEntity slimeEntity = new BouncySlimeEntity(ModEntities.BOUNCYSLIME,world);
                    slimeEntity.setPos(newPos.getX(),newPos.getY()+0.1,newPos.getZ());
                    slimeEntity.setSize(Random.create().nextInt(2)+Random.create().nextInt(2),true);
                    world.spawnEntity(slimeEntity);
                }
            }
        }
    }

    public boolean playerNearby(ServerWorld world, BlockPos pos, double range){
        for (PlayerEntity playerEntity : world.getPlayers()){
            if (!playerEntity.isSpectator()){
                double dist = playerEntity.squaredDistanceTo(pos.getX(),pos.getY(),pos.getZ());
                if (dist <= range){
                    return true;
                }
            }
        }
        return false;
    }
}
