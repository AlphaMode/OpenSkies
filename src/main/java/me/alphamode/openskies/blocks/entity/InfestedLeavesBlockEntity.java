package me.alphamode.openskies.blocks.entity;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.blocks.InfestedLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedLeavesBlockEntity extends BlockEntity {
    private float progress = 0;
    private boolean hasNearbyLeaves = true;
    private BlockState leavesType = Blocks.OAK_LEAVES.defaultBlockState();

    public InfestedLeavesBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.INFESTED_LEAVES, blockPos, blockState);
    }

    public void tick() {
        if (progress < 1.0f) {
            progress += 1.0 / 600;
            setChanged();

            if (progress > 1.0f) {
                progress = 1.0f;

                level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
            }
        }

        // Don't update unless there's leaves nearby, or we haven't checked for leavesUpdateFrequency ticks. And only update on the server
        if (!level.isClientSide && hasNearbyLeaves) {
            hasNearbyLeaves = false;

            BlockPos.MutableBlockPos newPos = new BlockPos.MutableBlockPos();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        newPos.set(worldPosition.offset(x, y, z));
                        BlockState state = level.getBlockState(newPos);

                        if (state != null && state.getBlock() != null && (state.is(BlockTags.LEAVES) && !state.is(OpenBlocks.INFESTED_LEAVES))) {
                            hasNearbyLeaves = true;

                            if (level.random.nextFloat() < 0.0015) {
                                InfestedLeavesBlock.infestLeafBlock(level, newPos);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("LeavesType")) {
            leavesType = NbtUtils.readBlockState(tag.getCompound("LeavesType"));
        } else {
            leavesType = Blocks.OAK_LEAVES.defaultBlockState();
        }

        progress = tag.getFloat("Progress");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("LeavesType", NbtUtils.writeBlockState(leavesType));
        tag.putFloat("Progress", progress);
    }

    public void setLeavesType(BlockState leavesBlock) {
        leavesType = leavesBlock;
        setChanged();
    }

    public BlockState getLeavesType() {
        return leavesType;
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
