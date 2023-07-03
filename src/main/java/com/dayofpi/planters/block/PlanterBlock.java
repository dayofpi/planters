package com.dayofpi.planters.block;

import com.dayofpi.planters.PlantersMod;
import com.dayofpi.planters.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PlanterBlock extends BaseEntityBlock {
    private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    protected static final VoxelShape SHAPE_NS = Block.box(0.0D, 0.0D, 5.0D, 16.0D, 6.0D, 11.0D);
    protected static final VoxelShape SHAPE_WE = Block.box(5.0D, 0.0D, 0.0D, 11.0D, 6.0D, 16.0D);

    public PlanterBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HANGING, false));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof PlanterBlockEntity planterBlockEntity) {
            ItemStack itemStack = pPlayer.getItemInHand(pHand);
            boolean isPlaceable = itemStack.is(ModTags.PLANTER_PLANTABLES);
            ItemStack itemStack1 = planterBlockEntity.getPlant(Slot.RIGHT);
            ItemStack itemStack2 = planterBlockEntity.getPlant(Slot.MIDDLE);
            ItemStack itemStack3 = planterBlockEntity.getPlant(Slot.LEFT);
            if (isPlaceable && itemStack1.isEmpty()) {
                this.setPlant(planterBlockEntity, Slot.RIGHT, pPlayer, itemStack);
            } else if (isPlaceable && itemStack2.isEmpty()) {
                this.setPlant(planterBlockEntity, Slot.MIDDLE, pPlayer, itemStack);
            }  else if (isPlaceable && itemStack3.isEmpty()) {
                this.setPlant(planterBlockEntity, Slot.LEFT, pPlayer, itemStack);
            } else {
                if (!itemStack3.isEmpty()) {
                    Block.popResource(pLevel, pPos, itemStack3.copyWithCount(1));
                    this.setPlant(planterBlockEntity, Slot.LEFT, pPlayer, ItemStack.EMPTY);
                } else if (!itemStack2.isEmpty()) {
                    Block.popResource(pLevel, pPos, itemStack2.copyWithCount(1));
                    this.setPlant(planterBlockEntity, Slot.MIDDLE, pPlayer, ItemStack.EMPTY);
                } else if (!itemStack1.isEmpty()) {
                    Block.popResource(pLevel, pPos, itemStack1.copyWithCount(1));
                    this.setPlant(planterBlockEntity, Slot.RIGHT, pPlayer, ItemStack.EMPTY);
                } else return InteractionResult.PASS;
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    private void setPlant(PlanterBlockEntity planterBlockEntity, Slot slot, Player player, ItemStack itemStack) {
        Level level = planterBlockEntity.getLevel();
        if (level == null)
            return;
        if (!itemStack.isEmpty()) {
            planterBlockEntity.setPlant(slot, itemStack.copyWithCount(1));
            if (!player.getAbilities().instabuild)
                itemStack.shrink(1);
            level.playSound(null, planterBlockEntity.getBlockPos(), SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.2F);
        }
        level.gameEvent(GameEvent.BLOCK_CHANGE, planterBlockEntity.getBlockPos(), GameEvent.Context.of(player, planterBlockEntity.getBlockState()));
        planterBlockEntity.setPlant(slot, itemStack);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof PlanterBlockEntity) {
                Direction direction = pState.getValue(FACING);

                for (Slot slot : Slot.values()) {
                    ItemStack itemstack = ((PlanterBlockEntity) blockentity).getPlant(slot).copyWithCount(1);
                    if (!itemstack.isEmpty()) {
                        float f = 0.25F * (float) direction.getStepX();
                        float f1 = 0.25F * (float) direction.getStepZ();
                        ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + 0.5D + (double) f, (double) (pPos.getY() + 1), (double) pPos.getZ() + 0.5D + (double) f1, itemstack);
                        itementity.setDefaultPickUpDelay();
                        pLevel.addFreshEntity(itementity);
                    }
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return direction == Direction.NORTH || direction == Direction.SOUTH ? SHAPE_NS : SHAPE_WE;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pState.getValue(HANGING) && !pState.canSurvive(pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        for(Direction direction : pContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                    return blockstate.setValue(FACING, pContext.getHorizontalDirection().getOpposite());
                }
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        if  (pState.getValue(HANGING))
            return pLevel.getBlockState(pPos.above()).isFaceSturdy(pLevel, pPos.above(), Direction.DOWN);
        return pLevel.getBlockState(pPos.below()).isFaceSturdy(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, HANGING);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return state.getValue(HANGING) ? PlantersMod.HANGING_PLANTER : SoundType.DECORATED_POT;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PlanterBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.PLANTER.get(), PlanterBlockEntity::tick);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public enum Slot {
        RIGHT,
        MIDDLE,
        LEFT;
    }
}
