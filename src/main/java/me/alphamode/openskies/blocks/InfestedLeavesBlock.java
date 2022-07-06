package me.alphamode.openskies.blocks;

import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.blocks.entity.InfestedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class InfestedLeavesBlock extends LeavesBlock implements EntityBlock {
    public InfestedLeavesBlock(Properties settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InfestedBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState state, BlockEntityType<T> blockEntityType) {
        return (level, blockPos, blockState, blockEntity) -> ((InfestedBlockEntity)blockEntity).tick();
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public static void infestLeafBlock(Level world, BlockPos pos) {
        BlockState block = world.getBlockState(pos);

        if (block.is(BlockTags.LEAVES) && !block.is(OpenBlocks.INFESTED_LEAVES)) {
            world.setBlock(pos, OpenBlocks.INFESTED_LEAVES.defaultBlockState(), 3);

            InfestedBlockEntity tile = (InfestedBlockEntity) world.getBlockEntity(pos);

            if (tile != null) {
                tile.setWrappedLeaves(block);
            }
        }
    }
}
