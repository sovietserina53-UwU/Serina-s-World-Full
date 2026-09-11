package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AxeItem;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SerinasWorldFullEdition.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).
                add(ModBlocks.getRK(ModBlocks.SALT_BLOCK.get()));
        tag(BlockTags.MINEABLE_WITH_AXE).
                add(ModBlocks.getRK(ModBlocks.SUGAR_CANE_BLOCK.get())).
                add(ModBlocks.getRK(ModBlocks.RUBBER_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.RUBBER_WOOD.get())).
                add(ModBlocks.getRK(ModBlocks.RUBBER_PLANKS.get())).
                add(ModBlocks.getRK(ModBlocks.STRIPPED_RUBBER_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.STRIPPED_RUBBER_WOOD.get())).
                add(ModBlocks.getRK(ModBlocks.CORK_LOG.get()));
        tag(BlockTags.MINEABLE_WITH_HOE).
                add(ModBlocks.getRK(ModBlocks.RUBBER_LEAVES.get()));
        tag(BlockTags.PLANKS).add(ModBlocks.getRK(ModBlocks.RUBBER_PLANKS.get()));
        tag(BlockTags.PREVENTS_NEARBY_LEAF_DECAY).
                add(ModBlocks.getRK(ModBlocks.RUBBER_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.STRIPPED_RUBBER_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.CORK_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.CORK_LOG.get())).
                add(ModBlocks.getRK(ModBlocks.STRIPPED_CORK_LOG.get()))
        ;
    }

}
