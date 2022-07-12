package me.alphamode.exnihiloabsentia.data;

import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> COMPOSTABLE = TagKey.create(Registry.ITEM_REGISTRY, ExNihiloAbsentia.asResource("compostable"));
}
