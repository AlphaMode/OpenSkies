package me.alphamode.openskies.compat.probe;

import mcjty.theoneprobe.api.*;
import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.OpenSkies;
import me.alphamode.openskies.barrel.BarrelItemStorage;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import me.alphamode.openskies.recipes.CompostRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class BarrelProvider implements IProbeInfoProvider {
    @Override
    public ResourceLocation getID() {
        return OpenSkies.asResource("barrel_info");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
        Optional<BarrelBlockEntity> barrelBlock = world.getBlockEntity(data.getPos(), OpenBlockEntities.BARREL);
        if (barrelBlock.isPresent()) {
            BarrelItemStorage storage = barrelBlock.get().getItemStorage(data.getSideHit());
            if (storage.getStack() == ItemStack.EMPTY)
                return;
            if (!CompostRegistry.containsCompost(storage.getStack().getItem())) {
                probeInfo.text(CompoundText.createLabelInfo("Progress: ", "Done"));
            } else {
                probeInfo.progress((int) (barrelBlock.get().getProgress() * 100), 100);
            }
        }
    }
}
