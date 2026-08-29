package com.serina.fullEdition.Config;

import com.serina.fullEdition.Blocks.ModBlockEntities;
import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Items.ModItems;
import com.serina.fullEdition.Menus.ModMenus;
import com.serina.fullEdition.ModCreativeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


@Mod(SerinasWorldFullEdition.MODID)
public class SerinasWorldFullEdition {

    public static final String MODID = "serinasworldfulledition";

    public static final Logger LOGGER = LogUtils.getLogger();

    public SerinasWorldFullEdition(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(FMLCommonSetupEvent event)
    {}




    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts

    }
}
