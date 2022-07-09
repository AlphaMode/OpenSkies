package me.alphamode.openskies.items;

import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.blocks.InfestedLeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class SilkWormItem extends Item {
    public SilkWormItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        BlockState state = useOnContext.getLevel().getBlockState(useOnContext.getClickedPos());
        if (state.is(BlockTags.LEAVES) && !state.is(OpenBlocks.INFESTED_LEAVES)) {
            InfestedLeavesBlock.infestLeafBlock(useOnContext.getLevel(), useOnContext.getClickedPos());
            useOnContext.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
