package me.alphamode.openskies.blocks.entity;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.meshes.MeshType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SieveBlockEntity extends BlockEntity {

    private MeshType mesh;
    public SieveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OpenBlockEntities.SIEVE, blockPos, blockState);
    }

    public void setMesh(@Nullable MeshType newMesh) {
        this.mesh = newMesh;
    }

    public MeshType getMesh() {
        return mesh;
    }
}
