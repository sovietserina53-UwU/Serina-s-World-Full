package com.serina.fullEdition.Blocks.Types.NormalBlock;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

public class CorkLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty Ruined=BooleanProperty.create("ruined");
    public CorkLogBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(Ruined,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(Ruined);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        Level level=context.getLevel();
        BlockPos pos=context.getClickedPos();
        if(context.getItemInHand().getItem() instanceof AxeItem)
        {
            CorkLogBlock.popResource(level,pos,new ItemStack(ModItems.CORK_BARK.get()));
            if(this.defaultBlockState().getValue(Ruined))
            {return ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(Ruined,true);}
            return ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState();
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(Ruined,true);
    }
}
