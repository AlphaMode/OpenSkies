package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.blocks.entity.InfestedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class InfestedLeavesProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return OpenSkies.asResource("infested_info");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        Optional<InfestedBlockEntity> be = world.getBlockEntity(data.getPos(), OpenBlockEntities.INFESTED_LEAVES);
        if (be.isPresent()) {
            if (be.get().getProgress() >= 1.0F) {
                probeInfo.text(CompoundText.createLabelInfo("Progress: ", "Done"));
            } else {
                probeInfo.progress((int) (be.get().getProgress() * 100), 100);
            }
        }
    }
}
