package com.dayofpi.planters.datagen;

import com.dayofpi.planters.block.ModBlocks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLANTER.get(), 3).pattern("C C").pattern("B B").pattern("BBB").define('C', Items.CHAIN).define('B', Items.BRICK).unlockedBy("has_brick", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BRICK).build())).save(pWriter);
        planter(pWriter, ModBlocks.WHITE_PLANTER.get(), Items.WHITE_DYE);
        planter(pWriter, ModBlocks.LIGHT_GRAY_PLANTER.get(), Items.LIGHT_GRAY_DYE);
        planter(pWriter, ModBlocks.GRAY_PLANTER.get(), Items.GRAY_DYE);
        planter(pWriter, ModBlocks.BLACK_PLANTER.get(), Items.BLACK_DYE);
        planter(pWriter, ModBlocks.BROWN_PLANTER.get(), Items.BROWN_DYE);
        planter(pWriter, ModBlocks.RED_PLANTER.get(), Items.RED_DYE);
        planter(pWriter, ModBlocks.ORANGE_PLANTER.get(), Items.ORANGE_DYE);
        planter(pWriter, ModBlocks.YELLOW_PLANTER.get(), Items.YELLOW_DYE);
        planter(pWriter, ModBlocks.LIME_PLANTER.get(), Items.LIME_DYE);
        planter(pWriter, ModBlocks.GREEN_PLANTER.get(), Items.GREEN_DYE);
        planter(pWriter, ModBlocks.CYAN_PLANTER.get(), Items.CYAN_DYE);
        planter(pWriter, ModBlocks.LIGHT_BLUE_PLANTER.get(), Items.LIGHT_BLUE_DYE);
        planter(pWriter, ModBlocks.BLUE_PLANTER.get(), Items.BLUE_DYE);
        planter(pWriter, ModBlocks.PURPLE_PLANTER.get(), Items.PURPLE_DYE);
        planter(pWriter, ModBlocks.MAGENTA_PLANTER.get(), Items.MAGENTA_DYE);
        planter(pWriter, ModBlocks.PINK_PLANTER.get(), Items.PINK_DYE);
    }

    private static void planter(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike planter, ItemLike dye) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, planter).requires(ModBlocks.PLANTER.get()).requires(dye).group("dyed_planter").unlockedBy(getHasName(dye), has(dye)).save(finishedRecipeConsumer);
    }
}
