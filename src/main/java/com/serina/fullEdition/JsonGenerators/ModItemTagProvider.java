package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SerinasWorldFullEdition.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.PLANKS).add(ModItems.getItemRK(ModBlocks.RUBBER_PLANKS.asItem()));
        tag(ItemTags.PLANKS).add(ModItems.getItemRK(ModBlocks.CORK_PLANKS.asItem()));
        tag(ItemTags.LOGS_THAT_BURN).add(ModItems.getItemRK(ModBlocks.CORK_LOG.asItem()));
        tag(ItemTags.LOGS_THAT_BURN).add(ModItems.getItemRK(ModBlocks.RUBBER_LOG.asItem()));
        tag(ItemTags.LOGS_THAT_BURN).add(ModItems.getItemRK(ModBlocks.STRIPPED_RUBBER_LOG.asItem()));

    }

}
