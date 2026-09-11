package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SALT_BLOCK.get());
        dropSelf(ModBlocks.SUGAR_CANE_BLOCK.get());

        dropSelf(ModBlocks.RUBBER_LOG.get());
        dropSelf(ModBlocks.RUBBER_WOOD.get());
        dropSelf(ModBlocks.RUBBER_PLANKS.get());
        dropSelf(ModBlocks.STRIPPED_RUBBER_LOG.get());
        dropSelf(ModBlocks.STRIPPED_RUBBER_WOOD.get());
        add(ModBlocks.RUBBER_LEAVES.get(),createLeavesDrops(ModBlocks.RUBBER_LEAVES.get(), ModBlocks.RUBBER_SAPLING.get(),0.02f));
        dropSelf(ModBlocks.RUBBER_SAPLING.get());

        dropSelf(ModBlocks.CORK_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CORK_LOG.get());
        dropSelf(ModBlocks.CORK_PLANKS.get());
        add(ModBlocks.CORK_LEAVES.get(),createLeavesDrops(ModBlocks.CORK_LEAVES.get(),ModBlocks.CORK_SAPLING.get(),0.02f));
        dropSelf(ModBlocks.CORK_SAPLING.get());

        dropSelf(ModBlocks.SIMPLE_JAR.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
