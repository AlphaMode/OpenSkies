package me.alphamode.exnihiloabsentia.blocks.entity;

import com.google.common.collect.Iterators;

import me.alphamode.exnihiloabsentia.ModBlockEntities;
import me.alphamode.exnihiloabsentia.barrel.BarrelItemStorage;
import me.alphamode.exnihiloabsentia.recipes.CompostRegistry;
import me.alphamode.exnihiloabsentia.util.Color;
import me.alphamode.exnihiloabsentia.util.FluidStorageProvider;
import me.alphamode.exnihiloabsentia.util.ItemStorageProvider;
import me.alphamode.star.transfer.FluidTank;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"UnstableApiUsage", "rawtypes"})
public class BarrelBlockEntity extends BlockEntity implements FluidStorageProvider<FluidTank>, ItemStorageProvider {
    private float compostProgress = 0;
    public Color originalColor = Color.WHITE;
    private Color currentColor = Color.WHITE;

    private FluidTank fluidTank = new FluidTank(FluidConstants.BUCKET);
    private BarrelItemStorage itemInventory = new BarrelItemStorage();

    public void tick() {
        if (itemInventory.getStack() == ItemStack.EMPTY && level.isRainingAt(worldPosition.above()) && compostProgress <= 0) {
            try (Transaction t = Transaction.openOuter()) {
                fluidTank.insert(FluidVariant.of(Fluids.WATER), 162L, t);
                t.commit();
            }
        }
        if (!itemInventory.getResource().isBlank() && CompostRegistry.containsCompost(itemInventory.getStack().getItem())) {
            compostProgress += 0.00166666667;
            currentColor = Color.average(originalColor, Color.WHITE, compostProgress);
            if (compostProgress >= 1) {
                itemInventory.setStack(CompostRegistry.getCompost(itemInventory.getStack().getItem()).getDefaultInstance());
                compostProgress = 0;
            }
        }
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public BarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BARREL, blockPos, blockState);
    }

    @Override
    public void load(CompoundTag tag) {
        fluidTank.fromNbt(tag, null);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        fluidTank.toNbt(tag, null);
    }

    public void setOriginalColor() {
        Block block = ((BlockItem)itemInventory.getStack().getItem()).getBlock();
        originalColor = new Color(Minecraft.getInstance().getBlockColors().getColor(block.defaultBlockState(), getLevel(), getBlockPos(), 0));
    }

    @Override
    public FluidTank getFluidStorage(@Nullable Direction side) {
        return fluidTank;
    }

    @Override
    public BarrelItemStorage getItemStorage(@Nullable Direction side) {
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


    public float getProgress() {
        return compostProgress;
    }
}
