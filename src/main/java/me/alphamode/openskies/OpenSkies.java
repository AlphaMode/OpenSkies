package me.alphamode.openskies;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import me.alphamode.openskies.meshes.OpenMeshes;
import me.alphamode.openskies.recipes.CompostLoader;
import me.alphamode.openskies.util.FluidStorageProvider;
import me.alphamode.openskies.util.ItemStorageProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
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

        FlammableBlockRegistry.getDefaultInstance().add(OpenBlocks.INFESTED_LEAVES, 30, 60);
        FuelRegistry.INSTANCE.add(OpenItems.WOOD_CROOK, 200);

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new CompostLoader());

        FluidStorage.SIDED.registerFallback((world, pos, state, blockEntity, context) -> {
            if (blockEntity != null && blockEntity instanceof FluidStorageProvider provider)
                return provider.getFluidStorage(context);
            return null;
        });
        ItemStorage.SIDED.registerFallback((world, pos, state, blockEntity, context) -> {
            if (blockEntity != null && blockEntity instanceof ItemStorageProvider provider)
                return provider.getItemStorage(context);
            return null;
        });
    }

    public static ResourceLocation asResource(String id) {
        return new ResourceLocation(MOD_ID, id);
    }
}
