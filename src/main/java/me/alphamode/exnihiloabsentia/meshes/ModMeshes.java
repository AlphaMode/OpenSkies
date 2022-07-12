package me.alphamode.exnihiloabsentia.meshes;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;

public class ModMeshes implements AutoRegistryContainer<MeshType> {
    public static Registry<MeshType> MESH = FabricRegistryBuilder.createDefaulted(MeshType.class, ExNihiloAbsentia.asResource("mesh_registry"), ExNihiloAbsentia.asResource("none")).buildAndRegister();
    public static final MeshType NONE  = new MeshType(-1, null, new TextComponent("None").withStyle(ChatFormatting.WHITE));
    public static final MeshType STRING  = new MeshType(0, ExNihiloAbsentia.asResource("string_mesh"), new TextComponent("String").withStyle(ChatFormatting.WHITE));
    public static final MeshType FLINT = new MeshType(1, ExNihiloAbsentia.asResource("flint_mesh"), new TextComponent("Flint").withStyle(ChatFormatting.GRAY));
    public static final MeshType IRON = new MeshType(2, ExNihiloAbsentia.asResource("iron_mesh"), new TextComponent("Iron").withStyle(ChatFormatting.DARK_GRAY));
    public static final MeshType DIAMOND = new MeshType(3, ExNihiloAbsentia.asResource("diamond_mesh"), new TextComponent("Diamond").withStyle(ChatFormatting.AQUA));

    @Override
    public Registry<MeshType> getRegistry() {
        return MESH;
    }

    @Override
    public Class<MeshType> getTargetFieldType() {
        return MeshType.class;
    }
}
