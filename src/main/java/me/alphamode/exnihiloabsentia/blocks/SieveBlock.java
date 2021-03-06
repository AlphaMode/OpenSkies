package me.alphamode.exnihiloabsentia.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

import me.alphamode.exnihiloabsentia.ModBlockEntities;
import me.alphamode.exnihiloabsentia.blocks.entity.SieveBlockEntity;
import me.alphamode.exnihiloabsentia.items.MeshItem;
import me.alphamode.exnihiloabsentia.meshes.MeshType;
import me.alphamode.exnihiloabsentia.meshes.ModMeshes;
import me.alphamode.exnihiloabsentia.state.RegistryProperty;

public class SieveBlock extends Block implements EntityBlock {

    public static final RegistryProperty<MeshType> MESH_TYPE = new RegistryProperty<>("mesh", MeshType.class, ModMeshes.MESH);

    public SieveBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(MESH_TYPE, ModMeshes.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MESH_TYPE);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SieveBlockEntity(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        // Swap the old mesh with the new one if player is holding shift
        if (state.getValue(MESH_TYPE) != ModMeshes.NONE && player.isShiftKeyDown()) {
            if (itemStack.getItem() instanceof MeshItem meshItem && itemStack.getCount() == 1) {
                player.setItemInHand(hand, new ItemStack(state.getValue(MESH_TYPE).meshItem()));
                return InteractionResult.SUCCESS;
            }
            player.getInventory().placeItemBackInInventory(new ItemStack(state.getValue(MESH_TYPE).meshItem()));
            world.setBlockAndUpdate(pos, state.setValue(MESH_TYPE, ModMeshes.NONE));
            return InteractionResult.SUCCESS;
        }
        if (itemStack.getItem() instanceof MeshItem meshItem && state.getValue(MESH_TYPE) == ModMeshes.NONE) {
            world.setBlockAndUpdate(pos, state.setValue(MESH_TYPE, meshItem.getMeshType()));
            if (!player.isCreative())
                itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        Optional<SieveBlockEntity> sieve = world.getBlockEntity(pos, ModBlockEntities.SIEVE);
        if (sieve.isPresent()) {

        }

        return super.use(state, world, pos, player, hand, blockHitResult);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!player.isCreative() && state.getValue(MESH_TYPE) != ModMeshes.NONE) {
            Block.popResource(level, pos, new ItemStack(state.getValue(MESH_TYPE).meshItem()));
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0, 0.6875, 0, 1, 1, 1));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.0625, 0.125, 0.6875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.875, 0.125, 0.6875, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.875, 0, 0.0625, 0.9375, 0.6875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0, 0.875, 0.9375, 0.6875, 0.9375));

        return shape;
    }
}
