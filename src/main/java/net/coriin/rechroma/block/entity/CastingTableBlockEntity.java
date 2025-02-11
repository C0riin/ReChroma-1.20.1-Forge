package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.auxiliary.StructureHelper;
import net.coriin.rechroma.auxiliary.WorldHelper;
import net.coriin.rechroma.block.ModBlockEntities;
import net.coriin.rechroma.block.custom.RuneBlock;
import net.coriin.rechroma.recipe.CastingRecipe;
import net.coriin.rechroma.screen.CastingTableMenu;
import net.coriin.rechroma.util.RuneData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public class CastingTableBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(10){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            CastingTableBlockEntity.this.setChanged();
        }
    };

    private static final int OUTPUT_SLOT = 9;

    //private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(() -> itemHandler);

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;
    public boolean doCrafting = false;

    public CastingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CASTING_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pindex) {
                return switch (pindex){
                    case 0 -> CastingTableBlockEntity.this.progress;
                    case 1 -> CastingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pindex, int pValue) {
                switch (pindex){
                    case 0 -> CastingTableBlockEntity.this.progress = pValue;
                    case 1 -> CastingTableBlockEntity.this.maxProgress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override public void onLoad() {
        super.onLoad();
        //lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < inventory.getContainerSize(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }


    @Override public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }
    @Override public CompoundTag getUpdateTag() {
        //CompoundTag tag = new CompoundTag();
        //saveAdditional(tag);
        //return tag;
        return this.saveWithFullMetadata();
    }
    @Override public void handleUpdateTag(CompoundTag tag) { load(tag); }

    @Override public Component getDisplayName() {
        return Component.translatable("block.rechroma.casting_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pCointainerid, Inventory inventory, Player player) {
        return new CastingTableMenu(pCointainerid, inventory, this, this.data);
    }

    @Override protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("casting_table.progress", progress);
        pTag.putBoolean("casting_table.docrafting", doCrafting);
        super.saveAdditional(pTag);
    }
    @Override public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("casting_table.progress");
        doCrafting = pTag.getBoolean("casting_table.docrafting");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe() && doCrafting && hasRunes(pPos) && getCurrentRecipe().get().tier <= calculateTier(pLevel,pPos)) {

            maxProgress = getCurrentRecipe().get().craftTime * calcCraftMultiplier();

            increaseCraftProgress();

            setChanged(pLevel, pPos, pState);


            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
            doCrafting = false;
        }
    }

    private void resetProgress() {
        progress = 0;
    }


    private void craftItem() {
        Optional<CastingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        int craftMultiplier = calcCraftMultiplier();

        for(int i = 0; i < itemHandler.getSlots()-1; i++){
            this.itemHandler.extractItem(i, craftMultiplier, false);
        }

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()*craftMultiplier));
    }

    public int calcCraftMultiplier(){
        Optional<CastingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        int[] itemsCount = new int[itemHandler.getSlots()-1];
        for(int i = 0; i < itemsCount.length-1; i++){
            if(this.itemHandler.getStackInSlot(i).getCount() > 0) {
                itemsCount[i] = this.itemHandler.getStackInSlot(i).getCount();
            }
        }
        Arrays.sort(itemsCount);
        int i1 = 0;
        int min = 0;
        while (min == 0){
            min = itemsCount[i1];
            i1++;
        }
        return Math.min(min,(result.getMaxStackSize()- this.itemHandler.getStackInSlot(9).getCount())/result.getCount());
    }

    private boolean hasRecipe() {
        Optional<CastingRecipe> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertIntoOutputSlot(result.getItem());
    }
    private boolean hasRunes(BlockPos currentPos){
        boolean t = true;

        if(hasRecipe()){
            CastingRecipe recipe = getCurrentRecipe().get();
            if(!recipe.isHaveRunes){
                return true;
            }
            RuneData[] recipeRunes = recipe.runes;
            for(int i = 0; i < recipeRunes.length; i++){
                Block runeBlock = this.level.getBlockState(WorldHelper.blockPosSum(currentPos,
                        new BlockPos(recipeRunes[i].blockPosX,recipeRunes[i].blockPosY,recipeRunes[i].blockPosZ))).getBlock();
                if(runeBlock instanceof RuneBlock){
                    t &= recipeRunes[i].colorIndex == ((RuneBlock) runeBlock).colorIndex;
                }
                else {return false;}
            }
        }
        return t;
    }

    public int calculateTier(Level pLevel,BlockPos pPos, Player pPlayer) {

        if(StructureHelper.castingTableTier1Structure.match(pLevel,WorldHelper.blockPosSum(pPos, new BlockPos(6,-1,-6)), pPlayer)){
           return 1;
        }
        if(StructureHelper.castingTableTier2Structure.match(pLevel,WorldHelper.blockPosSum(pPos, new BlockPos(6,-1,-6)), pPlayer)){
            return 2;
        }
        if(false){
            return 3;
        }
        return 0;
    }
    public int calculateTier(Level pLevel,BlockPos pPos) {

        if(StructureHelper.castingTableTier1Structure.match(pLevel,WorldHelper.blockPosSum(pPos, new BlockPos(6,-1,-6)))){
            return 1;
        }
        if(StructureHelper.castingTableTier2Structure.match(pLevel,WorldHelper.blockPosSum(pPos, new BlockPos(6,-1,-6)))){
            return 2;
        }
        if(false){
            return 3;
        }
        return 0;
    }

    private Optional<CastingRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(CastingRecipe.Type.INSTANCE, inventory, this.level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(9).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == item;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }
}
