package me.alphamode.exnihiloabsentia.fluids;

import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.ModFluids;
import me.alphamode.exnihiloabsentia.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;

public abstract class WitchWaterFluid extends WaterFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_WITCHWATER;
    }

    @Override
    public Fluid getSource() {
        return ModFluids.WITCHWATER;
    }

    @Override
    public Item getBucket() {
        return ModItems.WITCHWATER_BUCKET;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state) {
        return ModBlocks.WITCHWATER.defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == ModFluids.WITCHWATER || fluid == ModFluids.FLOWING_WITCHWATER;
    }


    public static class Flowing extends WitchWaterFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends WitchWaterFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
