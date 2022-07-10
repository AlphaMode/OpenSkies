package me.alphamode.openskies.meshes;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public record MeshType(int tier, ResourceLocation meshLocation, Component name) implements Comparable<MeshType> {

    @Override
    public int compareTo(@NotNull MeshType o) {
        return this == o ? 1 : 0;
    }

    public Item meshItem() {
        return Registry.ITEM.get(meshLocation);
    }
}
