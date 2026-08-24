package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;


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
        itemModels.generateFlatItem(ModItems.FRESH_BOWL_OF_LATEX.get(),ModelTemplates.FLAT_ITEM);


        blockModels.createTrivialCube(ModBlocks.SALT_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RUBBER_PLANKS.get());
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.SUGAR_CANE_BLOCK.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.RUBBER_LOG.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.STRIPPED_RUBBER_LOG.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.RUBBER_WOOD.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.STRIPPED_RUBBER_WOOD.get(), TexturedModel.COLUMN,TexturedModel.COLUMN_HORIZONTAL);

        blockModels.createPumpkinVariant(ModBlocks.CUT_RUBBER_LOG.get(), new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.CUT_RUBBER_LOG.get(),"_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.CUT_RUBBER_LOG.get(),"_side"))
                .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(ModBlocks.CUT_RUBBER_LOG.get())));
        blockModels.createPumpkinVariant(ModBlocks.CUT_STRIPPED_RUBBER_LOG.get(), new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.CUT_STRIPPED_RUBBER_LOG.get(),"_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.CUT_STRIPPED_RUBBER_LOG.get(),"_side"))
                .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(ModBlocks.CUT_STRIPPED_RUBBER_LOG.get())));
        blockModels.createTrivialCube(ModBlocks.RUBBER_LEAVES.get());
        blockModels.createCrossBlock(ModBlocks.RUBBER_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);


    }

}
