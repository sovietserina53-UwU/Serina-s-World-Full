package com.serina.fullEdition.Menus.BlockMenus.SimpleJar;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class SimpleJarScreen extends AbstractContainerScreen<SimpleJarMenu> {

    private static final Identifier MENU_GUI=Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,"textures/gui/simple_jar/simple_jar_gui.png");

    public SimpleJarScreen(SimpleJarMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x=(width-imageWidth)/2;
        int y=(height-imageHeight)/2;

        graphics.blit(RenderPipelines.GUI_TEXTURED,MENU_GUI,x,y,0,0,243,208,256,256);
    }
}
