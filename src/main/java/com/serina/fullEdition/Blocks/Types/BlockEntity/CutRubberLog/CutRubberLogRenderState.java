package com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog;

import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CutRubberLogRenderState extends BlockEntityRenderState {
    public int Latex;
    public int state;
    //public final Identifier cut_rubber_log1=Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,"modelosauxiliares/cut_rubber_log1");
    public QuadCollection model;
    public BlockStateModelPart model1;

}
