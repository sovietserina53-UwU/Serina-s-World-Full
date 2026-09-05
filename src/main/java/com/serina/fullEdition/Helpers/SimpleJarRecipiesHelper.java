package com.serina.fullEdition.Helpers;

import com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar.SimpleJarBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleJarRecipiesHelper {

    public record Recipes(Map<ItemResource,Integer> itemmapin, Map<FluidResource,Integer> fluidmapin, int time, List<ItemResource> itemout, FluidResource fluidout,int fluidoutamount){}

    public static List<Recipes> RepicesList()
    {
        return List.of
                (
                        new Recipes(Map.of(ItemResource.of(Items.SUGAR),2,ItemResource.of(Items.APPLE),1),Map.of(FluidResource.of(Fluids.WATER),100),120,null,FluidResource.of(Fluids.WATER),100),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),1),null,120,List.of(ItemResource.of(Items.ROTTEN_FLESH)),null,0)
                );
    }

    public record ItemandFluid(Item items, Fluid fluids){};

    public static List<ItemandFluid> BOTTLES()
    {
        return List.of(new ItemandFluid(Items.POTION,Fluids.WATER));
    }

    public static List<ItemandFluid> BUCKETS()
    {
        return List.of(new ItemandFluid(Items.WATER_BUCKET,Fluids.WATER));
    }

    public static void BucketInteraction(ItemStack itemStack, Level level, BlockPos pos, Player player)
    {
        boolean interact=true;
        PotionContents potions=itemStack.get(DataComponents.POTION_CONTENTS);

        if(level.getBlockEntity(pos) instanceof SimpleJarBE simpleJarBE)
        {
            if(!level.isClientSide())
            {
                if(itemStack.is(Items.STICK))
                {
                    System.out.println(simpleJarBE.fluids.getResource(0));
                    System.out.println(simpleJarBE.fluids.getAmountAsInt(0));
                    System.out.println(simpleJarBE.fluids.getResource(1));
                    System.out.println(simpleJarBE.fluids.getAmountAsInt(1));
                    RecipeChecker(simpleJarBE);
                }
                for(int i=0;i<simpleJarBE.fluids.size()&&interact;i++) {
                    for (ItemandFluid itemandFluid : BUCKETS()) {

                        if (simpleJarBE.fluids.getAmountAsInt(i)==0&&itemStack.is(itemandFluid.items)) {
                            try (Transaction transaction = Transaction.openRoot()) {
                                simpleJarBE.fluids.insert(FluidResource.of(itemandFluid.fluids), 1000, transaction);
                                transaction.commit();
                                System.out.println("esto es la interaccion de meter fluido con una cubeta");
                                itemStack.shrink(1);
                                player.addItem(new ItemStack(Items.BUCKET));
                                interact=false;
                            }

                        }
                        else if (simpleJarBE.fluids.getAmountAsInt(i)==1000&&itemStack.is(Items.BUCKET)) {
                            try (Transaction transaction = Transaction.openRoot()) {
                                simpleJarBE.fluids.extract(FluidResource.of(itemandFluid.fluids), 1000, transaction);
                                transaction.commit();
                                System.out.println("esto es la interaccion de sacar fluido con una cubeta");
                                itemStack.shrink(1);
                                player.addItem(new ItemStack(itemandFluid.items));
                                interact=false;
                            }
                        }
                    }
                    for (ItemandFluid itemandFluid : BOTTLES()) {
                        if (simpleJarBE.fluids.getResource(i).equals(itemandFluid.fluids) && simpleJarBE.fluids.getAmountAsInt(i) <= 900 && itemStack.is(itemandFluid.items)) {
                            try (Transaction transaction = Transaction.openRoot()) {
                                simpleJarBE.fluids.insert(FluidResource.of(itemandFluid.fluids), 100, transaction);
                                transaction.commit();
                                System.out.println("esto es la interaccion de meter fluido con una botella en un slot que ya tiene de ese contenido");
                                itemStack.shrink(1);
                                player.addItem(new ItemStack(Items.GLASS_BOTTLE));
                                interact=false;
                            }
                        } else if (simpleJarBE.fluids.getAmountAsInt(i) == 0 && itemStack.is(itemandFluid.items)) {
                            try (Transaction transaction = Transaction.openRoot()) {
                                simpleJarBE.fluids.insert(FluidResource.of(itemandFluid.fluids), 100, transaction);
                                transaction.commit();
                                System.out.println("esto es la interaccion de meter fluido con una botella en un slot vacio");
                                itemStack.shrink(1);
                                player.addItem(new ItemStack(Items.GLASS_BOTTLE));
                                interact=false;
                            }
                        } else if (simpleJarBE.fluids.getAmountAsInt(i) >= 100 && itemStack.is(Items.GLASS_BOTTLE)) {
                            try (Transaction transaction = Transaction.openRoot()) {
                                simpleJarBE.fluids.extract(FluidResource.of(itemandFluid.fluids), 100, transaction);
                                transaction.commit();
                                System.out.println("esto es la interaccion de meter sacar con una botella");
                                itemStack.shrink(1);
                                player.addItem(new ItemStack(itemandFluid.items));
                                interact=false;
                            }
                        }
                    }
                }
            }
        }

    }
    public static void RecipeChecker(SimpleJarBE simpleJarBE)
    {
        boolean ask=true;
        Map<FluidResource,Integer> currentFluid=new HashMap<>();
        Map<ItemResource,Integer> currentInventory=new HashMap<>();
        for(int i=0;i<simpleJarBE.fluids.size()&&ask;i++)
        {
            if(!simpleJarBE.fluids.getResource(i).isEmpty())
            {
                currentFluid.put(simpleJarBE.fluids.getResource(i),simpleJarBE.fluids.getAmountAsInt(i));
            }
        }
        for(int i=0;i<9;i++)
        {
            if(!simpleJarBE.inventorya.getResource(i).isEmpty())
            {
                int amount=currentInventory.getOrDefault(simpleJarBE.inventorya.getResource(i),0);
                currentInventory.put(simpleJarBE.inventorya.getResource(i),amount+1);
            }
        }
        //System.out.println("currentfluid: "+currentFluid);
        //System.out.println("currentinventorya: "+currentInventory);
        for(Recipes recipes:RepicesList())
        {
            if(simpleJarBE.progreso<recipes.time())
            {
                if(currentFluid.equals(recipes.fluidmapin())&&currentInventory.equals(recipes.itemmapin()))
                {
                    System.out.println("i'm gay");
                    simpleJarBE.progreso++;
                }
                else if(currentInventory.equals(recipes.itemmapin)&&recipes.fluidmapin==null)
                {
                    System.out.println("i'm gay but not wet");
                    simpleJarBE.progreso++;
                }
                else if(currentFluid.equals(recipes.fluidmapin)&&recipes.itemmapin==null)
                {
                    System.out.println("i'm gay and wet");
                    simpleJarBE.progreso++;
                }
            }
            else
            {
                System.out.println("itemout null? " + (recipes.itemout == null));
                System.out.println("fluidout null? " + (recipes.fluidout == null));

                if(recipes.itemout!=null&&recipes.fluidout!=null)
                {
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<simpleJarBE.fluids.size();i++)
                        {
                            if(!simpleJarBE.fluids.getResource(i).isEmpty())
                            {
                                simpleJarBE.fluids.extract(simpleJarBE.fluids.getResource(i),simpleJarBE.fluids.getAmountAsInt(i),transaction);
                            }
                        }
                        transaction.commit();
                    }
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        simpleJarBE.fluids.insert(recipes.fluidout, recipes.fluidoutamount, transaction);
                        transaction.commit();
                    }
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<9;i++)
                        {
                            if(!simpleJarBE.inventorya.getResource(i).isEmpty())
                            {
                                simpleJarBE.inventorya.extract(simpleJarBE.inventorya.getResource(i),simpleJarBE.inventorya.getAmountAsInt(i),transaction);
                            }
                        }
                        transaction.commit();
                    }
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<recipes.itemout.size();i++)
                        {
                            simpleJarBE.inventorya.insert(recipes.itemout.get(i),i,transaction);
                        }
                        transaction.commit();
                        ask=false;
                        simpleJarBE.progreso=0;
                    }
                }
                else if(recipes.itemout!=null&&recipes.fluidout==null)
                {
                    System.out.println("sweet thing");
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<9;i++)
                        {
                            if(!simpleJarBE.inventorya.getResource(i).isEmpty())
                            {
                                simpleJarBE.inventorya.extract(simpleJarBE.inventorya.getResource(i),simpleJarBE.inventorya.getAmountAsInt(i),transaction);
                            }
                        }
                        transaction.commit();
                    }
                    System.out.println("this shite's done");
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<recipes.itemout.size();i++)
                        {
                            simpleJarBE.inventorya.insert(recipes.itemout.get(i),1,transaction);
                            System.out.println("cannabis");
                        }
                        transaction.commit();
                    }
                    ask=false;
                    simpleJarBE.progreso=0;
                }
                else if(recipes.itemout==null&&recipes.fluidout!=null)
                {
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        for(int i=0;i<simpleJarBE.fluids.size();i++)
                        {
                            if(!simpleJarBE.fluids.getResource(i).isEmpty())
                            {simpleJarBE.fluids.extract(simpleJarBE.fluids.getResource(i),simpleJarBE.fluids.getAmountAsInt(i),transaction);}
                        }
                        transaction.commit();
                    }
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        simpleJarBE.fluids.insert(recipes.fluidout, recipes.fluidoutamount, transaction);
                        transaction.commit();
                    }
                    ask=false;
                    simpleJarBE.progreso=0;
                }
                else{
                    System.out.println("kool thing");
                }

                /*for(int i=0;i<simpleJarBE.inventorya.size();i++)
                {
                    if(!simpleJarBE.inventorya.getResource(i).isEmpty())
                    {
                        try (Transaction transaction = Transaction.openRoot())
                        {
                            simpleJarBE.inventorya.extract(simpleJarBE.inventorya.getResource(i),1,transaction);
                            transaction.commit();
                            System.out.println("llegue fome la wea");
                        }
                    }
                    if(recipes.itemout!=null)
                    {
                        try (Transaction transaction = Transaction.openRoot())
                        {
                            simpleJarBE.inventorya.insert(recipes.itemout.get(i),i,transaction);
                            transaction.commit();
                            System.out.println("y aca esta el break_csm");
                            break;
                        }
                    }
                }
                for(int i=0;i<simpleJarBE.fluids.size();i++)
                {
                    if(!simpleJarBE.fluids.getResource(i).isEmpty())
                    {
                        try (Transaction transaction = Transaction.openRoot())
                        {
                            simpleJarBE.fluids.extract(simpleJarBE.fluids.getResource(i),simpleJarBE.fluids.getAmountAsInt(i),transaction);
                            transaction.commit();
                        }
                    }
                    if(recipes.fluidout!=null)
                    {
                        try (Transaction transaction = Transaction.openRoot())
                        {
                            simpleJarBE.fluids.insert(recipes.fluidout,1,transaction);
                            transaction.commit();
                            break;
                        }
                    }
                }
                if(currentFluid.equals(recipes.fluidmapin())&&currentInventory.equals(recipes.itemmapin()))
                {

                    /*for(int i=0;i<simpleJarBE.inventorya.size();i++)
                    {
                        if(recipes.itemout!=null)
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.inventorya.insert(recipes.itemout.get(i),i,transaction);
                                transaction.commit();
                                break;
                            }
                        }
                    }
                    for(int i=0;i<simpleJarBE.fluids.size();i++)
                    {
                        if(recipes.fluidout!=null)
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.fluids.insert(recipes.fluidout,1,transaction);
                                transaction.commit();
                                break;
                            }
                        }
                    }
                }
                else if(currentInventory.equals(recipes.itemmapin)&&recipes.fluidmapin==null)
                {
                    for(int i=0;i<simpleJarBE.inventorya.size();i++)
                    {
                        if(!simpleJarBE.inventorya.getResource(i).isEmpty())
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.inventorya.extract(simpleJarBE.inventorya.getResource(i),1,transaction);
                                transaction.commit();
                            }
                        }
                        if(recipes.itemout!=null)
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.inventorya.insert(recipes.itemout.get(i),i,transaction);
                                transaction.commit();
                                break;
                            }
                        }
                    }
                }
                else if(currentFluid.equals(recipes.fluidmapin)&&recipes.itemmapin==null)
                {
                    for(int i=0;i<simpleJarBE.fluids.size();i++)
                    {
                        if(!simpleJarBE.fluids.getResource(i).isEmpty())
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.fluids.extract(simpleJarBE.fluids.getResource(i),simpleJarBE.fluids.getAmountAsInt(i),transaction);
                                transaction.commit();
                            }
                        }
                        if(recipes.fluidout!=null)
                        {
                            try (Transaction transaction = Transaction.openRoot())
                            {
                                simpleJarBE.fluids.insert(recipes.fluidout,1,transaction);
                                transaction.commit();
                                break;
                            }
                        }
                }

            }*/

        }

    }
    }
}
