package me.alphamode.exnihiloabsentia.items;

import me.alphamode.exnihiloabsentia.meshes.MeshType;
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
