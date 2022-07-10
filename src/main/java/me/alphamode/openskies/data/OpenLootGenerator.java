package me.alphamode.openskies.data;

import me.alphamode.openskies.OpenBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class OpenLootGenerator extends FabricBlockLootTableProvider {
    protected OpenLootGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        dropSelf(OpenBlocks.STONE_BARREL);
        dropSelf(OpenBlocks.WOOD_BARREL);
        dropSelf(OpenBlocks.SIEVE);
    }
}
