package com.serina.fullEdition.Blocks.Types.NormalBlock;

import com.serina.fullEdition.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CorkLogBlock extends RotatedPillarBlock {

    protected static final VoxelShape SMALL_SHAPEY = Block.box(1,0,1,15,16,15);
    protected static final VoxelShape SMALL_SHAPEX = Block.box(0,1,1,16,15,15);
    protected static final VoxelShape SMALL_SHAPEZ = Block.box(1,1,0,15,15,16);
    protected static final VoxelShape SHAPE= Block.box(0,0,0,16,16,16);

    public static final IntegerProperty STATES=IntegerProperty.create("states",1,2);
    public CorkLogBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(STATES,1));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(state.getValue(STATES)==1&&state.getValue(AXIS)== Direction.Axis.Y)
        {return SMALL_SHAPEY;}
        if(state.getValue(STATES)==1&&state.getValue(AXIS)== Direction.Axis.X)
        {return SMALL_SHAPEX;}
        if(state.getValue(STATES)==1&&state.getValue(AXIS)== Direction.Axis.Z)
        {return SMALL_SHAPEZ;}
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STATES);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);

        if(state.getValue(AXIS)==Direction.Axis.Y&&
                ((level.getBlockState(pos.below()).is(ModBlocks.CORK_LOG.get())||level.getBlockState(pos.above()).is(ModBlocks.CORK_LOG.get())||
                        (level.getBlockState(pos.below()).is(ModBlocks.STRIPPED_CORK_LOG.get())||level.getBlockState(pos.above()).is(ModBlocks.STRIPPED_CORK_LOG.get())))))
        {
            if(state.getValue(STATES)==1){level.setBlock(pos,ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(STATES,2),Block.UPDATE_ALL);System.out.println("i was happty in the haze of a drunken hour");}
            else{level.setBlock(pos,ModBlocks.CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.Y),Block.UPDATE_ALL);}
        }
        else if(state.getValue(AXIS)==Direction.Axis.X&&
                ((level.getBlockState(pos.east()).is(ModBlocks.CORK_LOG.get())||level.getBlockState(pos.west()).is(ModBlocks.CORK_LOG.get())||
                        (level.getBlockState(pos.east()).is(ModBlocks.STRIPPED_CORK_LOG.get())||level.getBlockState(pos.west()).is(ModBlocks.STRIPPED_CORK_LOG.get())))))
        {
            if(state.getValue(STATES)==1){level.setBlock(pos,ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.X).setValue(STATES,2),Block.UPDATE_ALL);System.out.println("i was happty in the haze of a drunken hour");}
            else{level.setBlock(pos,ModBlocks.CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.X),Block.UPDATE_ALL);}
        }
        else if(state.getValue(AXIS)==Direction.Axis.Z&&
                ((level.getBlockState(pos.north()).is(ModBlocks.CORK_LOG.get())||level.getBlockState(pos.south()).is(ModBlocks.CORK_LOG.get())||
                        (level.getBlockState(pos.north()).is(ModBlocks.STRIPPED_CORK_LOG.get())||level.getBlockState(pos.south()).is(ModBlocks.STRIPPED_CORK_LOG.get())))))
        {
            if(state.getValue(STATES)==1){level.setBlock(pos,ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.Z).setValue(STATES,2),Block.UPDATE_ALL);System.out.println("i was happty in the haze of a drunken hour");}
            else{level.setBlock(pos,ModBlocks.CORK_LOG.get().defaultBlockState().setValue(AXIS, Direction.Axis.Z),Block.UPDATE_ALL);}
        }
    }
}
