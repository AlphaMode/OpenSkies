package me.alphamode.openskies.blocks.entity;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.data.OpenItemTags;
import me.alphamode.star.transfer.FluidTank;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("UnstableApiUsage")
public class BarrelBlockEntity extends BlockEntity {

    private short compostInput = 0;

    private Storage<FluidVariant> fluidTank = new FluidTank(FluidConstants.BUCKET);
    private Storage<ItemVariant> itemInventory;

    public void tick() {

    }

    public BarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.BARREL, blockPos, blockState);
    }

    public void addCompost(ItemStack stack) {
        if (stack.is(OpenItemTags.COMPOSTABLE) && compostInput <= 8) {
            stack.shrink(1);
            compostInput++;
        }
    }
}
