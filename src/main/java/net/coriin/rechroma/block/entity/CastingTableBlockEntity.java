package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.item.ModItems;
import net.coriin.rechroma.recipe.CastingTier1Recipe;
import net.coriin.rechroma.screen.CastingTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
import java.util.Collections;
import java.util.Optional;

public class CastingTableBlockEntity extends BlockEntity implements MenuProvider {

    private  final ItemStackHandler itemHandler = new ItemStackHandler(10);

    private static final int OUTPUT_SLOT = 9;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;

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

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
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

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.rechroma.casting_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pCointainerid, Inventory inventory, Player player) {
        return new CastingTableMenu(pCointainerid, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("casting_table.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("casting_table.progress");

    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
        //Minecraft.getInstance().player.displayClientMessage(Component.literal(this.itemHandler.getStackInSlot(5).toString()), true);
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        /*
        Optional<CastingTier1Recipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        int[] items = new int[itemHandler.getSlots()];
        for(int i = 0; i < items.length; i++){
            items[i] = this.itemHandler.getStackInSlot(i).getCount();
        }
        Arrays.sort(items);

        int craftMultiplier = Math.min(items[0],result.getMaxStackSize()-this.itemHandler.getStackInSlot(9).getCount());

        for(int i = 0; i < 9;i++){
            this.itemHandler.extractItem(i, 1, false);
        }


        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));

         */
        Optional<CastingTier1Recipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(0, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {

        Optional<CastingTier1Recipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }

        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertIntoOutputSlot(result.getItem());

    }

    private Optional<CastingTier1Recipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(CastingTier1Recipe.Type.INSTANCE, inventory, this.level);
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
