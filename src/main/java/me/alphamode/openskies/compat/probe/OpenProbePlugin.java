package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.blocks.entity.InfestedLeavesBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OpenProbePlugin implements ITheOneProbePlugin {
    @Override
    public void onLoad(ITheOneProbe api) {
        api.registerProvider(new IProbeInfoProvider() {
            @Override
            public ResourceLocation getID() {
                return OpenSkies.asResource("infested_info");
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
                BlockEntity be = world.getBlockEntity(data.getPos());
                if (be != null && be instanceof InfestedLeavesBlockEntity tile) {
                    if (tile.getProgress() >= 1.0F) {
                        probeInfo.text("Progress: Done");
                    } else {
                        probeInfo.progress((int) (tile.getProgress() * 100), 100);
                    }
                }
            }
        });
    }
}
