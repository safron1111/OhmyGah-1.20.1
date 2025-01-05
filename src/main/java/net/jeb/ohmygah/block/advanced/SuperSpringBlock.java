package net.jeb.ohmygah.block.advanced;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuperSpringBlock extends Block {
    public SuperSpringBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity.isDescending()) {
            int safeFallDistance = entity.getSafeFallDistance();
            entity.fallDistance = safeFallDistance;
            super.onSteppedOn(world, pos, state, entity);
        } else {
            entity.addVelocity(0.0f, 3.0f, 0.0f);
            int safeFallDistance = entity.getSafeFallDistance();
            entity.fallDistance = safeFallDistance;
            super.onSteppedOn(world, pos, state, entity);
        }
    }
}
