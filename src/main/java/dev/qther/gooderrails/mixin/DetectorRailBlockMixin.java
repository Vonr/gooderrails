package dev.qther.gooderrails.mixin;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.qther.gooderrails.helper.RailPlacementHelper.NO_CONNECT_POS;

@Mixin(DetectorRailBlock.class)
public abstract class DetectorRailBlockMixin extends AbstractRailBlock {
    protected DetectorRailBlockMixin(boolean isStraight, Settings properties) {
        super(isStraight, properties);
    }

    @Shadow protected abstract void updatePoweredStatus(World world, BlockPos pos, BlockState state);

    @Inject(
            method = "onBlockAdded", at = @At("HEAD"), cancellable = true
    )
    private void cancelUpdates(BlockState state, World level, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        BlockPos noUpdatePos = NO_CONNECT_POS.get();
        if (pos.equals(noUpdatePos)) {
            ci.cancel();
            level.updateNeighbor(state, pos, this, pos, notify);
            this.updatePoweredStatus(level, pos, state);
        }

        if (noUpdatePos != null) {
            NO_CONNECT_POS.remove();
        }
    }
}