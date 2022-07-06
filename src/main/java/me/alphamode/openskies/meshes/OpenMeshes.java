package me.alphamode.openskies.meshes;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.openskies.OpenItems;
import me.alphamode.openskies.OpenSkies;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;

public class OpenMeshes implements AutoRegistryContainer<MeshType> {
    public static Registry<MeshType> MESH = FabricRegistryBuilder.createDefaulted(MeshType.class, OpenSkies.asResource("mesh_registry"), OpenSkies.asResource("none")).buildAndRegister();
    public static final MeshType NONE  = new MeshType(-1, null);
    public static final MeshType STRING  = new MeshType(0, OpenItems.STRING_MESH);
    public static final MeshType FLINT = new MeshType(1, OpenItems.FLINT_MESH);
    public static final MeshType IRON = new MeshType(2, OpenItems.IRON_MESH);
    public static final MeshType DIAMOND = new MeshType(3, OpenItems.DIAMOND_MESH);

    @Override
    public Registry<MeshType> getRegistry() {
        return MESH;
    }

    @Override
    public Class<MeshType> getTargetFieldType() {
        return MeshType.class;
    }
}
