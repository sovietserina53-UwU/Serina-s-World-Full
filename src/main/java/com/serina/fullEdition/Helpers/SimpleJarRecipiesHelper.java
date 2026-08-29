package com.serina.fullEdition.Helpers;

import com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar.SimpleJarBE;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

import java.util.List;
import java.util.Map;

public class SimpleJarRecipiesHelper {

    public record Recipes(Map<ItemResource,Integer> mapp,int time,List<ItemResource> output,Map<FluidResource,Integer> fluids){}

    public static List<Recipes> RepicesList()
    {
        return List.of
                (
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),1),120,List.of(ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),2),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),3),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),4),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),5),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),6),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),7),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),8),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.BEEF),9),120,List.of(ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH),ItemResource.of(Items.ROTTEN_FLESH)),null),
                        new Recipes(Map.of(ItemResource.of(Items.APPLE),1,ItemResource.of(Items.SUGAR),2),120,null,Map.of(FluidResource.of(Fluids.WATER),250))
                );
    }

    public static void BucketInteraction(ItemStack itemStack, SimpleJarBE simpleJarBE)
    {
        PotionContents potions=itemStack.get(DataComponents.POTION_CONTENTS);

        for(int i=0;i<simpleJarBE.fluids.size();i++)
        {
            if(simpleJarBE.fluids.getAmountAsInt(i)>=0&&simpleJarBE.fluids.getAmountAsInt(i)<=900)
            {
                if(potions!=null&&potions.is(Potions.WATER))
                {
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        simpleJarBE.fluids.insert(FluidResource.of(Fluids.WATER),100,transaction);
                        transaction.commit();
                        System.out.println(simpleJarBE.fluids.getResource(i));
                    }
                }
            }
            else if(simpleJarBE.fluids.getAmountAsInt(i)==0)
            {
                if(itemStack.is(Items.WATER_BUCKET))
                {
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        simpleJarBE.fluids.insert(FluidResource.of(Fluids.WATER),1000,transaction);
                        transaction.commit();
                        System.out.println(simpleJarBE.fluids.getResource(i));
                    }
                }
            }
            else if(simpleJarBE.fluids.getAmountAsInt(i)==1000)
            {
                if(itemStack.is(Items.BUCKET))
                {
                    try(Transaction transaction=Transaction.openRoot())
                    {
                        simpleJarBE.fluids.extract(FluidResource.of(simpleJarBE.fluids.getResource(i).getFluid()),1000,transaction);
                        transaction.commit();
                        System.out.println(simpleJarBE.fluids.getResource(i));
                    }
                }
            }
        }
    }


}
