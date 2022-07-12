package me.alphamode.exnihiloabsentia.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.ModBlockEntities;
import me.alphamode.exnihiloabsentia.blocks.entity.InfestedLeavesBlockEntity;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class InfestedLeavesProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return ExNihiloAbsentia.asResource("infested_info");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        Optional<InfestedLeavesBlockEntity> be = world.getBlockEntity(data.getPos(), ModBlockEntities.INFESTED_LEAVES);
        if (be.isPresent()) {
            probeInfo.text(CompoundText.createLabelInfo("Leaves Type: ", I18n.get(be.get().getLeavesType().getBlock().getDescriptionId())));
            if (be.get().getProgress() >= 1.0F) {
                probeInfo.text(CompoundText.createLabelInfo("Progress: ", "Done"));
            } else {
                probeInfo.progress((int) (be.get().getProgress() * 100), 100);
            }
        }
    }
}
