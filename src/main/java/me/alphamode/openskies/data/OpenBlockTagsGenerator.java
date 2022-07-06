package me.alphamode.openskies.data;

import me.alphamode.openskies.OpenBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
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
    }

    public class OpenItemTagsGenerator extends FabricTagProvider.ItemTagProvider {

        public OpenItemTagsGenerator(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
            super(dataGenerator, blockTagProvider);
        }

        @Override
        protected void generateTags() {
            tag(OpenItemTags.COMPOSTABLE)
                    .addTag(ItemTags.SAPLINGS);
        }
    }
}
