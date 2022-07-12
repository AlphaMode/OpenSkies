package me.alphamode.exnihiloabsentia.state;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collection;
import java.util.Optional;

import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;

public class RegistryProperty<T extends Comparable<T>> extends Property<T> {

    private final Registry<T> registry;

    public RegistryProperty(String name, Class<T> clazz, Registry<T> registry) {
        super(name, clazz);
        this.registry = registry;
    }

    @Override
    public Collection<T> getPossibleValues() {
        return registry.stream().toList();
    }

    @Override
    public String getName(T comparable) {
        return registry.getKey(comparable).getPath();
    }

    @Override
    public Optional<T> getValue(String string) {
        return Optional.ofNullable(registry.get(new ResourceLocation(ExNihiloAbsentia.MOD_ID, string)));
    }
}
