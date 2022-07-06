package me.alphamode.openskies;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import me.alphamode.openskies.meshes.OpenMeshes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class OpenSkies implements ModInitializer {

    public static final String MOD_ID = "open_skies";
    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(asResource(MOD_ID), () -> new ItemStack(OpenBlocks.SIEVE.asItem()));

    @Override
    public void onInitialize() {
        FieldRegistrationHandler.register(OpenItems.class, MOD_ID, false);
        FieldRegistrationHandler.register(OpenMeshes.class, MOD_ID, false);
        FieldRegistrationHandler.register(OpenBlocks.class, MOD_ID, false);
        FieldRegistrationHandler.register(OpenBlockEntities.class, MOD_ID, false);
    }

    public static ResourceLocation asResource(String id) {
        return new ResourceLocation(MOD_ID, id);
    }
}
