package me.alphamode.exnihiloabsentia.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import org.jetbrains.annotations.Nullable;

import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.ModFluids;
import me.alphamode.exnihiloabsentia.ModItems;

public class ModBlockTagsGenerator extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        dataGenerator.addProvider(new ModItemTagsGenerator(dataGenerator, this));
        dataGenerator.addProvider(new ModFluidTagsGenerator(dataGenerator));
    }

    @Override
    protected void generateTags() {
        tag(BlockTags.LEAVES)
                .add(ModBlocks.INFESTED_LEAVES);
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.INFESTED_LEAVES);
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SIEVE)
                .add(ModBlocks.WOOD_BARREL);
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STONE_BARREL);
    }

    public class ModItemTagsGenerator extends FabricTagProvider.ItemTagProvider {
        public ModItemTagsGenerator(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
            super(dataGenerator, blockTagProvider);
        }

        @Override
        protected void generateTags() {
            tag(ModItemTags.COMPOSTABLE)
                    .forceAddTag(ItemTags.SAPLINGS);

            tag(ConventionalItemTags.WATER_BUCKETS)
                    .add(ModItems.WITCHWATER_BUCKET);
        }

        public FabricTagBuilder<Item> tag(TagKey<Item> tag) {
            return getOrCreateTagBuilder(tag);
        }
    }

    public class ModFluidTagsGenerator extends FabricTagProvider.FluidTagProvider {
        public ModFluidTagsGenerator(FabricDataGenerator dataGenerator) { 
            super(dataGenerator);
        }

        @Override
        protected void generateTags() {
            tag(FluidTags.WATER)
                    .add(ModFluids.WITCHWATER)
                    .add(ModFluids.FLOWING_WITCHWATER);
        }

        @Override
        protected TagAppender<Fluid> tag(TagKey<Fluid> tag) {
            return getOrCreateTagBuilder(tag);
        }
    }
}
