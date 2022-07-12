package me.alphamode.exnihiloabsentia.blocks;

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

import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.blocks.entity.InfestedLeavesBlockEntity;

public class InfestedLeavesBlock extends LeavesBlock implements EntityBlock {
    public InfestedLeavesBlock(Properties settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new InfestedLeavesBlockEntity(blockPos, blockState);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return false;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState state, BlockEntityType<T> blockEntityType) {
        return (level, blockPos, blockState, blockEntity) -> {
            if (!blockEntity.hasLevel()) blockEntity.setLevel(l);
            ((InfestedLeavesBlockEntity) blockEntity).tick();
        };
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public static void infestLeafBlock(Level world, BlockPos pos) {
        BlockState block = world.getBlockState(pos);

        if (block.is(BlockTags.LEAVES) && !block.is(ModBlocks.INFESTED_LEAVES)) {
            world.setBlock(pos, ModBlocks.INFESTED_LEAVES.defaultBlockState(), 3);

            InfestedLeavesBlockEntity tile = (InfestedLeavesBlockEntity) world.getBlockEntity(pos);

            if (tile != null) {
                tile.setLeavesType(block);
            }
        }
    }
}
