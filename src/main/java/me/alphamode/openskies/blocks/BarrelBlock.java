package me.alphamode.openskies.blocks;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.barrel.BarrelItemStorage;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import me.alphamode.openskies.data.OpenItemTags;
import me.alphamode.openskies.recipes.CompostRegistry;
import me.alphamode.star.transfer.FluidTank;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.PlayerInventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class BarrelBlock extends Block implements EntityBlock {
    public BarrelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BarrelBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, blockState1, blockEntity) -> ((BarrelBlockEntity)blockEntity).tick();
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        Storage<FluidVariant> fluidStorage = ContainerItemContext.ofPlayerHand(player, hand).find(FluidStorage.ITEM);
        Optional<BarrelBlockEntity> barrel = level.getBlockEntity(pos, OpenBlockEntities.BARREL);
        if (barrel.isPresent()) {
            FluidTank barrelFluidStorage = barrel.get().getFluidStorage(result.getDirection());
            BarrelItemStorage barrelItemStorage = barrel.get().getItemStorage(result.getDirection());
            ResourceAmount<FluidVariant> curentResource = barrel.get().getCurrentFluid();
            try (Transaction t = Transaction.openOuter()) {
                if (fluidStorage != null && barrelItemStorage.getAmount() <= 0) {
                    ResourceAmount<FluidVariant> resourceAmount = StorageUtil.findExtractableContent(fluidStorage, t);
                    if (resourceAmount != null) {
                        StorageUtil.move(fluidStorage, barrelFluidStorage, v -> true, resourceAmount.amount(), t);
                        t.commit();
                        return InteractionResult.SUCCESS;
                    }
                    if (!curentResource.resource().isBlank() && barrelFluidStorage.simulateExtract(curentResource.resource(), FluidConstants.BUCKET, t) == FluidConstants.BUCKET) {
                        StorageUtil.move(barrelFluidStorage, fluidStorage, v -> true, FluidConstants.BUCKET, t);
                        t.commit();
                        return InteractionResult.SUCCESS;
                    }
                }
            }
            if (barrelFluidStorage.getAmount() <= 0) {
                SingleSlotStorage<ItemVariant> handStorage = PlayerInventoryStorage.of(player).getHandSlot(hand);
                Item compostItem = CompostRegistry.getCompost(handStorage.getResource().getItem());
                if (compostItem != null) {
                    try (Transaction t = Transaction.openOuter()) {
                        StorageUtil.move(handStorage, barrelItemStorage, v -> v.getItem() instanceof BlockItem, 1, t);
                        t.commit();
                        barrel.get().setOriginalColor();
                    }
                }
            }
        }
        return super.use(blockState, level, pos, player, hand, result);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Block.box(0.0625f * 16, 0, 0.0625f * 16, 0.9375f * 16, 16f, 0.9375f * 16);
    }
}
