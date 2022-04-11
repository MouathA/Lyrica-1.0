//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.player;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.utils.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.apache.commons.lang3.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Spammer extends Module
{
    private TimerSpammer timer;
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        if (this.timer.hasReached(1050L)) {
            this.mc.thePlayer.sendChatMessage(String.valueOf(new StringBuilder().append("Lyrica Client v1.0 - By D7oMz [").append(RandomStringUtils.random(10, "abcdef0123456789")).append("]")));
            this.timer.reset();
        }
    }
    
    public Spammer() {
        super("Spammer", 0, Category.Player);
        this.timer = new TimerSpammer();
    }
    
    @Override
    public void onDisable() {
    }
}
