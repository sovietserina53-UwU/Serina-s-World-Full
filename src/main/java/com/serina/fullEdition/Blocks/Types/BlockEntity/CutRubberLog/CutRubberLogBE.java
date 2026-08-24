package com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog;

import com.serina.fullEdition.Blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CutRubberLogBE extends BlockEntity {

    public int Latex=10;
    public int state=0;

    public CutRubberLogBE(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CUT_RUBBER_LOG_BE.get(), worldPosition, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.state=input.getIntOr("state",state);
        this.Latex=input.getIntOr("latex",Latex);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("state",state);
        output.putInt("latex",Latex);
    }
}
