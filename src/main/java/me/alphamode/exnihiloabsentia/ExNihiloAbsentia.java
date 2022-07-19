package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import me.alphamode.exnihiloabsentia.meshes.ModMeshes;
import me.alphamode.exnihiloabsentia.recipes.compost.CompostLoader;
import me.alphamode.exnihiloabsentia.recipes.sieve.SieveLoader;
import me.alphamode.exnihiloabsentia.util.FluidStorageProvider;
import me.alphamode.exnihiloabsentia.util.ItemStorageProvider;
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

public class ExNihiloAbsentia implements ModInitializer {
    public static final String MOD_ID = "exnihiloabsentia";
    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(asResource(MOD_ID), () -> new ItemStack(ModBlocks.SIEVE.asItem()));

    @Override
    public void onInitialize() {
        FieldRegistrationHandler.register(ModItems.class, MOD_ID, false);
        FieldRegistrationHandler.register(ModMeshes.class, MOD_ID, false);
        FieldRegistrationHandler.register(ModBlocks.class, MOD_ID, false);
        FieldRegistrationHandler.register(ModFluids.class, MOD_ID, false);
        FieldRegistrationHandler.register(ModBlockEntities.class, MOD_ID, false);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.INFESTED_LEAVES, 30, 60);
        FuelRegistry.INSTANCE.add(ModItems.WOOD_CROOK, 200);

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new CompostLoader());
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new SieveLoader());

        FluidStorage.SIDED.registerFallback((world, pos, state, blockEntity, context) -> {
            if (blockEntity != null && blockEntity instanceof FluidStorageProvider<?> provider)
                return provider.getFluidStorage(context);
            return null;
        });
        ItemStorage.SIDED.registerFallback((world, pos, state, blockEntity, context) -> {
            if (blockEntity != null && blockEntity instanceof ItemStorageProvider<?> provider)
                return provider.getItemStorage(context);
            return null;
        });
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
