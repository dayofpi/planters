package com.dayofpi.planters.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class PlanterBlockEntity extends BlockEntity {
    ItemStack rightPlant = ItemStack.EMPTY;
    ItemStack middlePlant = ItemStack.EMPTY;
    ItemStack leftPlant = ItemStack.EMPTY;
    private final RandomSource random = RandomSource.create();
    private int spreadDelay = this.random.nextInt(6000) + 6000;

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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = super.getUpdateTag();
        compoundtag.put("RightPlant", this.rightPlant.save(new CompoundTag()));
        compoundtag.put("MiddlePlant", this.middlePlant.save(new CompoundTag()));
        compoundtag.put("LeftPlant", this.leftPlant.save(new CompoundTag()));
        return compoundtag;
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

    @Nullable
    private PlanterBlock.Slot getSpreadableSlot() {
        for (PlanterBlock.Slot slot : PlanterBlock.Slot.values()) {
            if (this.getPlant(slot).isEmpty())
                return slot;
        }
        return null;
    }

    private ItemStack getSpreadablePlant() {
        for (PlanterBlock.Slot slot : PlanterBlock.Slot.values()) {
            if (!this.getPlant(slot).isEmpty())
                return this.getPlant(slot);
        }
        return ItemStack.EMPTY;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PlanterBlockEntity planterBlockEntity) {
        PlanterBlock.Slot slot = planterBlockEntity.getSpreadableSlot();
        ItemStack itemStack = planterBlockEntity.getSpreadablePlant();
        if (slot != null && !itemStack.isEmpty() && --planterBlockEntity.spreadDelay <= 0) {
            planterBlockEntity.setPlant(slot, itemStack.copyWithCount(1));
            level.gameEvent(null, GameEvent.BLOCK_CHANGE, planterBlockEntity.worldPosition);
            level.levelEvent(2005, blockPos, 0);
            planterBlockEntity.spreadDelay = planterBlockEntity.random.nextInt(6000) + 6000;
        }
    }
}
