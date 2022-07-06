package me.alphamode.openskies;

import io.wispforest.owo.registration.annotations.IterationIgnored;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import me.alphamode.openskies.blocks.entity.InfestedBlockEntity;
import me.alphamode.openskies.blocks.entity.SieveBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class OpenBlockEntities implements AutoRegistryContainer<BlockEntityType<?>> {
    public static final BlockEntityType<InfestedBlockEntity> INFESTED_LEAVES = FabricBlockEntityTypeBuilder.create(InfestedBlockEntity::new, OpenBlocks.INFESTED_LEAVES).build();
    @IterationIgnored
    public static final BlockEntityType<SieveBlockEntity> SIEVE = FabricBlockEntityTypeBuilder.create(SieveBlockEntity::new, OpenBlocks.SIEVE).build();
    public static final BlockEntityType<BarrelBlockEntity> BARREL = FabricBlockEntityTypeBuilder.create(BarrelBlockEntity::new, OpenBlocks.WOOD_BARREL, OpenBlocks.STONE_BARREL).build();

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registry.BLOCK_ENTITY_TYPE;
    }

    @Override
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
