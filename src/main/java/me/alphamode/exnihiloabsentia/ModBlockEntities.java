package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.exnihiloabsentia.blocks.entity.BarrelBlockEntity;
import me.alphamode.exnihiloabsentia.blocks.entity.InfestedLeavesBlockEntity;
import me.alphamode.exnihiloabsentia.blocks.entity.SieveBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities implements AutoRegistryContainer<BlockEntityType<?>> {
    public static final BlockEntityType<InfestedLeavesBlockEntity> INFESTED_LEAVES = FabricBlockEntityTypeBuilder.create(InfestedLeavesBlockEntity::new, ModBlocks.INFESTED_LEAVES).build();
    public static final BlockEntityType<SieveBlockEntity> SIEVE = FabricBlockEntityTypeBuilder.create(SieveBlockEntity::new, ModBlocks.SIEVE).build();
    public static final BlockEntityType<BarrelBlockEntity> BARREL = FabricBlockEntityTypeBuilder.create(BarrelBlockEntity::new, ModBlocks.WOOD_BARREL, ModBlocks.STONE_BARREL).build();

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registry.BLOCK_ENTITY_TYPE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
