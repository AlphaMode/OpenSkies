package me.alphamode.openskies.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public interface FluidStorageProvider {
    Storage<FluidVariant> getFluidStorage(@Nullable Direction side);
}
