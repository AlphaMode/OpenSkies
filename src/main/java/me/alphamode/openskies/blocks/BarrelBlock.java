package me.alphamode.openskies.blocks;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import me.alphamode.openskies.data.OpenItemTags;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
        if (fluidStorage == null)
            return InteractionResult.PASS;
        Optional<BarrelBlockEntity> barrel = level.getBlockEntity(pos, OpenBlockEntities.BARREL);
        if (barrel.isPresent()) {
            Storage<FluidVariant> barrelStorage = barrel.get().getFluidStorage(result.getDirection());
            ResourceAmount<FluidVariant> curentResource = barrel.get().getCurrentFluid();
            try (Transaction t = Transaction.openOuter()) {
                ResourceAmount<FluidVariant> resourceAmount = StorageUtil.findExtractableContent(fluidStorage, t);
                if (resourceAmount != null) {
                    StorageUtil.move(fluidStorage, barrelStorage, v -> true, resourceAmount.amount(), t);
                    t.commit();
                    return InteractionResult.PASS;
                }
                if (!curentResource.resource().isBlank() && barrelStorage.simulateExtract(curentResource.resource(), FluidConstants.BUCKET, t) == FluidConstants.BUCKET) {
                    StorageUtil.move(barrelStorage, fluidStorage, v -> true, FluidConstants.BUCKET, t);
                    t.commit();
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
