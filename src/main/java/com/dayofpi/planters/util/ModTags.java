package com.dayofpi.planters.util;

import com.dayofpi.planters.PlantersMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> PLANTER_PLANTABLES = ItemTags.create(new ResourceLocation(PlantersMod.MOD_ID, "planter_plantables"));
}
