package com.dayofpi.planters.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlanterBlockEntity extends BlockEntity {
    ItemStack rightPlant = ItemStack.EMPTY;
    ItemStack middlePlant = ItemStack.EMPTY;
    ItemStack leftPlant = ItemStack.EMPTY;

    public PlanterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PLANTER.get(), pPos, pBlockState);
    }

    public ItemStack getPlant(PlanterBlock.Slot slot) {
        return switch (slot) {
            case RIGHT -> this.rightPlant;
            case MIDDLE -> this.middlePlant;
            case LEFT -> this.leftPlant;
        };
    }

    public void setPlant(PlanterBlock.Slot slot, ItemStack itemStack) {
        switch (slot) {
            case RIGHT -> this.rightPlant = itemStack;
            case MIDDLE -> this.middlePlant = itemStack;
            case LEFT -> this.leftPlant = itemStack;
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("RightPlant", 10)) {
            this.setPlant(PlanterBlock.Slot.RIGHT, ItemStack.of(pTag.getCompound("RightPlant")));
        }
        if (pTag.contains("MiddlePlant", 10)) {
            this.setPlant(PlanterBlock.Slot.MIDDLE, ItemStack.of(pTag.getCompound("MiddlePlant")));
        }
        if (pTag.contains("LeftPlant", 10)) {
            this.setPlant(PlanterBlock.Slot.LEFT, ItemStack.of(pTag.getCompound("LeftPlant")));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.getPlant(PlanterBlock.Slot.RIGHT).isEmpty()) {
            pTag.put("RightPlant", this.getPlant(PlanterBlock.Slot.RIGHT).save(new CompoundTag()));
        }
        if (!this.getPlant(PlanterBlock.Slot.MIDDLE).isEmpty()) {
            pTag.put("MiddlePlant", this.getPlant(PlanterBlock.Slot.MIDDLE).save(new CompoundTag()));
        }
        if (!this.getPlant(PlanterBlock.Slot.LEFT).isEmpty()) {
            pTag.put("LeftPlant", this.getPlant(PlanterBlock.Slot.LEFT).save(new CompoundTag()));
        }
    }
}
