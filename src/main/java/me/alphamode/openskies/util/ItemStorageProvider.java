package me.alphamode.openskies.util;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public interface ItemStorageProvider {
    Storage<ItemVariant> getItemStorage(@Nullable Direction side);
}
