package me.alphamode.openskies.items;

import me.alphamode.openskies.OpenBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class SilkworkItem extends Item {
    public SilkworkItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        BlockState state = useOnContext.getLevel().getBlockState(useOnContext.getClickedPos());
        if (state.is(BlockTags.LEAVES)) {
            useOnContext.getLevel().setBlock(useOnContext.getClickedPos(), OpenBlocks.INFESTED_LEAVES.defaultBlockState(), 3);
            useOnContext.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
