package com.serina.fullEdition.Events;

import com.serina.fullEdition.Blocks.ModBlockEntities;
import com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog.CutRubberLogBE;
import com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog.CutRubberLogBER;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = SerinasWorldFullEdition.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = SerinasWorldFullEdition.MODID, value = Dist.CLIENT)
public class SerinasWorldFullEditionClient {
    public SerinasWorldFullEditionClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        SerinasWorldFullEdition.LOGGER.info("HELLO FROM CLIENT SETUP");
        SerinasWorldFullEdition.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntities.CUT_RUBBER_LOG_BE.get(), CutRubberLogBER::new);
    }

    public static final StandaloneModelKey<QuadCollection> CUT_RUBBER_LOG1 = new StandaloneModelKey<>(new ModelDebugName() {@Override public String debugName() {return "examplemod: Example Model";}});
    @SubscribeEvent
    public static void registerExtra(ModelEvent.RegisterStandalone event)
    {
        event.register(CUT_RUBBER_LOG1, SimpleUnbakedStandaloneModel.quadCollection(Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,"modelosauxiliares/cut_rubber_log1")));

    }
}

