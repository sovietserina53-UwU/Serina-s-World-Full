package com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog;

import com.ibm.icu.text.Normalizer2;
import com.mojang.blaze3d.vertex.PoseStack;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import com.serina.fullEdition.Events.SerinasWorldFullEditionClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.SimpleModelWrapper;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelLoader;
import org.jspecify.annotations.Nullable;

public class CutRubberLogBER implements BlockEntityRenderer<CutRubberLogBE, CutRubberLogRenderState> {

    //public Identifier cut_rubber_log1=Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,"modelosauxiliares/cut_rubber_log1");

    private final BlockModelResolver blockModelResolver;
    public CutRubberLogBER(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver=context.blockModelResolver();

    }
    public static class rendererHandler extends BlockEntityRenderState {
        public BlockState blockState;
        public BlockPos blockPos;

    };

    @Override
    public CutRubberLogRenderState createRenderState() {
        return new CutRubberLogRenderState();
    }

    @Override
    public void extractRenderState(CutRubberLogBE blockEntity, CutRubberLogRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.blockPos=blockEntity.getBlockPos();
        //QuadCollection model=modelManager.getStandaloneModel(SerinasWorldFullEditionClient.CUT_RUBBER_LOG1);
        ModelManager modelManager= Minecraft.getInstance().getModelManager();
        state.model=modelManager.getStandaloneModel(SerinasWorldFullEditionClient.CUT_RUBBER_LOG1);
        //SimpleModelWrapper.findNonBlockSprites()

        QuadCollection quads = Minecraft.getInstance()
                .getModelManager()
                .getStandaloneModel(SerinasWorldFullEditionClient.CUT_RUBBER_LOG1);

        state.model1 = new SimpleModelWrapper(
                quads,
                false,
                null
        );
        blockEntity.state=state.state;
    }



    @Override
    public void submit(CutRubberLogRenderState cutRubberLogBERState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if(cutRubberLogBERState.state==0)
        {
            //
            // System.out.println("las ganas deee");
            //ModelManager modelManager= Minecraft.getInstance().getModelManager();

            cutRubberLogBERState.model1.getQuads(Direction.NORTH);
            //cutRubberLogBERState.c.submit(poseStack,submitNodeCollector,cutRubberLogBERState.lightCoords,cutRubberLogBERState.lightCoords,1);

        }

    }
}
