package me.alphamode.openskies;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import me.alphamode.openskies.blocks.BarrelBlock;
import me.alphamode.openskies.blocks.InfestedLeavesBlock;
import me.alphamode.openskies.blocks.SieveBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class OpenBlocks implements BlockRegistryContainer {
    public static final InfestedLeavesBlock INFESTED_LEAVES = new InfestedLeavesBlock(FabricBlockSettings.of(Material.LEAVES).noOcclusion());
    public static final SieveBlock SIEVE = new SieveBlock(FabricBlockSettings.of(Material.WOOD));
    public static final BarrelBlock WOOD_BARREL = new BarrelBlock(FabricBlockSettings.of(Material.WOOD));
    public static final BarrelBlock STONE_BARREL = new BarrelBlock(FabricBlockSettings.of(Material.STONE));

    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Properties().tab(OpenSkies.TAB));
    }
}
