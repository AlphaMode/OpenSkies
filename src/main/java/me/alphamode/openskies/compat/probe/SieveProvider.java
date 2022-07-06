package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.blocks.SieveBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SieveProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return OpenSkies.asResource("sieve_info");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        if (blockState.getBlock() instanceof SieveBlock) {
            probeInfo.mcText(new TextComponent("Mesh Type: ").withStyle(ChatFormatting.GRAY).append(blockState.getValue(SieveBlock.MESH_TYPE).name()));
        }
    }
}
