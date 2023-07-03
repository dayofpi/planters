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
    }
}
