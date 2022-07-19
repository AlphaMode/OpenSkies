package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import me.alphamode.exnihiloabsentia.items.CrookItem;
import me.alphamode.exnihiloabsentia.items.SeedItem;
import me.alphamode.exnihiloabsentia.items.MeshItem;
import me.alphamode.exnihiloabsentia.items.SilkwormItem;
import me.alphamode.exnihiloabsentia.meshes.ModMeshes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

public class ModItems implements ItemRegistryContainer {
    public static CrookItem WOOD_CROOK = new CrookItem(defaultSettings());
    public static CrookItem BONE_CROOK = new CrookItem(defaultSettings());

    public static MeshItem STRING_MESH = new MeshItem(ModMeshes.STRING, defaultSettings());
    public static MeshItem FLINT_MESH = new MeshItem(ModMeshes.FLINT, defaultSettings());
    public static MeshItem IRON_MESH = new MeshItem(ModMeshes.IRON, defaultSettings());
    public static MeshItem DIAMOND_MESH = new MeshItem(ModMeshes.DIAMOND, defaultSettings());

    public static SilkwormItem SILKWORM = new SilkwormItem(defaultSettings());

    // PEBBLEs
    public static Item STONE_PEBBLE = new Item(defaultSettings());
    public static Item GRANITE_PEBBLE = new Item(defaultSettings());
    public static Item DIORITE_PEBBLE = new Item(defaultSettings());
    public static Item ANDESITE_PEBBLE = new Item(defaultSettings());

    // SEEDS
    public static SeedItem OAK_SEEDS = new SeedItem(Blocks.OAK_SAPLING, defaultSettings());
    public static SeedItem SPRUCE_SEEDS = new SeedItem(Blocks.SPRUCE_SAPLING, defaultSettings());
    public static SeedItem BIRCH_SEEDS = new SeedItem(Blocks.BIRCH_SAPLING, defaultSettings());
    public static SeedItem JUNGLE_SEEDS = new SeedItem(Blocks.JUNGLE_SAPLING, defaultSettings());
    public static SeedItem ACACIA_SEEDS = new SeedItem(Blocks.ACACIA_SAPLING, defaultSettings());
    public static SeedItem DARK_OAK_SEEDS = new SeedItem(Blocks.DARK_OAK_SAPLING, defaultSettings());
    public static SeedItem CACTUS_SEEDS = new SeedItem(Blocks.CACTUS, defaultSettings());
    public static SeedItem SUGAR_CANE_SEEDS = new SeedItem(Blocks.SUGAR_CANE, defaultSettings());
    public static SeedItem CARROT_SEEDS = new SeedItem(Blocks.CARROTS, defaultSettings());
    public static SeedItem POTATO_SEEDS = new SeedItem(Blocks.POTATOES, defaultSettings());

    public static BucketItem WITCHWATER_BUCKET = new BucketItem(ModFluids.WITCHWATER, defaultSettings().craftRemainder(Items.BUCKET).stacksTo(1));

    public static FabricItemSettings defaultSettings() {
        return new FabricItemSettings().group(ExNihiloAbsentia.TAB);
    }
}
