package com.dayofpi.planters.block;

import com.dayofpi.planters.PlantersMod;
import com.dayofpi.planters.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PlantersMod.MOD_ID);

    public static final RegistryObject<Block> PLANTER = registerBlock("planter", () -> new PlanterBlock(BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> WHITE_PLANTER = registerBlock("white_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> LIGHT_GRAY_PLANTER = registerBlock("light_gray_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> GRAY_PLANTER = registerBlock("gray_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> BLACK_PLANTER = registerBlock("black_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> BROWN_PLANTER = registerBlock("brown_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> RED_PLANTER = registerBlock("red_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> ORANGE_PLANTER = registerBlock("orange_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> YELLOW_PLANTER = registerBlock("yellow_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> LIME_PLANTER = registerBlock("lime_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> GREEN_PLANTER = registerBlock("green_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> CYAN_PLANTER = registerBlock("cyan_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> LIGHT_BLUE_PLANTER = registerBlock("light_blue_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> BLUE_PLANTER = registerBlock("blue_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> PURPLE_PLANTER = registerBlock("purple_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> MAGENTA_PLANTER = registerBlock("magenta_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));
    public static final RegistryObject<Block> PINK_PLANTER = registerBlock("pink_planter", () -> new PlanterBlock(BlockBehaviour.Properties.copy(PLANTER.get())));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> returnValue = BLOCKS.register(name, block);
        registerBlockItem(name, returnValue);
        return returnValue;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
