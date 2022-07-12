package me.alphamode.exnihiloabsentia.compat.rei;

import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.compat.rei.catergories.BarrelCategory;
import me.alphamode.exnihiloabsentia.compat.rei.displays.BarrelDisplay;
import me.alphamode.exnihiloabsentia.recipes.CompostRegistry;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ModREIPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<BarrelDisplay> BARREL = CategoryIdentifier.of(ExNihiloAbsentia.asResource("rei_barrel"));

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new BarrelCategory());

        registry.addWorkstations(BARREL, EntryStacks.of(ModBlocks.WOOD_BARREL), EntryStacks.of(ModBlocks.STONE_BARREL));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        CompostRegistry.getCompostMap().forEach((from, to) -> {
            registry.add(new BarrelDisplay(from, to));
        });
    }
}
