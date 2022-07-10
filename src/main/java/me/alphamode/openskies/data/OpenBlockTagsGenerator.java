package me.alphamode.openskies.data;

import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.OpenItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class OpenBlockTagsGenerator extends FabricTagProvider.BlockTagProvider {
    public OpenBlockTagsGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        dataGenerator.addProvider(new OpenItemTagsGenerator(dataGenerator, this));
    }

    @Override
    protected void generateTags() {
        tag(BlockTags.LEAVES)
                .add(OpenBlocks.INFESTED_LEAVES);
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(OpenBlocks.INFESTED_LEAVES);
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(OpenBlocks.SIEVE)
                .add(OpenBlocks.WOOD_BARREL);
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(OpenBlocks.STONE_BARREL);
    }

    public class OpenItemTagsGenerator extends FabricTagProvider.ItemTagProvider {

        public OpenItemTagsGenerator(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
            super(dataGenerator, blockTagProvider);
        }

        @Override
        protected void generateTags() {
            tag(OpenItemTags.COMPOSTABLE)
                    .forceAddTag(ItemTags.SAPLINGS);
        }

        public FabricTagBuilder<Item> tag(TagKey<Item> tag) {
            return getOrCreateTagBuilder(tag);
        }
    }
}
