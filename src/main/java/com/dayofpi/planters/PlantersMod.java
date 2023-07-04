package com.dayofpi.planters;

import com.dayofpi.planters.block.ModBlockEntities;
import com.dayofpi.planters.block.ModBlocks;
import com.dayofpi.planters.block.PlanterRenderer;
import com.dayofpi.planters.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PlantersMod.MOD_ID)
public class PlantersMod {
    public static final String MOD_ID = "planters";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final SoundType HANGING_PLANTER = new SoundType(1.0F, 1.0F, SoundEvents.DECORATED_POT_BREAK, SoundEvents.DECORATED_POT_STEP, SoundEvents.CHAIN_PLACE, SoundEvents.DECORATED_POT_HIT, SoundEvents.DECORATED_POT_FALL);

    public PlantersMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS || event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            event.accept(ModBlocks.PLANTER);
            event.accept(ModBlocks.WHITE_PLANTER);
            event.accept(ModBlocks.LIGHT_GRAY_PLANTER);
            event.accept(ModBlocks.GRAY_PLANTER);
            event.accept(ModBlocks.BLACK_PLANTER);
            event.accept(ModBlocks.BROWN_PLANTER);
            event.accept(ModBlocks.RED_PLANTER);
            event.accept(ModBlocks.ORANGE_PLANTER);
            event.accept(ModBlocks.YELLOW_PLANTER);
            event.accept(ModBlocks.LIME_PLANTER);
            event.accept(ModBlocks.GREEN_PLANTER);
            event.accept(ModBlocks.CYAN_PLANTER);
            event.accept(ModBlocks.LIGHT_BLUE_PLANTER);
            event.accept(ModBlocks.BLUE_PLANTER);
            event.accept(ModBlocks.PURPLE_PLANTER);
            event.accept(ModBlocks.MAGENTA_PLANTER);
            event.accept(ModBlocks.PINK_PLANTER);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.PLANTER.get(), PlanterRenderer::new);
        }
    }
}
