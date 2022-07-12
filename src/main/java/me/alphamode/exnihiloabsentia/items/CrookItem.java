package me.alphamode.exnihiloabsentia.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

public class CrookItem extends Item {
    public CrookItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockState) {
        return blockState.is(BlockTags.LEAVES);
    }
}
