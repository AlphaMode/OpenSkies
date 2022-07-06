package me.alphamode.openskies.items;

import me.alphamode.openskies.meshes.MeshType;
import net.minecraft.world.item.Item;

public class MeshItem extends Item {

    private final MeshType meshType;

    public MeshItem(MeshType meshType, Properties properties) {
        super(properties);
        this.meshType = meshType;
    }

    public MeshType getMeshType() {
        return meshType;
    }
}
