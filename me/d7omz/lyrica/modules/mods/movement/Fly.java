//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.movement;

import me.d7omz.lyrica.modules.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Fly extends Module
{
    @Override
    public void onDisable() {
        if (this.mc.thePlayer != null) {
            this.mc.thePlayer.capabilities.isFlying = false;
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        this.mc.thePlayer.capabilities.isFlying = true;
        if (this.mc.thePlayer.fallDistance > 3.0f) {
            this.mc.thePlayer.onGround = true;
        }
    }
    
    public Fly() {
        super("Fly", 0, Category.Movement);
    }
}
