package com.serina.fullEdition.Items;

import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Items.Types.ToolsAndWeapons.KnifeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS=DeferredRegister.createItems(SerinasWorldFullEdition.MODID);

    public static final DeferredItem<Item> SALT= ITEMS.registerSimpleItem("salt");
    public static final DeferredItem<Item> PEBBLE= ITEMS.registerItem("pebble",properties -> new Item(properties));
    public static final DeferredItem<Item> FRESH_BOWL_OF_LATEX=ITEMS.registerItem("fresh_bowl_of_latex",properties -> new Item(properties));


    public static final DeferredItem<Item> SHARP_PEBBLE= ITEMS.registerItem("sharp_pebble",properties -> new Item(properties));


    public static final DeferredItem<Item> SHARP_STICK= ITEMS.registerItem("sharp_stick",properties->new KnifeItem(properties.sword(ToolMaterial.WOOD,2.0f,1.2f).durability(5)));
    public static final DeferredItem<Item> RUDIMENTARY_BLADE= ITEMS.registerItem("rudimentary_blade",properties -> new KnifeItem(properties.sword(ToolMaterial.STONE,4.0f,1.5f).durability(6)));
    public static final DeferredItem<Item> IRON_KNIFE= ITEMS.registerItem("iron_knife",properties->new KnifeItem(properties.sword(ToolMaterial.IRON,6.0f,1.8f).durability(7)));

    public static void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
