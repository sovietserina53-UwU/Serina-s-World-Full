package com.serina.fullEdition.JsonGenerators;

import com.serina.fullEdition.Config.SerinasWorldFullEdition;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower RUBBER_TREE=new TreeGrower(SerinasWorldFullEdition.MODID+":rubber_tree", Optional.empty(),Optional.of(ModConfiguredFeature.RUBBER_KEY),Optional.empty());
    public static final TreeGrower CORK_TREE=new TreeGrower(SerinasWorldFullEdition.MODID+":cork_tree", Optional.empty(),Optional.of(ModConfiguredFeature.CORK_KEY),Optional.empty());
}
