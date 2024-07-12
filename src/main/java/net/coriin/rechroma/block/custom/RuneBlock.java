package net.coriin.rechroma.block.custom;

import net.minecraft.world.level.block.Block;

public class RuneBlock extends Block {

    public int colorIndex;

    public RuneBlock(Properties pProperties, int colorIndex) {
        super(pProperties);
        this.colorIndex = colorIndex;
    }

}
