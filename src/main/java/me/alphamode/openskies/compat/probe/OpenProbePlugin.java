package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.blocks.entity.InfestedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OpenProbePlugin implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe api) {
        api.registerProvider(new InfestedLeavesProvider());
        api.registerProvider(new SieveProvider());
    }
}
