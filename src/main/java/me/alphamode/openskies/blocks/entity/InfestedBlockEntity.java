package me.alphamode.openskies.blocks.entity;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.blocks.InfestedLeavesBlock;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedBlockEntity extends BlockEntity implements RenderAttachmentBlockEntity {

    private float progress = 0;
    private boolean hasNearbyLeaves = true;
    private BlockState leavesState = Blocks.OAK_LEAVES.defaultBlockState();

    public InfestedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.INFESTED_LEAVES, blockPos, blockState);
    }

    public void tick() {
        if (progress < 1.0F)
        {
            progress += 1.0 / 600;
//            markDirty();

            if (progress > 1.0F)
            {
                progress = 1.0F;

                level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
            }
        }

        // Don't update unless there's leaves nearby, or we haven't checked for leavesUpdateFrequency ticks. And only update on the server
        if (!level.isClientSide && hasNearbyLeaves /*|| level.getGameTime() % 40 == updateIndex*/)
        {
            hasNearbyLeaves = false;

            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {
                    for (int z = -1; z <= 1; z++)
                    {
                        BlockPos newPos = new BlockPos(worldPosition.offset(x, y, z));
                        BlockState state = level.getBlockState(newPos);

                        if (state != null && state.getBlock() != null && (state.getBlock() == Blocks.OAK_LEAVES /*|| state.getBlock() == Blocks.LEAVES2*/))
                        {
                            hasNearbyLeaves = true;

                            if (level.random.nextFloat() < 0.0015)
                            {
                                InfestedLeavesBlock.infestLeafBlock(level, newPos);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setWrappedLeaves(BlockState leavesBlock) {
        leavesState = leavesBlock;
    }

    public BlockState getLeavesState() {
        return leavesState;
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public Object getRenderAttachmentData() {
        return progress;
    }
}
