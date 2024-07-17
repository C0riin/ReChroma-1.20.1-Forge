package net.coriin.rechroma.screen.lexicon;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.item.custom.ChromaticLexicon;
import net.coriin.rechroma.item.custom.InfoFragmentItem;
import net.coriin.rechroma.screen.ModMenuTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;


public class LexiconFragmentMenu extends AbstractContainerMenu {

    public int rowIndexOffset = 0;
    public int invSize;
    public ItemStackHandler handler;

    public ItemStack lexiconStack;
    private Inventory inventory;

    public LexiconFragmentMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        //super(ModMenuTypes.LEXICON_FRAGMENTS_MENU.get(),pContainerId);
        this(pContainerId, inv, inv.player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ChromaticLexicon
                ? inv.player.getItemInHand(InteractionHand.MAIN_HAND) : inv.player.getItemInHand(InteractionHand.OFF_HAND));

        if(!(inv.player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ChromaticLexicon) &&
                !(inv.player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ChromaticLexicon)){
            throw new AssertionError("trying to open chromatic lexicon menu, but lexicon is not in hand");
        }

        ReChroma.LOGGER.error("LexiconFragmentMenu but wrong constructor");
    }

    public LexiconFragmentMenu(int pContainerId, Inventory inv, ItemStack lexiconStack) {
        super(ModMenuTypes.LEXICON_FRAGMENTS_MENU.get(),pContainerId);

        ReChroma.LOGGER.error("LexiconFragmentMenu - right constructor");

        this.inventory = inv;
        this.lexiconStack = lexiconStack;
        this.handler = new ItemStackHandler(((ChromaticLexicon)lexiconStack.getItem()).fragmentInvSize){
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return stack.getItem() instanceof InfoFragmentItem;
            }
        };
        invSize = handler.getSlots();
        handler.deserializeNBT(lexiconStack.getTag() != null ? lexiconStack.getTag() : new CompoundTag());

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                if(j*9+i + rowIndexOffset*9 < invSize){
                    this.addSlot(new SlotItemHandler(handler, j*9+i + rowIndexOffset*9, 8 + 18*i, 17 + j*18));
                }

            }
        }

    }

    @Override
    public void removed(Player pPlayer) {
        if(lexiconStack != null && lexiconStack.getItem() instanceof ChromaticLexicon && handler != null){
            lexiconStack.setTag(handler.serializeNBT());
        }
        super.removed(pPlayer);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    public void setOffset(int value) {
        this.rowIndexOffset = value;
        
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 27;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
