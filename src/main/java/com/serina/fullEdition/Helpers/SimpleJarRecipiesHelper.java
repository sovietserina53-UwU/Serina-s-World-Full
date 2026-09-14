package com.serina.fullEdition.Helpers;

import com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar.SimpleJarBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

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


    public record ItemandFluid(Fluid fluids,Item bucket,Item bottle){};

    public static ItemStack potions=new ItemStack(Items.POTION);




    public static List<ItemandFluid> Recipients()
    {
        return List.of
                (
                        new ItemandFluid(Fluids.LAVA,Items.LAVA_BUCKET,Items.MAGMA_BLOCK),
                        new ItemandFluid(Fluids.WATER,Items.WATER_BUCKET,null)

                );
    }

    public static void RecipientInteraction(ItemStack stack,Level level,BlockPos pos,Player player)
    {
        if(level.getBlockEntity(pos) instanceof SimpleJarBE simpleJarBE)
        {
            PotionContents awa=stack.get(DataComponents.POTION_CONTENTS);

            boolean tristeza=false;
            if(awa!=null&&awa.is(Potions.WATER)&&simpleJarBE.TotalFluid<=900)
            {
                for(int i=0;i<simpleJarBE.fluids.size();i++)
                {
                    if(simpleJarBE.fluids.getResource(i).is(Fluids.WATER))
                    {
                        try(Transaction transaction=Transaction.openRoot())
                        {
                            simpleJarBE.fluids.insert(i,FluidResource.of(Fluids.WATER),100,transaction);
                            if(!player.isCreative()){stack.shrink(1);player.addItem(new ItemStack(Items.GLASS_BOTTLE));}else{}tristeza=true;transaction.commit();
                        }
                    }
                    else{}
                }
                for(int i=0;i<simpleJarBE.fluids.size()&&!tristeza;i++)
                {
                    if(!simpleJarBE.fluids.getResource(i).is(Fluids.WATER)&&simpleJarBE.fluids.getResource(i).isEmpty())
                    {
                        try(Transaction transaction=Transaction.openRoot())
                        {
                            simpleJarBE.fluids.insert(i,FluidResource.of(Fluids.WATER),100,transaction);
                            if(!player.isCreative()){stack.shrink(1);player.addItem(new ItemStack(Items.GLASS_BOTTLE));}else{}transaction.commit();break;
                        }
                    }
                    else {}
                }
            }
            else if(stack.is(Items.STICK))
            {
                for(int i=0;i<simpleJarBE.fluids.size();i++)
                {
                    System.out.println(simpleJarBE.fluids.getResource(i)+": "+simpleJarBE.fluids.getAmountAsInt(i));
                }
                System.out.println("este es el total conchatumae po: "+simpleJarBE.TotalFluid);
            }
            else if(stack.is(Items.GLASS_BOTTLE)&&simpleJarBE.TotalFluid>0)
            {
                for(int i=simpleJarBE.fluids.size()-1;i>=0;i--)
                {
                    if(!simpleJarBE.fluids.getResource(i).isEmpty())
                    {
                        System.out.println("the heck i am");
                        try(Transaction transaction=Transaction.openRoot())
                        {
                            if(!player.isCreative())
                            {ItemStack waterbottle=new ItemStack(Items.POTION);waterbottle.set(DataComponents.POTION_CONTENTS,new PotionContents(Potions.WATER));for (ItemandFluid itemandFluid : Recipients()){if (simpleJarBE.fluids.getResource(i).is(itemandFluid.fluids())) {stack.shrink(1);if(itemandFluid.bottle==null){player.addItem(waterbottle);}else{player.addItem(new ItemStack(itemandFluid.bottle));}}}}else{}
                            simpleJarBE.fluids.extract(i,FluidResource.of(simpleJarBE.fluids.getResource(i).getFluid()),100,transaction);transaction.commit();break;
                        }
                    }
                    else {
                        System.out.println("where did you goooooo");
                    }
                }
            }
            else if(stack.is(Items.BUCKET)&&simpleJarBE.TotalFluid==1000&&simpleJarBE.fluids.getAmountAsInt(0)==1000)
            {
                System.out.println("filtro 1");
                try(Transaction transaction=Transaction.openRoot())
                {
                    System.out.println("filtro 2");
                    if(!player.isCreative()){for (ItemandFluid itemandFluid : Recipients()){if (simpleJarBE.fluids.getResource(0).is(itemandFluid.fluids())) {stack.shrink(1);player.addItem(new ItemStack(itemandFluid.bucket));}}}else{}
                    simpleJarBE.fluids.extract(0,FluidResource.of(simpleJarBE.fluids.getResource(0).getFluid()),1000,transaction);transaction.commit();
                }
            }
            else
            {
                for(ItemandFluid itemandFluid:Recipients())
                {
                    if(stack.is(itemandFluid.bottle)&&simpleJarBE.TotalFluid<=900)
                    {
                        for(int i=0;i<simpleJarBE.fluids.size();i++)
                        {
                            if(simpleJarBE.fluids.getResource(i).is(itemandFluid.fluids))
                            {
                                try(Transaction transaction=Transaction.openRoot())
                                {
                                    simpleJarBE.fluids.insert(i,FluidResource.of(itemandFluid.fluids),100,transaction);
                                    if(!player.isCreative()){stack.shrink(1);player.addItem(new ItemStack(Items.GLASS_BOTTLE));}else{}tristeza=true;transaction.commit();
                                }
                            }
                            else{}
                        }
                        for(int i=0;i<simpleJarBE.fluids.size()&&!tristeza;i++)
                        {
                            if(!simpleJarBE.fluids.getResource(i).is(itemandFluid.fluids)&&simpleJarBE.fluids.getResource(i).isEmpty())
                            {
                                try(Transaction transaction=Transaction.openRoot())
                                {
                                    simpleJarBE.fluids.insert(i,FluidResource.of(itemandFluid.fluids),100,transaction);
                                    if(!player.isCreative()){stack.shrink(1);player.addItem(new ItemStack(Items.GLASS_BOTTLE));}else{}transaction.commit();break;
                                }
                            }
                            else {}
                        }
                    }
                    else if(stack.is(itemandFluid.bucket)&&simpleJarBE.TotalFluid==0)
                    {
                        try(Transaction transaction=Transaction.openRoot())
                        {
                            simpleJarBE.fluids.insert(0,FluidResource.of(itemandFluid.fluids),1000,transaction);
                            if(!player.isCreative()){stack.shrink(1);player.addItem(new ItemStack(Items.BUCKET));}else{};transaction.commit();
                        }
                    }
                    else {}
                }
            }
            System.out.println("apdad");


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
