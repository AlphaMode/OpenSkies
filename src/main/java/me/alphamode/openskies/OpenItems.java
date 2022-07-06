package me.alphamode.openskies;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import me.alphamode.openskies.items.CrookItem;
import me.alphamode.openskies.items.MeshItem;
import me.alphamode.openskies.items.SilkworkItem;
import me.alphamode.openskies.meshes.OpenMeshes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class OpenItems implements ItemRegistryContainer {
    public static SilkworkItem SILKWORM = new SilkworkItem(defaultSettings());
    public static MeshItem STRING_MESH = new MeshItem(OpenMeshes.STRING, defaultSettings());
    public static MeshItem FLINT_MESH = new MeshItem(OpenMeshes.FLINT, defaultSettings());
    public static MeshItem IRON_MESH = new MeshItem(OpenMeshes.IRON, defaultSettings());
    public static MeshItem DIAMOND_MESH = new MeshItem(OpenMeshes.DIAMOND, defaultSettings());

    public static CrookItem WOOD_CROOK = new CrookItem(defaultSettings());

    public static FabricItemSettings defaultSettings() {
        return new FabricItemSettings().group(OpenSkies.TAB);
    }
}
