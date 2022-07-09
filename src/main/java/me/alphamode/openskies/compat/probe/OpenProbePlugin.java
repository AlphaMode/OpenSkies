package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;

public class OpenProbePlugin implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe api) {
        api.registerProvider(new InfestedLeavesProvider());
        api.registerProvider(new SieveProvider());
    }
}
