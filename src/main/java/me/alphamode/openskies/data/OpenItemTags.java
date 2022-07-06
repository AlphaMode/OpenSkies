package me.alphamode.openskies.data;

import me.alphamode.openskies.OpenSkies;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class OpenItemTags {
    public static final TagKey<Item> COMPOSTABLE = TagKey.create(Registry.ITEM_REGISTRY, OpenSkies.asResource("compostable"));
}
