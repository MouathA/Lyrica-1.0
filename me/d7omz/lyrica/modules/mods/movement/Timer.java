//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.movement;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.d7omz.lyrica.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Timer extends Module
{
    private NumberValue speed;
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        TimerSpeed.tmr().timerSpeed = (float)this.speed.getValue();
    }
    
    public Timer() {
        super("Timer", 0, Category.Movement);
        this.addValue(this.speed = new NumberValue("Timer speed", 2.0, 0.0, 4.0));
    }
    
    @Override
    public void onDisable() {
        TimerSpeed.stopTmr();
    }
}
