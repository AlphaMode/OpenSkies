package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import me.alphamode.exnihiloabsentia.items.CrookItem;
import me.alphamode.exnihiloabsentia.items.MeshItem;
import me.alphamode.exnihiloabsentia.items.SilkwormItem;
import me.alphamode.exnihiloabsentia.meshes.ModMeshes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ModItems implements ItemRegistryContainer {
    public static SilkwormItem SILKWORM = new SilkwormItem(defaultSettings());
    public static MeshItem STRING_MESH = new MeshItem(ModMeshes.STRING, defaultSettings());
    public static MeshItem FLINT_MESH = new MeshItem(ModMeshes.FLINT, defaultSettings());
    public static MeshItem IRON_MESH = new MeshItem(ModMeshes.IRON, defaultSettings());
    public static MeshItem DIAMOND_MESH = new MeshItem(ModMeshes.DIAMOND, defaultSettings());

    public static CrookItem WOOD_CROOK = new CrookItem(defaultSettings());

    public static FabricItemSettings defaultSettings() {
        return new FabricItemSettings().group(ExNihiloAbsentia.TAB);
    }
}
