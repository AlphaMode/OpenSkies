package me.alphamode.exnihiloabsentia.recipes;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {

    private static final Map<Either<Item, TagKey<Item>>, Item> compostMap = new HashMap<>();

    public static void addCompost(Item from, Item to) {
        compostMap.put(Either.left(from), to);
    }

    public static void addCompost(TagKey<Item> from, Item to) {
        compostMap.put(Either.right(from), to);
    }

    public static Map<Either<Item, TagKey<Item>>, Item> getCompostMap() {
        return compostMap;
    }

    private static final Map<Item, Item> cachedItemMap = new HashMap<>();

    @Nullable
    public static Item getCompost(Item from) {
        if (cachedItemMap.size() == 0)
            CompostRegistry.cacheEntries();
        return cachedItemMap.getOrDefault(from, null);
    }

    @Nullable
    public static boolean containsCompost(Item from) {
        if (cachedItemMap.size() == 0)
            CompostRegistry.cacheEntries();
        return cachedItemMap.containsKey(from);
    }

    protected static void cacheEntries() {
        for (Map.Entry<Either<Item, TagKey<Item>>, Item> entry : compostMap.entrySet()) {
            entry.getKey().ifLeft(item -> cachedItemMap.put(item, entry.getValue()));
            entry.getKey().ifRight(itemTagKey -> Registry.ITEM.getOrCreateTag(itemTagKey).forEach(itemHolder -> cachedItemMap.put(itemHolder.value(), entry.getValue())));
        }
    }

    protected static void invalidate() {
        compostMap.clear();
        cachedItemMap.clear();
    }
}
