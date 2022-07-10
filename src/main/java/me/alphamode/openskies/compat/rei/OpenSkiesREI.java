package me.alphamode.openskies.compat.rei;

import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.compat.rei.catergories.BarrelCategory;
import me.alphamode.openskies.compat.rei.displays.BarrelDisplay;
import me.alphamode.openskies.recipes.CompostRegistry;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class OpenSkiesREI implements REIClientPlugin {

    public static final CategoryIdentifier<BarrelDisplay> BARREL = CategoryIdentifier.of(OpenSkies.asResource("rei_barrel"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new BarrelCategory());

        registry.addWorkstations(BARREL, EntryStacks.of(OpenBlocks.WOOD_BARREL), EntryStacks.of(OpenBlocks.STONE_BARREL));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        CompostRegistry.getCompostMap().forEach((from, to) -> {
            registry.add(new BarrelDisplay(from, to));
        });
    }
}
