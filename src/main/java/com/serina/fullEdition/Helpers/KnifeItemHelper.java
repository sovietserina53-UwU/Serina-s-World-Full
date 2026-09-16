package com.serina.fullEdition.Helpers;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Blocks.Types.NormalBlock.StrippedCorkLogBlock;
import com.serina.fullEdition.Blocks.Types.NormalBlock.RubberLogBlock;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.FlowerBedBlock.FACING;

public class KnifeItemHelper {

    //List of items that get sharp.
    public record SharpenItems(Item input, Item output){}
    public static List<SharpenItems> SharpenItemsList()
    {
            return List.of
            (
                    new SharpenItems(Items.STICK, ModItems.SHARP_STICK.get()),
                    new SharpenItems(ModItems.PEBBLE.get(), ModItems.SHARP_PEBBLE.get())

            );
    }
    //List of the block you click, the block it turns into, the items it may drop and the amount
    public record outputRecord(Predicate<BlockState> blockinput, Function<UseOnContext, BlockState> blockout, Item item, Integer amount){}
    public static List<outputRecord> outputRecordList()
    {
        return List.of
                (
                        new outputRecord(state -> state.is(Blocks.HAY_BLOCK),context -> {return Blocks.AIR.defaultBlockState();},Items.WHEAT,9),
                        new outputRecord(state -> state.is(Blocks.COBBLESTONE),context -> {return Blocks.AIR.defaultBlockState();},ModItems.PEBBLE.get(),4),
                        new outputRecord(state->state.is(Blocks.MELON),context -> {return Blocks.AIR.defaultBlockState();},Items.MELON_SLICE,7),
                        new outputRecord(state->state.is(ModBlocks.SUGAR_CANE_BLOCK),context -> {return Blocks.AIR.defaultBlockState();},Items.SUGAR_CANE,9),
                        new outputRecord(state->state.is(ModBlocks.RUBBER_LOG)&&state.getValue(RubberLogBlock.CUT_ABLE)&&state.getValue(RubberLogBlock.AXIS)== Direction.Axis.Y, context -> {return ModBlocks.CUT_RUBBER_LOG.get().defaultBlockState().setValue(FACING,context.getClickedFace());},null,null),
                        new outputRecord(state->state.is(ModBlocks.STRIPPED_RUBBER_LOG)&&state.getValue(RubberLogBlock.CUT_ABLE)&&state.getValue(RubberLogBlock.AXIS)== Direction.Axis.Y,context -> {return ModBlocks.CUT_STRIPPED_RUBBER_LOG.get().defaultBlockState().setValue(FACING,context.getClickedFace());},null,null),
                        new outputRecord(state -> state.is(ModBlocks.STRIPPED_CORK_LOG)&&state.getValue(StrippedCorkLogBlock.Ruined)==false, context -> {return ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(StrippedCorkLogBlock.Ruined,true).setValue(StrippedCorkLogBlock.AXIS,context.getClickedFace().getAxis());},null,null),
                        new outputRecord(state -> state.is(ModBlocks.CORK_LOG), context -> {return ModBlocks.STRIPPED_CORK_LOG.get().defaultBlockState().setValue(StrippedCorkLogBlock.Ruined,true).setValue(StrippedCorkLogBlock.AXIS, context.getClickedFace().getAxis());},ModItems.CORK_BARK.get(),1)

                );}
}
