package com.serina.fullEdition.Blocks;

import com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog.CutRubberLogBE;
import com.serina.fullEdition.Blocks.Types.BlockEntity.SimpleJar.SimpleJarBE;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES=DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE,SerinasWorldFullEdition.MODID);
    public static final Supplier<BlockEntityType<CutRubberLogBE>> CUT_RUBBER_LOG_BE =BLOCKENTITIES.register("cut_rubber_log_be",()-> new BlockEntityType<>(CutRubberLogBE::new,ModBlocks.CUT_RUBBER_LOG.get(),ModBlocks.CUT_STRIPPED_RUBBER_LOG.get()));
    public static final Supplier<BlockEntityType<SimpleJarBE>> SIMPLE_JAR_BE =BLOCKENTITIES.register("simple_jar_be",()-> new BlockEntityType<>(SimpleJarBE::new,ModBlocks.SIMPLE_JAR.get()));
    public static void register(IEventBus eventBus){BLOCKENTITIES.register(eventBus);}
}
