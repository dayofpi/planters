package com.dayofpi.planters.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlanterBlockEntity extends BlockEntity {
    public PlanterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PLANTER.get(), pPos, pBlockState);
    }
}
