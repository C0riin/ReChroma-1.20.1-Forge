package net.coriin.rechroma.util;

import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class RuneData {
    public static enum runeColor {
        Black,
        Blue,
        Brown,
        Cyan,
        Gray,
        Green,
        Lightblue,
        Lightgray,
        Lime,
        Magenta,
        Orange,
        Pink,
        Purple,
        Red,
        White,
        Yellow

    }

    public static Map<String,runeColor> getRuneColorMap(){
        Map<String,runeColor> colors = new HashMap<String,runeColor>();
        colors.put("Black",runeColor.Black);
        colors.put("Blue",runeColor.Blue);
        colors.put("Brown",runeColor.Brown);
        colors.put("Cyan",runeColor.Cyan);
        colors.put("Gray",runeColor.Gray);
        colors.put("Green",runeColor.Green);
        colors.put("Lightblue",runeColor.Lightblue);
        colors.put("Lightgray",runeColor.Lightgray);
        colors.put("Lime",runeColor.Lime);
        colors.put("Magenta",runeColor.Magenta);
        colors.put("Orange",runeColor.Orange);
        colors.put("Pink",runeColor.Pink);
        colors.put("Purple",runeColor.Purple);
        colors.put("Red",runeColor.Red);
        colors.put("White",runeColor.White);
        colors.put("Yellow",runeColor.Yellow);

        return colors;
    }

    public int colorIndex;
    public int blockPosX;
    public int blockPosY;
    public int blockPosZ;

    public static int indexOfColor(runeColor color) {
        for (int i = 0; i < getRuneColorMap().size(); i++) {
            if(color == runeColor.values()[i]) { return i; }
        }
        throw new IllegalArgumentException("no such color");
    }

    public RuneData(runeColor color, BlockPos pos) {
        this.colorIndex = indexOfColor(color);
        this.blockPosX = pos.getX();
        this.blockPosY = pos.getY();
        this.blockPosZ = pos.getZ();
    }
    public RuneData(int color, int x, int y, int z) {
        this.colorIndex = color;
        this.blockPosX = x;
        this.blockPosY = y;
        this.blockPosZ = z;
    }

}
