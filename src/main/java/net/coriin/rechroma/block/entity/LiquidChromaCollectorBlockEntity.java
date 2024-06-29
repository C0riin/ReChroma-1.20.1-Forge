package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.fluid.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;


public class LiquidChromaCollectorBlockEntity extends BlockEntity {

    protected FluidTank fluidTank = new FluidTank(3000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            LiquidChromaCollectorBlockEntity.this.setChanged();
        }
    };
    private final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> fluidTank);

    public LiquidChromaCollectorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LIQUID_CHROMA_COLLECTOR_BE.get(), pPos, pBlockState);
    }


    //@Override public ClientboundBlockEntityDataPacket getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata); }

    //@Override public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) { load(pkt.getTag()); }

    //@Override public CompoundTag getUpdateTag() { return this.saveWithFullMetadata(); }

    //@Override public void handleUpdateTag(CompoundTag tag) { load(tag); }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("fluidTank", this.fluidTank.writeToNBT(new CompoundTag()));
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.fluidTank.readFromNBT(pTag.getCompound("fluidTank"));

    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return this.fluidOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.fluidOptional.invalidate();
    }

    public void collectChroma(int amount){
        this.fluidTank.fill(new FluidStack(ModFluids.SOURCE_LIQUID_CHROMA.get(), amount), IFluidHandler.FluidAction.EXECUTE);
    }

    public FluidTank getFluidTank() {
        return this.fluidTank;
    }

    public LazyOptional<FluidTank> getFluidOptional() {
        return this.fluidOptional;
    }

    public int getFluidFreeSpace() {
        return this.fluidTank.getCapacity() -  this.fluidTank.getFluidAmount();
    }

    public void drainBuckets(int amount) {
        getFluidTank().drain(new FluidStack(ModFluids.SOURCE_LIQUID_CHROMA.get(), 1000*amount), IFluidHandler.FluidAction.EXECUTE);
    }
}
