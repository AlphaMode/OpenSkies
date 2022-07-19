package me.alphamode.exnihiloabsentia.recipes.sieve;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.recipes.compost.CompostRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class SieveLoader extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public SieveLoader() {
        super(GSON, "sieve");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
        SieveRegistry.invalidate();
        for (ResourceLocation loc : jsonMap.keySet()) {
            JsonObject sieveMap = GsonHelper.convertToJsonObject(jsonMap.get(loc), "Compost Type");
            for (JsonElement compostElement : sieveMap.getAsJsonArray("data")) {
                JsonObject compostInfo = compostElement.getAsJsonObject();
                String from = GsonHelper.getAsString(compostInfo, "from");
                if (from.startsWith("#")) {
                    CompostRegistry.addCompost(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(from.substring(1))), GsonHelper.getAsItem(compostInfo, "to"), GsonHelper.getAsFloat(compostInfo, "amount"));
                    continue;
                }
                for (JsonElement dropsElement : compostInfo.getAsJsonArray("drops")) {
                    JsonObject drops = GsonHelper.convertToJsonObject(dropsElement, "Drops");
                    CompostRegistry.addCompost(GsonHelper.getAsItem(compostInfo, "from"), GsonHelper.getAsItem(drops, "item"), GsonHelper.getAsFloat(drops, "chance"));
                }

            }
        }
    }

    @Override
    public ResourceLocation getFabricId() {
        return ExNihiloAbsentia.asResource("sieve_registry");
    }
}
