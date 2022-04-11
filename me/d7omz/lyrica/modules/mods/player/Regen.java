//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.player;

import me.d7omz.lyrica.modules.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Regen extends Module
{
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        if (!this.mc.thePlayer.capabilities.isCreativeMode && this.mc.thePlayer.getFoodStats().getFoodLevel() > 17 && this.mc.thePlayer.getHealth() != 0.0f && this.mc.thePlayer.onGround) {
            if (this.mc.thePlayer.getHealth() >= 20.0f) {
                return;
            }
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer());
        }
    }
    
    public Regen() {
        super("Regen", 0, Category.Player);
    }
    
    @Override
    public void onDisable() {
    }
}
