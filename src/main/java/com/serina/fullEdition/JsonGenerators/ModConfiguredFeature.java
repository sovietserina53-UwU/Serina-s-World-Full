package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeature {

    public static final ResourceKey<ConfiguredFeature<?,?>> RUBBER_KEY=registerkey("rubber_key");
    public static final ResourceKey<ConfiguredFeature<?,?>> CORK_KEY=registerkey("cork_key");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> bootstrapContext)
    {
        register(bootstrapContext,RUBBER_KEY,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder
                (
                        BlockStateProvider.simple(ModBlocks.RUBBER_LOG.get()),
                        new StraightTrunkPlacer(10,0,0),
                        BlockStateProvider.simple(ModBlocks.RUBBER_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                        new TwoLayersFeatureSize(1,0,1),
                        BlockStateProvider.simple(Blocks.DIRT)

                ).build());
        register(bootstrapContext,CORK_KEY,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder
                (
                        BlockStateProvider.simple(ModBlocks.CORK_LOG.get()),
                        new StraightTrunkPlacer(10,0,0),
                        BlockStateProvider.simple(ModBlocks.CORK_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                        new TwoLayersFeatureSize(1,0,1),
                        BlockStateProvider.simple(Blocks.DIRT)

                ).build());
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerkey(String name)
    {return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>>key, F feature, FC Configuration)
    {context.register(key,new ConfiguredFeature<>(feature,Configuration));}
}
