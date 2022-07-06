package me.alphamode.openskies.meshes;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.openskies.OpenItems;
import me.alphamode.openskies.OpenSkies;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class OpenMeshes implements AutoRegistryContainer<MeshType> {
    public static Registry<MeshType> MESH = FabricRegistryBuilder.createDefaulted(MeshType.class, OpenSkies.asResource("mesh_registry"), OpenSkies.asResource("none")).buildAndRegister();
    public static final MeshType NONE  = new MeshType(-1, null, new TextComponent("None").withStyle(ChatFormatting.WHITE));
    public static final MeshType STRING  = new MeshType(0, OpenItems.STRING_MESH, new TextComponent("String").withStyle(ChatFormatting.WHITE));
    public static final MeshType FLINT = new MeshType(1, OpenItems.FLINT_MESH, new TextComponent("Flint").withStyle(ChatFormatting.GRAY));
    public static final MeshType IRON = new MeshType(2, OpenItems.IRON_MESH, new TextComponent("Iron").withStyle(ChatFormatting.DARK_GRAY));
    public static final MeshType DIAMOND = new MeshType(3, OpenItems.DIAMOND_MESH, new TextComponent("Diamond").withStyle(ChatFormatting.AQUA));

    @Override
    public Registry<MeshType> getRegistry() {
        return MESH;
    }

    @Override
    public Class<MeshType> getTargetFieldType() {
        return MeshType.class;
    }
}
