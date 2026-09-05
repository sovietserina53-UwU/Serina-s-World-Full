package com.serina.fullEdition;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,SerinasWorldFullEdition.MODID);

    public static final Supplier<CreativeModeTab> ITEMS=CREATIVE_MODE_TAB.register("items",()-> CreativeModeTab.builder().
            title(Component.translatable("items")).
            icon(()->new ItemStack(ModItems.PEBBLE.get())).displayItems((itemDisplayParameters, output) ->
            {
                output.accept(ModItems.SALT);
                output.accept(ModItems.PEBBLE);
                output.accept(ModItems.SHARP_PEBBLE);
                output.accept(ModItems.FRESH_BOWL_OF_LATEX);

            }).build());
    public static final Supplier<CreativeModeTab> BLOCKS=CREATIVE_MODE_TAB.register("blocks",()-> CreativeModeTab.builder().
            title(Component.translatable("blocks")).
            icon(()->new ItemStack(ModBlocks.SALT_BLOCK.get())).displayItems((itemDisplayParameters, output) ->
            {
                output.accept(ModBlocks.SALT_BLOCK);
                output.accept(ModBlocks.SUGAR_CANE_BLOCK);
                output.accept(ModBlocks.RUBBER_LOG);
                output.accept(ModBlocks.STRIPPED_RUBBER_LOG);
                output.accept(ModBlocks.RUBBER_PLANKS);
                output.accept(ModBlocks.RUBBER_WOOD);
                output.accept(ModBlocks.STRIPPED_RUBBER_WOOD);
                output.accept(ModBlocks.CUT_RUBBER_LOG);
                output.accept(ModBlocks.CUT_STRIPPED_RUBBER_LOG);
                output.accept(ModBlocks.RUBBER_LEAVES);
                output.accept(ModBlocks.RUBBER_SAPLING);
                output.accept(ModBlocks.SIMPLE_JAR);

            }).build());
    public static final Supplier<CreativeModeTab> TOOLS=CREATIVE_MODE_TAB.register("tools",()-> CreativeModeTab.builder().
            title(Component.translatable("tools")).
            icon(()->new ItemStack(ModItems.IRON_KNIFE.get())).displayItems((itemDisplayParameters, output) ->
            {
                output.accept(ModItems.SHARP_STICK);
                output.accept(ModItems.RUDIMENTARY_BLADE);
                output.accept(ModItems.IRON_KNIFE);
            }).build());


    public static void register(IEventBus eventBus){CREATIVE_MODE_TAB.register(eventBus);}

}
