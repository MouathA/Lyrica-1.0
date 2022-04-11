//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.movement;

import me.d7omz.lyrica.modules.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Sprint extends Module
{
    public Sprint() {
        super("Sprint", 0, Category.Movement);
    }
    
    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSprint.getKeyCode(), false);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSprint.getKeyCode(), true);
    }
}
