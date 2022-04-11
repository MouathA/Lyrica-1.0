//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.player;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import me.d7omz.lyrica.utils.*;
import net.minecraft.inventory.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class CStealer extends Module
{
    int delay;
    private NumberValue stealerdelay;
    private TimerCStealer timer;
    
    public CStealer() {
        super("CStealer", 0, Category.Player);
        this.delay = 0;
        this.stealerdelay = new NumberValue("Delay", 100.0, 0.0, 200.0);
        this.timer = new TimerCStealer();
        this.addValue(this.stealerdelay);
    }
    
    public boolean isChestEmpty(final ContainerChest containerChest) {
        for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
            if (containerChest.getLowerChestInventory().getStackInSlot(i) != null) {
                return false;
            }
        }
        return true;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        this.delay = (int)this.stealerdelay.getValue();
        if (this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
            final ContainerChest containerChest = (ContainerChest)this.mc.thePlayer.openContainer;
            for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
                if (containerChest.getLowerChestInventory().getStackInSlot(i) != null) {
                    if (this.timer.hasReached(this.delay)) {
                        this.mc.playerController.windowClick(containerChest.windowId, i, 0, 1, (EntityPlayer)this.mc.thePlayer);
                        this.timer.reset();
                    }
                }
                else if (this.isChestEmpty(containerChest)) {
                    this.mc.thePlayer.closeScreen();
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
    }
}
