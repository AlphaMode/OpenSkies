package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import me.alphamode.exnihiloabsentia.blocks.BarrelBlock;
import me.alphamode.exnihiloabsentia.blocks.InfestedLeavesBlock;
import me.alphamode.exnihiloabsentia.blocks.SieveBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;

public class ModBlocks implements BlockRegistryContainer {
    public static final InfestedLeavesBlock INFESTED_LEAVES = new InfestedLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

    public static final SieveBlock SIEVE = new SieveBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD));
    public static final BarrelBlock WOOD_BARREL = new BarrelBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD));
    public static final BarrelBlock STONE_BARREL = new BarrelBlock(FabricBlockSettings.copy(Blocks.STONE));
    @NoBlockItem
    public static final Block WITCHWATER = new LiquidBlock(ModFluids.WITCHWATER, FabricBlockSettings.copy(Blocks.WATER));

    // COMPRESSED BLOCKS

    public static final Block COMPRESSED_SAND = new Block(FabricBlockSettings.copy(Blocks.SAND));

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Properties().tab(ExNihiloAbsentia.TAB));
    }
}
