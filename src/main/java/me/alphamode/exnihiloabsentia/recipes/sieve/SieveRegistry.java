package me.alphamode.exnihiloabsentia.recipes.sieve;

import me.alphamode.exnihiloabsentia.meshes.MeshType;
import me.alphamode.exnihiloabsentia.recipes.ItemInfo;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SieveRegistry {
    private static Map<MeshType, Map<Item, List<ItemInfo>>> sieveMap = new HashMap<>();

    public static void register(MeshType type, Item from, Item to, float chance) {
        sieveMap.putIfAbsent(type, new HashMap<>());
        sieveMap.get(type).get(from).add(new ItemInfo(to, chance));
    }

    public static List<ItemInfo> getSieveInfo(Item from, MeshType type) {
        return sieveMap.get(type).get(from);
    }

    public static boolean containsKey(Item from, MeshType type) {
        return sieveMap.get(type).containsKey(from);
    }

    public static void invalidate() {
        sieveMap.clear();
    }
}
