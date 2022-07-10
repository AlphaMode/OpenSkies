package me.alphamode.openskies.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.alphamode.openskies.OpenSkies;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class CompostLoader extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public CompostLoader() {
        super(GSON, "compost");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        CompostRegistry.invalidate();
        for (ResourceLocation loc : jsonMap.keySet()) {
            JsonObject compostInfo = GsonHelper.convertToJsonObject(jsonMap.get(loc), "Compost Type");
            String from = GsonHelper.getAsString(compostInfo, "from");
            if (from.startsWith("#")) {
                CompostRegistry.addCompost(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(from.substring(1))), GsonHelper.getAsItem(compostInfo, "to"));
                continue;
            }
            CompostRegistry.addCompost(GsonHelper.getAsItem(compostInfo, "from"), GsonHelper.getAsItem(compostInfo, "to"));
        }
        CompostRegistry.cacheEntries();
    }

    @Override
    public ResourceLocation getFabricId() {
        return OpenSkies.asResource("compost_registry");
    }
}
