package com.serina.fullEdition.Blocks;

import com.mojang.serialization.MapCodec;
import com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog.CutRubberLogBlock;
import com.serina.fullEdition.Blocks.Types.NormalBlock.RubberLogBlock;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import com.serina.fullEdition.JsonGenerators.ModConfiguredFeature;
import com.serina.fullEdition.JsonGenerators.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS= DeferredRegister.createBlocks(SerinasWorldFullEdition.MODID);

    public static final DeferredBlock<Block> SALT_BLOCK=registerBlock("salt_block", properties -> new FallingBlock(properties.sound(SoundType.SAND).strength(1)) {@Override protected MapCodec<? extends FallingBlock> codec() {return null;}@Override public int getDustColor(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {return 0;}});
    public static final DeferredBlock<Block> SUGAR_CANE_BLOCK=registerBlock("sugar_cane_block",properties -> new RotatedPillarBlock(properties.sound(SoundType.HARD_CROP).strength(1)));
    public static final DeferredBlock<Block> RUBBER_LOG=registerBlock("rubber_log",properties -> new RubberLogBlock(properties.sound(SoundType.WOOD).strength(1)));
    public static final DeferredBlock<Block> CUT_RUBBER_LOG=registerBlock("cut_rubber_log",properties -> new CutRubberLogBlock(properties.sound(SoundType.WOOD).strength(1).noLootTable().randomTicks()));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_LOG=registerBlock("stripped_rubber_log",properties -> new RubberLogBlock(properties.sound(SoundType.WOOD).strength(1)));
    public static final DeferredBlock<Block> CUT_STRIPPED_RUBBER_LOG=registerBlock("cut_stripped_rubber_log",properties -> new CutRubberLogBlock(properties.sound(SoundType.WOOD).strength(1).noLootTable().randomTicks()));
    public static final DeferredBlock<Block> RUBBER_WOOD=registerBlock("rubber_wood",properties -> new RotatedPillarBlock(properties.sound(SoundType.WOOD).strength(1)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_WOOD=registerBlock("stripped_rubber_wood",properties -> new RotatedPillarBlock(properties.sound(SoundType.WOOD).strength(1)));
    public static final DeferredBlock<Block> RUBBER_PLANKS=registerBlock("rubber_planks",properties -> new Block(properties.sound(SoundType.WOOD).strength(1)));
    public static final DeferredBlock<Block> RUBBER_LEAVES=registerBlock("rubber_leaves",properties -> new LeavesBlock(0.1f,properties) {@Override public MapCodec<? extends LeavesBlock> codec() {return null;}@Override protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {}});
    public static final DeferredBlock<Block> RUBBER_SAPLING=registerBlock("rubber_sapling",properties -> new SaplingBlock(ModTreeGrowers.RUBBER_TREE,properties.noCollision()));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block)
    {
        DeferredBlock<T> Ret=BLOCKS.registerBlock(name,block);
        registerBlockItem(name,Ret);
        return Ret;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.registerItem(name,properties -> new BlockItem(block.get(),properties.useBlockDescriptionPrefix()));
    }
    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static void register(IEventBus eventBus){BLOCKS.register(eventBus);}
}
