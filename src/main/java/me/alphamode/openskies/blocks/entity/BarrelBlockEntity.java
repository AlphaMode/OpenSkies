package me.alphamode.openskies.blocks.entity;

import com.google.common.collect.Iterators;
import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.barrel.BarrelItemStorage;
import me.alphamode.openskies.data.OpenItemTags;
import me.alphamode.openskies.util.FluidStorageProvider;
import me.alphamode.openskies.util.ItemStorageProvider;
import me.alphamode.star.transfer.FluidTank;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class BarrelBlockEntity extends BlockEntity implements FluidStorageProvider, ItemStorageProvider {

    private short compostInput = 0;

    private Storage<FluidVariant> fluidTank = new FluidTank(FluidConstants.BUCKET);
    private BarrelItemStorage itemInventory = new BarrelItemStorage();

    public void tick() {
        if (itemInventory.getStack() == null && level.isRainingAt(worldPosition.above())) {
            try (Transaction t = Transaction.openOuter()) {
                fluidTank.insert(FluidVariant.of(Fluids.WATER), 162L, t);
                t.commit();
            }
        }
    }

    public BarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.BARREL, blockPos, blockState);
    }

    public void addCompost(ItemStack stack) {
        if (stack.is(OpenItemTags.COMPOSTABLE) && compostInput <= 8) {
            stack.shrink(1);
            compostInput++;
        }
    }

    @Override
    public Storage<FluidVariant> getFluidStorage(@Nullable Direction side) {
        return fluidTank;
    }

    @Override
    public Storage<ItemVariant> getItemStorage(@Nullable Direction side) {
        return itemInventory;
    }

    /**
     * Returns the current amount of fluid in the tank, if none it will just return a blank {@link FluidVariant}
     * */
    public ResourceAmount<FluidVariant> getCurrentFluid() {
        try (Transaction t = Transaction.openOuter()) {
            StorageView<FluidVariant> view = Iterators.get(fluidTank.iterator(t), 0);
            return new ResourceAmount<>(view.getResource(), view.getAmount());
        }
    }

    public long getCapacity() {
        try (Transaction t = Transaction.openOuter()) {
            return Iterators.get(fluidTank.iterator(t), 0).getCapacity();
        }
    }


}
