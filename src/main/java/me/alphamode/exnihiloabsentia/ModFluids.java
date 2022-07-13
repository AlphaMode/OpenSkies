package me.alphamode.exnihiloabsentia;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import me.alphamode.exnihiloabsentia.fluids.WitchWaterFluid;
import net.minecraft.core.Registry;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public class ModFluids implements AutoRegistryContainer<Fluid> {
    public static final FlowingFluid FLOWING_WITCHWATER = new WitchWaterFluid.Flowing();
    public static final FlowingFluid WITCHWATER = new WitchWaterFluid.Source();

    @Override
    public Registry<Fluid> getRegistry() {
        return Registry.FLUID;
    }

    @Override
    public Class<Fluid> getTargetFieldType() {
        return Fluid.class;
    }
}
