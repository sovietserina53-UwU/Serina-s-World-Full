package com.serina.fullEdition.Menus.BlockMenus.SimpleJar;

import com.serina.fullEdition.Blocks.ModBlocks;
import com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar.SimpleJarBE;
import com.serina.fullEdition.Menus.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import org.jspecify.annotations.Nullable;

public class SimpleJarMenu extends AbstractContainerMenu {

    public final SimpleJarBE blockEntity;
    private final Level level;

    public SimpleJarMenu(int containerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId,inventory,inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()),new ItemStacksResourceHandler(9));
    }
    public SimpleJarMenu(int containerId, @Nullable Inventory inventory, BlockEntity blockEntity,ItemStacksResourceHandler itemStacksResourceHandler)
    {
        super(ModMenus.SIMPLEJAR_MENU.get(),containerId);
        this.blockEntity=(SimpleJarBE) blockEntity;
        this.level=inventory.player.level();

        PlayerInventory(inventory);
        PlayerHotBar(inventory);

        for(int i=0;i<3;i++){
            for(int o=0;o<3;o++)
            {addSlot(new ResourceHandlerSlot(itemStacksResourceHandler,itemStacksResourceHandler::set,o+i*3,91+i*22,40+o*22)
        {@Override public int getMaxStackSize() {return 1;}}
            );}}}


    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),player, ModBlocks.SIMPLE_JAR.get());
    }

    private void PlayerInventory(Inventory inventory)
    {
        for(int i=0;i<3;i++)
        {
            for(int o=0;o<9;o++)
            {
                this.addSlot(new Slot(inventory,o+i*9+9,42+o*18,119+i*18));
            }
        }
    }

    private void PlayerHotBar(Inventory inventory)
    {
        for(int i=0;i<9;i++)
        {
            this.addSlot(new Slot(inventory,i,42+i*18,182));
        }
    }
}
