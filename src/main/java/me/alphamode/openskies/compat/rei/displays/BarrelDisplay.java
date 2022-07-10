package me.alphamode.openskies.compat.rei.displays;

import com.mojang.datafixers.util.Either;
import me.alphamode.openskies.compat.rei.OpenSkiesREI;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public class BarrelDisplay implements Display {

    private final List<EntryIngredient> from, to;

    public BarrelDisplay(Either<Item, TagKey<Item>> from, Item to) {
        if (from.left().isPresent())
            this.from = List.of(EntryIngredients.of(from.left().get()));
        else
            this.from = List.of(EntryIngredients.ofItemTag(from.right().get()));
        this.to = List.of(EntryIngredients.of(to));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return from;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return to;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return OpenSkiesREI.BARREL;
    }
}
