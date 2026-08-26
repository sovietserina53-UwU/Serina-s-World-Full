package com.serina.fullEdition.Blocks.Types.BlockEntity.CutRubberLog;

import com.serina.fullEdition.Blocks.ModBlockEntities;
import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CutRubberLogBE extends BlockEntity {

    public final Identifier cut_rubber_log1=Identifier.fromNamespaceAndPath(SerinasWorldFullEdition.MODID,"modelosauxiliares/cut_rubber_log1");
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
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
