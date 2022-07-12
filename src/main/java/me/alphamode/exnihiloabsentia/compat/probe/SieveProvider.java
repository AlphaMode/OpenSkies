package me.alphamode.exnihiloabsentia.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.blocks.SieveBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SieveProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return ExNihiloAbsentia.asResource("sieve_info");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        if (blockState.getBlock() instanceof SieveBlock) {
            probeInfo.mcText(new TextComponent("Mesh Type: ").withStyle(ChatFormatting.GRAY).append(blockState.getValue(SieveBlock.MESH_TYPE).name()));
        }
    }
}
