package me.alphamode.exnihiloabsentia.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSuitableFor(ItemStack stack, BlockState state) {
        return super.isSuitableFor(stack, state);
    }
}
