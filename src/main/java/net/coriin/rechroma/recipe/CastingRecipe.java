package net.coriin.rechroma.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.logging.LogUtils;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.util.RuneData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import com.google.gson.JsonArray;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static net.minecraft.world.item.crafting.ShapedRecipe.itemStackFromJson;


public class CastingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    public RuneData[] runes;
    public boolean isHaveRunes;
    private static final Logger LOGGER = LogUtils.getLogger();
    public BlockPos castingTablePos;
    public int tier;
    public int craftTime;

    public CastingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, int craftTime, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.isHaveRunes = false;
        this.tier = 0;
        this.craftTime = craftTime;


    }

    public CastingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, int craftTime, ResourceLocation id, RuneData[] runes) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.runes = runes;
        this.isHaveRunes = true;
        this.tier = 1;
        this.craftTime = craftTime;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level level) {
        if (level.isClientSide) {
            return false;
        }


        boolean t = true;

        /*
        if(runes.length > 0){

        }*/

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < inputItems.size() / 3; j++) {
                t &= inputItems.get(i * 3 + j).test(pContainer.getItem(i * 3 + j));
            }
        }


        return t;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    static String[] patternFromJson(JsonArray pPatternArray) {
        String[] astring = new String[pPatternArray.size()];
        if (astring.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + 3 + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < astring.length; ++i) {
                String s = GsonHelper.convertToString(pPatternArray.get(i), "pattern[" + i + "]");
                if (s.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + 3 + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }
            //LOGGER.error(Arrays.toString(astring));
            return astring;
        }
    }

    static RuneData[] runeDataFromJson(JsonArray pRuneDataArray) {
        RuneData[] runes = new RuneData[pRuneDataArray.size()];
        for (int i = 0; i < runes.length; ++i) {
            JsonObject s = pRuneDataArray.get(i).getAsJsonObject();
            int runeColorIndex = GsonHelper.getAsInt(s, "colorIndex");
            int runeX = GsonHelper.getAsInt(s, "x");
            int runeY = GsonHelper.getAsInt(s, "y");
            int runeZ = GsonHelper.getAsInt(s, "z");
            runes[i] = new RuneData(RuneData.runeColor.values()[runeColorIndex], new BlockPos(runeX, runeY, runeZ));
            //LOGGER.error(("json_test"+ runeColorIndex+" "+runeX+" "+runeY+" "+runeZ).toString());

        }
        return runes;
    }

    static Map<String, Ingredient> keyFromJsonCasting(JsonObject pKeyEntry) {
        Map<String, Ingredient> map = Maps.newHashMap();
        Iterator var2 = pKeyEntry.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<String, JsonElement> entry = (Map.Entry) var2.next();
            if (((String) entry.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put((String) entry.getKey(), Ingredient.fromJson((JsonElement) entry.getValue(), false));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    private static int firstNonSpace(String pEntry) {
        int i;
        for (i = 0; i < pEntry.length() && pEntry.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int lastNonSpace(String pEntry) {
        int i;
        for (i = pEntry.length() - 1; i >= 0 && pEntry.charAt(i) == ' '; --i) {
        }

        return i;
    }

    static NonNullList<Ingredient> dissolvePattern(String[] pPattern, Map<String, Ingredient> pKeys) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(9, Ingredient.EMPTY);

        Set<String> set = Sets.newHashSet(pKeys.keySet());
        set.remove(" ");

        //LOGGER.error(Arrays.toString(pPattern));

        for (int i = 0; i < pPattern.length; ++i) {
            for (int j = 0; j < 3; ++j) {
                String s = pPattern[i].substring(j, j + 1);
                Ingredient ingredient;
                if (!s.equals(" ")) {
                    ingredient = pKeys.get(s);
                } else {
                    ingredient = Ingredient.EMPTY;
                }

                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(i * 3 + j, ingredient);


            }

        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            //LOGGER.error(nonnulllist.toString());
            return nonnulllist;
        }
    }

    public static NonNullList<Ingredient> offsetList(NonNullList<Ingredient> list, int offset) {
        for (int i = list.size() - 1; i >= offset; i--) {
            list.set(i, list.get(i - 1));
        }
        return list;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CastingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "casting";
    }

    @VisibleForTesting
    static String[] shrink(String... pToShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int i1 = 0; i1 < pToShrink.length; ++i1) {
            String s = pToShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pToShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[pToShrink.length - l - k];

            for (int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = pToShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    static Map<String, Ingredient> keyFromJson(JsonObject pKeyEntry) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : pKeyEntry.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue(), false));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }


    public static class Serializer implements RecipeSerializer<CastingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ReChroma.MOD_ID, "casting");

        public CastingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {

            Map<String, Ingredient> map = keyFromJson(GsonHelper.getAsJsonObject(pJson, "key"));
            String[] astring = patternFromJson(GsonHelper.getAsJsonArray(pJson, "pattern"));
            NonNullList<Ingredient> nonnulllist = dissolvePattern(astring, map);
            ItemStack itemstack = itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "output"));

            int craftTime = GsonHelper.getAsInt(pJson, "craftTime");

            boolean isHaveRunes = GsonHelper.getAsBoolean(pJson, "isHaveRunes", false);

            if (isHaveRunes) {
                RuneData[] runes = runeDataFromJson(GsonHelper.getAsJsonArray(pJson, "runes"));
                return new CastingRecipe(nonnulllist, itemstack, craftTime, pRecipeId, runes);
            }


            return new CastingRecipe(nonnulllist, itemstack, craftTime, pRecipeId);
        }


        @Override
        public @Nullable CastingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

            for (int i = 0; i < 9; i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();

            int CraftTime = pBuffer.readInt();

            boolean isHaveRunes = pBuffer.readBoolean();
            RuneData[] runes;
            if (isHaveRunes) {
                runes = new RuneData[pBuffer.readInt()];
                for (int i = 0; i < runes.length; ++i) {
                    runes[i] = new RuneData(pBuffer.readInt(), pBuffer.readInt(), pBuffer.readInt(), pBuffer.readInt());
                }
                return new CastingRecipe(inputs, output, CraftTime, pRecipeId, runes);
            }

            return new CastingRecipe(inputs, output, CraftTime, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, CastingRecipe pRecipe) {

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);

            pBuffer.writeInt(pRecipe.craftTime);

            pBuffer.writeBoolean(pRecipe.isHaveRunes);

            if (pRecipe.isHaveRunes) {
                pBuffer.writeInt(pRecipe.runes.length);
                //LOGGER.error("saving rune data to net work");
                for (RuneData rd : pRecipe.runes) {
                    pBuffer.writeInt(rd.colorIndex);
                    pBuffer.writeInt(rd.blockPosX);
                    pBuffer.writeInt(rd.blockPosY);
                    pBuffer.writeInt(rd.blockPosZ);
                }
            }
        }
    }

}
