package me.alphamode.exnihiloabsentia.recipes.compost;

import com.mojang.datafixers.util.Either;
import me.alphamode.exnihiloabsentia.recipes.ItemInfo;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CompostRegistry {

    private static final Map<Either<Item, TagKey<Item>>, ItemInfo> compostMap = new HashMap<>();

    public static void addCompost(Item from, Item to, float amount) {
        compostMap.put(Either.left(from), new ItemInfo(to, amount));
    }

    public static void addCompost(TagKey<Item> from, Item to, float amount) {
        compostMap.put(Either.right(from), new ItemInfo(to, amount));
    }

    public static Map<Either<Item, TagKey<Item>>, ItemInfo> getCompostMap() {
        return compostMap;
    }

    private static final Map<Item, ItemInfo> cachedItemMap = new HashMap<>();

    @Nullable
    public static ItemInfo getCompost(Item from) {
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
        for (Map.Entry<Either<Item, TagKey<Item>>, ItemInfo> entry : compostMap.entrySet()) {
            entry.getKey().ifLeft(item -> cachedItemMap.put(item, entry.getValue()));
            entry.getKey().ifRight(itemTagKey -> Registry.ITEM.getOrCreateTag(itemTagKey).forEach(itemHolder -> cachedItemMap.put(itemHolder.value(), entry.getValue())));
        }
    }

    protected static void invalidate() {
        compostMap.clear();
        cachedItemMap.clear();
    }
}
