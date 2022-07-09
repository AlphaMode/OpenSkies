package me.alphamode.openskies.blocks;

import me.alphamode.openskies.blocks.entity.SieveBlockEntity;
import me.alphamode.openskies.items.MeshItem;
import me.alphamode.openskies.meshes.MeshType;
import me.alphamode.openskies.meshes.OpenMeshes;
import me.alphamode.openskies.state.RegistryProperty;
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

public class SieveBlock extends Block implements EntityBlock {

    public static final RegistryProperty<MeshType> MESH_TYPE = new RegistryProperty<>("mesh", MeshType.class, OpenMeshes.MESH);

    public SieveBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(MESH_TYPE, OpenMeshes.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MESH_TYPE);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SieveBlockEntity(blockPos, blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        SieveBlockEntity sieveBE = (SieveBlockEntity) world.getBlockEntity(pos);
        ItemStack itemStack = player.getItemInHand(hand);
        if (sieveBE == null) return InteractionResult.PASS;
        // Swap the old mesh with the new one if player is holding shift
        if (sieveBE.getMesh() != null && player.isShiftKeyDown()) {
            if (itemStack.getItem() instanceof MeshItem meshItem && itemStack.getCount() == 1) {
                player.setItemInHand(hand, new ItemStack(sieveBE.getMesh().meshItem()));
                sieveBE.setMesh(meshItem.getMeshType());
                return InteractionResult.SUCCESS;
            }
            player.getInventory().placeItemBackInInventory(new ItemStack(sieveBE.getMesh().meshItem()));
            sieveBE.setMesh(null);
            world.setBlockAndUpdate(pos, state.setValue(MESH_TYPE, OpenMeshes.NONE));
            return InteractionResult.SUCCESS;
        }
        if (itemStack.getItem() instanceof MeshItem meshItem && sieveBE.getMesh() == null) {
            sieveBE.setMesh(meshItem.getMeshType());
            world.setBlockAndUpdate(pos, state.setValue(MESH_TYPE, meshItem.getMeshType()));
            itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, blockHitResult);
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
