package com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar;

import com.mojang.serialization.MapCodec;
import com.serina.fullEdition.Blocks.ModBlockEntities;
import com.serina.fullEdition.Helpers.SimpleJarRecipiesHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class SimpleJarBlock extends BaseEntityBlock {
    public SimpleJarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(level.getBlockEntity(pos) instanceof SimpleJarBE simpleJarBE)
        {
            if(!level.isClientSide())
            {
                if(player.isCrouching())
                {
                    if(itemStack.isEmpty())
                    {player.openMenu(new SimpleMenuProvider(simpleJarBE, Component.literal("Simple_Jar")),pos);}

                }
                else if(!player.isCrouching())
                {
                    SimpleJarRecipiesHelper.BucketInteraction(itemStack,simpleJarBE);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if(level.isClientSide()){return null;}
        return createTickerHelper(type,ModBlockEntities.SIMPLE_JAR_BE.get(), (level1,pos,state,simpleJarBE)->simpleJarBE.tick(level,pos,state));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SimpleJarBE(blockPos,blockState);
    }
}
