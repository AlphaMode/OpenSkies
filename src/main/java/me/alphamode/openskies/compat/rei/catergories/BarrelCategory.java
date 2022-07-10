package me.alphamode.openskies.compat.rei.catergories;

import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.compat.rei.OpenSkiesREI;
import me.alphamode.openskies.compat.rei.displays.BarrelDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.List;

public class BarrelCategory implements DisplayCategory<BarrelDisplay> {
    @Override
    public CategoryIdentifier<BarrelDisplay> getCategoryIdentifier() {
        return OpenSkiesREI.BARREL;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("rei.open_skies.title.barrel");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(OpenBlocks.WOOD_BARREL);
    }

    @Override
    public int getDisplayHeight() {
        return 30;
    }

    @Override
    public int getDisplayWidth(BarrelDisplay display) {
        return 85;
    }

    @Override
    public List<Widget> setupDisplay(BarrelDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createSlot(point(bounds.x + 10, bounds.y + 7))
                .markInput()
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createArrow(point(bounds.x + 30, bounds.y + 7)));
        widgets.add(Widgets.createSlot(point(bounds.x + 60, bounds.y + 7))
                .markOutput()
                .entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    public Point point(int x, int y) {
        return new Point(x, y);
    }
}
