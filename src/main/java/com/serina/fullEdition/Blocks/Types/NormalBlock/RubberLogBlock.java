package com.serina.fullEdition.Blocks.Types.NormalBlock;

import com.serina.fullEdition.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;

public class RubberLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty CUT_ABLE=BooleanProperty.create("cut_able");
    public RubberLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CUT_ABLE,true).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {

        BlockPos clickedpos=context.getClickedPos();
        Level level=context.getLevel();
        if(context.getItemInHand().getItem() instanceof AxeItem axeItem)
        {if(state.is(ModBlocks.RUBBER_LOG.get())){return ModBlocks.STRIPPED_RUBBER_LOG.get().defaultBlockState().setValue(CUT_ABLE,state.getValue(CUT_ABLE)).setValue(AXIS,state.getValue(AXIS));
        }}

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CUT_ABLE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(CUT_ABLE,false).setValue(AXIS,context.getClickedFace().getAxis());
    }
}
