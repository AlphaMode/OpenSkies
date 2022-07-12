package me.alphamode.exnihiloabsentia.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import me.alphamode.exnihiloabsentia.ModBlocks;

public class ModBlockTagsGenerator extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        dataGenerator.addProvider(new OpenItemTagsGenerator(dataGenerator, this));
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

    public class OpenItemTagsGenerator extends FabricTagProvider.ItemTagProvider {

        public OpenItemTagsGenerator(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
            super(dataGenerator, blockTagProvider);
        }

        @Override
        protected void generateTags() {
            tag(ModItemTags.COMPOSTABLE)
                    .forceAddTag(ItemTags.SAPLINGS);
        }

        public FabricTagBuilder<Item> tag(TagKey<Item> tag) {
            return getOrCreateTagBuilder(tag);
        }
    }
}
