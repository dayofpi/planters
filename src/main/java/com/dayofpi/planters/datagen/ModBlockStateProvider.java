package com.dayofpi.planters.datagen;

import com.dayofpi.planters.PlantersMod;
import com.dayofpi.planters.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PlantersMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockItem(ModBlocks.PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/planter")));
        simpleBlockItem(ModBlocks.WHITE_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/white_planter")));
        simpleBlockItem(ModBlocks.LIGHT_GRAY_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/light_gray_planter")));
        simpleBlockItem(ModBlocks.GRAY_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/gray_planter")));
        simpleBlockItem(ModBlocks.BLACK_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/black_planter")));
        simpleBlockItem(ModBlocks.BROWN_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/brown_planter")));
        simpleBlockItem(ModBlocks.RED_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/red_planter")));
        simpleBlockItem(ModBlocks.ORANGE_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/orange_planter")));
        simpleBlockItem(ModBlocks.YELLOW_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/yellow_planter")));
        simpleBlockItem(ModBlocks.LIME_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/lime_planter")));
        simpleBlockItem(ModBlocks.GREEN_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/green_planter")));
        simpleBlockItem(ModBlocks.CYAN_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/cyan_planter")));
        simpleBlockItem(ModBlocks.LIGHT_BLUE_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/light_blue_planter")));
        simpleBlockItem(ModBlocks.BLUE_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/blue_planter")));
        simpleBlockItem(ModBlocks.PURPLE_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/purple_planter")));
        simpleBlockItem(ModBlocks.MAGENTA_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/magenta_planter")));
        simpleBlockItem(ModBlocks.PINK_PLANTER.get(), this.models().getExistingFile(new ResourceLocation(PlantersMod.MOD_ID, "block/pink_planter")));
    }
}
