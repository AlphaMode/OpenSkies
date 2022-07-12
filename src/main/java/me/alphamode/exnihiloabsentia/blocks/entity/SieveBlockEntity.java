package me.alphamode.exnihiloabsentia.blocks.entity;

import me.alphamode.exnihiloabsentia.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SieveBlockEntity extends BlockEntity {
    private Block content;
    private int progress;

    public SieveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SIEVE, blockPos, blockState);
    }

    public Block getContent() {
        return content;
    }
}
