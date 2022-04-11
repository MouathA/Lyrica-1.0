//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.d7omz.lyrica.utils.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Velocity extends Module
{
    private NumberValue Vertical;
    private NumberValue Horizontal;
    
    @Override
    public void onDisable() {
    }
    
    public Velocity() {
        super("Velocity", 0, Category.Combat);
        this.Horizontal = new NumberValue("Horizontal", 0.0, 0.0, 100.0);
        this.Vertical = new NumberValue("Vertical", 0.0, 0.0, 100.0);
        this.addValue(this.Horizontal);
        this.addValue(this.Vertical);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        if (Wrapper.getPlayer() == null) {
            return;
        }
        if (Wrapper.getWorld() == null) {
            return;
        }
        final double n = this.Vertical.getValue() / 100.0;
        final double n2 = this.Horizontal.getValue() / 100.0;
        if (Wrapper.getPlayer().hurtTime == Wrapper.getPlayer().maxHurtTime && Wrapper.getPlayer().maxHurtTime > 0) {
            final EntityPlayerSP player = Wrapper.getPlayer();
            player.motionX *= n2;
            final EntityPlayerSP player2 = Wrapper.getPlayer();
            player2.motionZ *= n2;
            final EntityPlayerSP player3 = Wrapper.getPlayer();
            player3.motionY *= n;
        }
    }
}
