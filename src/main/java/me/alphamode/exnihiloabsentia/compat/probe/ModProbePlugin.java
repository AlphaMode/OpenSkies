package me.alphamode.exnihiloabsentia.compat.probe;

import mcjty.theoneprobe.api.*;

public class ModProbePlugin implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe api) {
        api.registerProvider(new InfestedLeavesProvider());
        api.registerProvider(new BarrelProvider());
        api.registerProvider(new SieveProvider());
    }
}
