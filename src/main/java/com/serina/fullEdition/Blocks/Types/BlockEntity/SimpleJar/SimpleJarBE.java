package com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar;

import com.serina.fullEdition.Blocks.ModBlockEntities;
import com.serina.fullEdition.Helpers.SimpleJarRecipiesHelper;
import com.serina.fullEdition.Menus.BlockMenus.SimpleJar.SimpleJarMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.Nullable;


import java.util.*;

public class SimpleJarBE extends BlockEntity implements MenuProvider {

    public FluidStacksResourceHandler fluids=
            new FluidStacksResourceHandler(2,1000);
    public int progreso=0;
    public List<ItemResource> ListOfItems= new ArrayList<>();


    public final ItemStacksResourceHandler inventorya = new ItemStacksResourceHandler(9)
    {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            SimpleJarBE.this.setChanged();
            if(!level.isClientSide())
            {
                level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
            }
        }
    };

    public void tick(Level level,BlockPos pos,BlockState state)
    {
        SimpleJarRecipiesHelper.RecipeChecker(this);
        /*Map<ItemResource,Integer> current=new HashMap<>();
        Map<Fluid,Integer> currentfluid=new HashMap<>();
        for(int i=0;i<9;i++)
            {
                if(!inventorya.getResource(i).isEmpty())
                {
                    int cantidad= current.getOrDefault(inventorya.getResource(i),0);
                    current.put(inventorya.getResource(i),cantidad+1);
                }
            }
        for(int i=0;i<fluids.size();i++)
        {
            currentfluid.put(fluids.getResource(i).getFluid(),fluids.getAmountAsInt(i));
        }

        for(SimpleJarRecipiesHelper.Recipes recipes:SimpleJarRecipiesHelper.RepicesList())
        {
            if(current.equals(recipes.itemmapin())&&currentfluid.equals(recipes.fluidmapin()))
            {
                progreso++;
                if(progreso>=recipes.time())
                {

                    progreso=0;
                    try (Transaction transaction = Transaction.openRoot())
                    {
                        for(int i=0;i<inventorya.size();i++)
                        {
                            if(!inventorya.getResource(i).isEmpty())
                            {
                                inventorya.extract(inventorya.getResource(i),inventorya.getAmountFrom(inventorya.getResource(i).toStack()),transaction);
                            }
                        }
                        transaction.commit();
                    }
                    if(recipes.itemout()!=null)
                    {try (Transaction transaction = Transaction.openRoot())
                    {
                        for(int i=0;i<recipes.itemout().size();i++)
                        {
                            if(inventorya.getResource(i).isEmpty())
                            {
                                inventorya.insert(recipes.itemout().get(i),1,transaction);
                            }
                        }
                        transaction.commit();
                    }}
                    else {}

                }
            }
        }*/

    }


    public SimpleJarBE(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_JAR_BE.get(), worldPosition, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.child("inventory").ifPresent(inventorya::deserialize);
        input.getInt("progreso");
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putChild("inventory", inventorya);
        output.putInt("progreso",progreso);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("Todas las Hojas Son del Viento...");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SimpleJarMenu(i,inventory,this,this.inventorya);
    }

    public void drops()
    {
        SimpleContainer simpleContainer=new SimpleContainer(inventorya.size());
        for(int i = 0; i< inventorya.size(); i++)
        {
            ItemAccess itemAccess=ItemAccess.forHandlerIndex(inventorya,0);
            simpleContainer.setItem(i,new ItemStack(itemAccess.getResource().getItem(),itemAccess.getAmount()));
        }
        Containers.dropContents(this.level,this.worldPosition,simpleContainer);
    }
}
