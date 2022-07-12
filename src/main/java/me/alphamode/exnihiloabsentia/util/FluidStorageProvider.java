package me.alphamode.exnihiloabsentia.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public interface FluidStorageProvider<T extends Storage<FluidVariant>> {
    T getFluidStorage(@Nullable Direction side);
}
