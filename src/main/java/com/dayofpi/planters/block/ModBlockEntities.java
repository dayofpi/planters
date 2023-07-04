package com.dayofpi.planters.block;

import com.dayofpi.planters.PlantersMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PlantersMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<PlanterBlockEntity>> PLANTER = BLOCK_ENTITIES.register("planter", () -> BlockEntityType.Builder.of(PlanterBlockEntity::new, ModBlocks.PLANTER.get(), ModBlocks.WHITE_PLANTER.get(), ModBlocks.LIGHT_GRAY_PLANTER.get(), ModBlocks.GRAY_PLANTER.get(), ModBlocks.BLACK_PLANTER.get(), ModBlocks.BROWN_PLANTER.get(), ModBlocks.RED_PLANTER.get(), ModBlocks.ORANGE_PLANTER.get(), ModBlocks.YELLOW_PLANTER.get(), ModBlocks.LIME_PLANTER.get(), ModBlocks.GREEN_PLANTER.get(), ModBlocks.CYAN_PLANTER.get(), ModBlocks.LIGHT_BLUE_PLANTER.get(), ModBlocks.BLUE_PLANTER.get(), ModBlocks.PURPLE_PLANTER.get(), ModBlocks.MAGENTA_PLANTER.get(), ModBlocks.PINK_PLANTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
