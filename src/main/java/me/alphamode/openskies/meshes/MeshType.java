package me.alphamode.openskies.meshes;

import me.alphamode.openskies.items.MeshItem;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public record MeshType(int tier, MeshItem meshItem, Component name) implements Comparable<MeshType> {

    @Override
    public int compareTo(@NotNull MeshType o) {
        return this == o ? 1 : 0;
    }
}
