package com.serina.fullEdition.Menus;

import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Menus.BlockMenus.SimpleJar.SimpleJarMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MOD_MENUS=DeferredRegister.create(Registries.MENU,SerinasWorldFullEdition.MODID);


    public static final DeferredHolder<MenuType<?>,MenuType<SimpleJarMenu>> SIMPLEJAR_MENU=registerMenu("simplejar_menu",SimpleJarMenu::new);

    public static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>,MenuType<T>> registerMenu(String name, IContainerFactory<T> iContainerFactory)
    {return MOD_MENUS.register(name,()-> IMenuTypeExtension.create(iContainerFactory));};

    public static void register(IEventBus eventBus){MOD_MENUS.register(eventBus);}
}
