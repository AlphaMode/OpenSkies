package me.alphamode.exnihiloabsentia.items;

import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.blocks.InfestedLeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class SilkwormItem extends Item {
    public SilkwormItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        BlockState state = useOnContext.getLevel().getBlockState(useOnContext.getClickedPos());
        if (state.is(BlockTags.LEAVES) && !state.is(ModBlocks.INFESTED_LEAVES)) {
            InfestedLeavesBlock.infestLeafBlock(useOnContext.getLevel(), useOnContext.getClickedPos());
            useOnContext.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
