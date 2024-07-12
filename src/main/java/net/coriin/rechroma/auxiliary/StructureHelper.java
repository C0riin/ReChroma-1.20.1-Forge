package net.coriin.rechroma.auxiliary;

import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class StructureHelper {


    static String[] castingTableTier1Layer0 = new String[]{
            "sssssssssssss",
            "s     s     s",
            "s     s     s",
            "s  sssssss  s",
            "s  s     s  s",
            "s  s  s  s  s",
            "ssss s s ssss",
            "s  s  s  s  s",
            "s  s     s  s",
            "s  sssssss  s",
            "s     s     s",
            "s     s     s",
            "sssssssssssss"};

    static String[] castingTableTier1Layer1 = new String[]{
            "s  s  b  s  s",
            "             ",
            "             ",
            "s  s s s s  s",
            "             ",
            "   s     s   ",
            "b           b",
            "   s     s   ",
            "             ",
            "s  s s s s  s",
            "             ",
            "             ",
            "s  s  b  s  s"};

    static String[] castingTableTier1Layer234 = new String[]{
            "c  c  c  c  c",
            "             ",
            "             ",
            "c           c",
            "             ",
            "             ",
            "c           c",
            "             ",
            "             ",
            "c           c",
            "             ",
            "             ",
            "c  c  c  c  c"};

    static String[] castingTableTier1Layer5 = new String[]{
            "Cees  c  seeC",
            "e           e",
            "e           e",
            "s           s",
            "             ",
            "             ",
            "c           c",
            "             ",
            "             ",
            "s           s",
            "e           e",
            "e           e",
            "Cees  c  seeC"};

    static String[] castingTableTier1Layer6 = new String[]{
            "   geeleeg   ",
            "             ",
            "             ",
            "g           g",
            "e           e",
            "e           e",
            "l           l",
            "e           e",
            "e           e",
            "g           g",
            "             ",
            "             ",
            "   geeleeg   "};




    static String[][] castingTableTier1 = {castingTableTier1Layer0,
                                           castingTableTier1Layer1,
                                           castingTableTier1Layer234,
                                           castingTableTier1Layer234,
                                           castingTableTier1Layer234,
                                           castingTableTier1Layer5,
                                           castingTableTier1Layer6};

    static String[] castingTableTier2Layer0 = new String[]{
            "sqqsqqsqqsqqs",
            "qsssssssssssq",
            "qsssssssssssq",
            "sssssssssssss",
            "qsssssssssssq",
            "qsssssssssssq",
            "ssssss ssssss",
            "qsssssssssssq",
            "qsssssssssssq",
            "sssssssssssss",
            "qsssssssssssq",
            "qsssssssssssq",
            "sqqsqqsqqsqqs"};

    static String[] castingTableTier2Layer1 = new String[]{
            "s  s  b  s  s",
            "             ",
            "  s s s s s  ",
            "s           s",
            "  s t t t s  ",
            "             ",
            "b s t   t s b",
            "             ",
            "  s t t t s  ",
            "s            s",
            "  s s s s s  ",
            "             ",
            "s  s  b  s  s"};

    static String[] castingTableTier2Layer2 = new String[]{
            "c  c  c  c  c",
            "             ",
            "  t t t t t  ",
            "c           c",
            "  t       t  ",
            "             ",
            "c t       t c",
            "             ",
            "  t       t  ",
            "c           c",
            "  t t t t t  ",
            "             ",
            "c  c  c  c  c"};

    static String[][] castingTableTier2 = {castingTableTier2Layer0,
                                           castingTableTier2Layer1,
                                           castingTableTier2Layer2,
                                           castingTableTier1Layer234,
                                           castingTableTier1Layer234,
                                           castingTableTier1Layer5,
                                           castingTableTier1Layer6};




    public static Structure castingTableTier1Structure = new Structure(castingTableTier1, getBlockMapCastingTier1(), getBlockTagMapCastingTier1());

    static Map<String,Block> getBlockMapCastingTier1(){
        Map<String,Block> a = new HashMap<String,Block>();
        //a.put("s", ModBlocks.CRYSTALLINE_STONE.get());
        a.put("g", ModBlocks.ENGRAVED_CRYSTALLINE_STONE.get());
        a.put("b", ModBlocks.EMBOSSED_CRYSTALLINE_STONE.get());
        a.put("e", ModBlocks.CRYSTALLINE_STONE_BEAM.get());
        a.put("c", ModBlocks.CRYSTALLINE_STONE_COLUMN.get());
        a.put("C", Blocks.COAL_BLOCK);
        a.put("l", Blocks.LAPIS_BLOCK);

        return a;
    }
    static Map<String, TagKey<Block>> getBlockTagMapCastingTier1(){
        Map<String,TagKey<Block>> a = new HashMap<String,TagKey<Block>>();

        a.put("s",ModTags.Blocks.CRYSTALLINE_STONE_LIKE);

        return a;
    }


    public static Structure castingTableTier2Structure = new Structure(castingTableTier2, getBlockMapCastingTier2(), getBlockTagMapCastingTier2());

    static Map<String,Block> getBlockMapCastingTier2(){
        Map<String,Block> a = new HashMap<String,Block>();
        //a.put("s", ModBlocks.CRYSTALLINE_STONE.get());
        a.put("g", ModBlocks.ENGRAVED_CRYSTALLINE_STONE.get());
        a.put("b", ModBlocks.EMBOSSED_CRYSTALLINE_STONE.get());
        a.put("e", ModBlocks.CRYSTALLINE_STONE_BEAM.get());
        a.put("t", ModBlocks.CASTING_STAND.get());
        a.put("c", ModBlocks.CRYSTALLINE_STONE_COLUMN.get());
        a.put("C", Blocks.REDSTONE_BLOCK);
        a.put("l", Blocks.GOLD_BLOCK);
        a.put("q", Blocks.QUARTZ_BLOCK);

        return a;
    }
    static Map<String, TagKey<Block>> getBlockTagMapCastingTier2(){
        Map<String,TagKey<Block>> a = new HashMap<String,TagKey<Block>>();

        a.put("s",ModTags.Blocks.CRYSTALLINE_STONE_LIKE);

        return a;
    }

    public static class Structure{
        String[][] pattern3d;

        Map<String, Block> blockMap;
        Map<String, TagKey<Block>> blockTagMap;

        public Structure(String[][] pattern3d, Map<String, Block> a, Map<String, TagKey<Block>> b){
            this.pattern3d = pattern3d;
            this.blockMap = a;
            this.blockTagMap = b;
        }

        public boolean match(Level pLevel, BlockPos startPos, Player pPlayer){
            boolean t = true;

            //Block[][][] blockPattern = parsePattern(this.pattern3d, this.blockMap, this.blockTagMap);

            for(int i = 0; i < pattern3d.length; i++){
                for(int j = 0; j < pattern3d[i].length; j++){
                    for(int k = 0; k < pattern3d[0][0].length(); k++){
                        if(pattern3d[i][j].substring(k,k+1).equals(" ")){ }

                        else if(blockMap.containsKey(pattern3d[i][j].substring(k,k+1))){
                            t &= pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockMap.get(pattern3d[i][j].substring(k,k+1)));

                            if(!pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockMap.get(pattern3d[i][j].substring(k,k+1)))){
                                pPlayer.sendSystemMessage(Component.literal(String.valueOf((-j)+" "+i+" "+k+" "+false)));
                            }

                        }
                        else if(blockTagMap.containsKey(pattern3d[i][j].substring(k,k+1))){
                            t &= pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockTagMap.get(pattern3d[i][j].substring(k,k+1)));

                            if(!pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockTagMap.get(pattern3d[i][j].substring(k,k+1)))){
                                pPlayer.sendSystemMessage(Component.literal(String.valueOf((-j)+" "+i+" "+k+" "+false)));
                            }
                        }
                        else{
                            throw new IllegalArgumentException("No such key");
                        }
                    }
                }
            }
            return t;
        }

        public boolean match(Level pLevel, BlockPos startPos){
            boolean t = true;

            //Block[][][] blockPattern = parsePattern(this.pattern3d, this.blockMap, this.blockTagMap);

            for(int i = 0; i < pattern3d.length; i++){
                for(int j = 0; j < pattern3d[i].length; j++){
                    for(int k = 0; k < pattern3d[0][0].length(); k++){
                        if(pattern3d[i][j].substring(k,k+1).equals(" ")){

                        }
                        else if(blockMap.containsKey(pattern3d[i][j].substring(k,k+1))){
                            t &= pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockMap.get(pattern3d[i][j].substring(k,k+1)));
                        }
                        else if(blockTagMap.containsKey(pattern3d[i][j].substring(k,k+1))){
                            t &= pLevel.getBlockState(WorldHelper.blockPosSum(startPos, new BlockPos(-j,i,k)))
                                    .is(blockTagMap.get(pattern3d[i][j].substring(k,k+1)));
                        }
                        else{
                            throw new IllegalArgumentException("No such key");
                        }
                    }
                }
            }
            return t;
        }

        /*
        Block[][][] parsePattern(String[][] pattern3d,Map<String, Block> blockMap, Map<String, TagKey<Block>> blockTagMap){
            Block[][][] toReturn = new Block[pattern3d.length][pattern3d[0].length][pattern3d[0][0].length()];
            for(int i = 0; i < pattern3d.length; i++){
                for(int j = 0; j < pattern3d[0].length; j++){
                    for(int k = 0; k < pattern3d[0][0].length(); k++){
                        String s = pattern3d[i][j].substring(k,k+1);
                        if(s.equals(" ")){
                            continue;
                        }
                        else if(blockMap.containsKey(s)){
                            toReturn[i][j][k] = blockMap.get(s);
                        }
                        else if(blockTagMap.containsKey(s)){
                            toReturn[i][j][k] = blockTagMap.get(s);
                        }
                    }
                }
            }
            return toReturn;
        }*/

    }


}
