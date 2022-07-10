package me.alphamode.openskies.barrel;

import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.minecraft.world.item.ItemStack;

public class BarrelItemStorage extends SingleStackStorage {
    protected ItemStack currentStack;

    @Override
    public ItemStack getStack() {
        return currentStack;
    }

    @Override
    public void setStack(ItemStack stack) {
        this.currentStack = stack;
    }
}
