package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, SerinasWorldFullEdition.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
       // itemModels.generateFlatItem(ModBlocks.SALT_BLOCK.asItem(),ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.SALT.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SHARP_PEBBLE.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PEBBLE.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SHARP_STICK.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RUDIMENTARY_BLADE.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.IRON_KNIFE.get(),ModelTemplates.FLAT_ITEM);


        blockModels.createTrivialCube(ModBlocks.SALT_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RUBBER_PLANKS.get());
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.SUGAR_CANE_BLOCK.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.RUBBER_LOG.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.STRIPPED_RUBBER_LOG.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.RUBBER_WOOD.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.STRIPPED_RUBBER_WOOD.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);

    }
}
