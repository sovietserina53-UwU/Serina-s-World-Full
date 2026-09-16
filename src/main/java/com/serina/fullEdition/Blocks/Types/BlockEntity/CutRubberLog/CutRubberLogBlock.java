package com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog;

import com.mojang.serialization.MapCodec;
import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Blocks.Types.NormalBlock.RubberLogBlock;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import org.jspecify.annotations.Nullable;

import static net.minecraft.world.level.block.CarvedPumpkinBlock.FACING;


public class CutRubberLogBlock extends BaseEntityBlock {

    public CutRubberLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if(!level.isClientSide())
        {
            if(level.getBlockEntity(pos) instanceof CutRubberLogBE cutRubberLogBE)
            {
                if(cutRubberLogBE.state<3)
                {
                    cutRubberLogBE.state++;
                    System.out.println(cutRubberLogBE.state);
                    cutRubberLogBE.setChanged();
                    cutRubberLogBE.getLevel().sendBlockUpdated(pos,state,state,Block.UPDATE_ALL);
                }
            }
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide())
        {
            if(level.getBlockEntity(pos) instanceof CutRubberLogBE cutRubberLogBE)
            {
                if(cutRubberLogBE.Latex>0&&cutRubberLogBE.state==3&&itemStack.is(Items.BOWL))
                {
                    cutRubberLogBE.state=0;
                    cutRubberLogBE.Latex--;
                    itemStack.shrink(1);
                    player.addItem(new ItemStack(ModItems.FRESH_BOWL_OF_LATEX.get()));
                    cutRubberLogBE.setChanged();
                    cutRubberLogBE.getLevel().sendBlockUpdated(pos,state,state,Block.UPDATE_ALL);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }


    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        for(int i=1;i<10;i++)
        {
            BlockPos posAbove=pos.above(i);BlockState stateAbove=level.getBlockState(posAbove);
            BlockPos posBelow=pos.below(i);BlockState stateBelow=level.getBlockState(posBelow);
            if(stateAbove.is(ModBlocks.RUBBER_LOG)||stateAbove.is(ModBlocks.STRIPPED_RUBBER_LOG))
            {
                if(stateAbove.getValue(RubberLogBlock.CUT_ABLE))
                {level.setBlock(posAbove,stateAbove.setValue(RubberLogBlock.CUT_ABLE,false),Block.UPDATE_ALL);}
            }
            if(stateBelow.is(ModBlocks.RUBBER_LOG)||stateBelow.is(ModBlocks.STRIPPED_RUBBER_LOG))
            {
                if(stateBelow.getValue(RubberLogBlock.CUT_ABLE))
                {level.setBlock(posBelow,stateBelow.setValue(RubberLogBlock.CUT_ABLE,false),Block.UPDATE_ALL);}
            }
            else{}

        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CutRubberLogBE(blockPos,blockState);
    }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }
}
