package me.alphamode.exnihiloabsentia.util;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

@SuppressWarnings("UnstableApiUsage")
public class SingleItemStorage extends SingleStackStorage {
    private final Predicate<ItemVariant> variantPredicate;

    public SingleItemStorage(Predicate<ItemVariant> variantPredicate) {
        this.variantPredicate = variantPredicate;
    }

    public SingleItemStorage() {
        this(v -> true);
    }

    protected ItemStack currentStack = ItemStack.EMPTY;

    @Override
    public ItemStack getStack() {
        return currentStack;
    }

    @Override
    public void setStack(ItemStack stack) {
        if (!variantPredicate.test(ItemVariant.of(stack)))
            return;
        this.currentStack = stack;
    }

    @Override
    public long insert(ItemVariant insertedVariant, long maxAmount, TransactionContext transaction) {
        if (!variantPredicate.test(insertedVariant))
            return 0;
        return super.insert(insertedVariant, maxAmount, transaction);
    }
}
