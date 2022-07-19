package me.alphamode.exnihiloabsentia.blocks.entity;

import me.alphamode.exnihiloabsentia.ModBlockEntities;
import me.alphamode.exnihiloabsentia.util.ItemStorageProvider;
import me.alphamode.exnihiloabsentia.util.SingleItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantItemStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SieveBlockEntity extends BlockEntity implements ItemStorageProvider<SingleItemStorage> {
    private final SingleItemStorage storage = new SingleItemStorage(v -> v.getItem() instanceof BlockItem);
    private int progress;

    public SieveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SIEVE, blockPos, blockState);
    }

    public Block getContent() {
        return ((BlockItem) storage.getStack().getItem()).getBlock();
    }

    @Override
    public SingleItemStorage getItemStorage(@Nullable Direction side) {
        return storage;
    }
}
