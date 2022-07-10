package me.alphamode.openskies.blocks.entity;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.meshes.MeshType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SieveBlockEntity extends BlockEntity {

    private Block content;
    private int progress;

    public SieveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.SIEVE, blockPos, blockState);
    }

    public Block getContent() {
        return content;
    }
}
