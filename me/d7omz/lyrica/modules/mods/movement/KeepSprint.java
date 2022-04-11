//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.movement;

import me.d7omz.lyrica.modules.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class KeepSprint extends Module
{
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent attackEntityEvent) {
        if (!this.getState()) {
            return;
        }
        if (attackEntityEvent.target instanceof EntityPlayer) {
            final EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
            thePlayer.motionX /= 0.6;
            final EntityPlayerSP thePlayer2 = Minecraft.getMinecraft().thePlayer;
            thePlayer2.motionZ /= 0.6;
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
    
    public KeepSprint() {
        super("KeepSprint", 0, Category.Movement);
    }
    
    @Override
    public void onDisable() {
    }
}
