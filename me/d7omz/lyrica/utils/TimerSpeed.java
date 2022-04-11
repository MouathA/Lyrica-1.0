//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.utils;

import net.minecraft.client.*;
import net.minecraft.util.*;
import java.lang.reflect.*;

public class TimerSpeed
{
    protected static Minecraft mc;
    
    public static Timer tmr() {
        try {
            final Field declaredField = Minecraft.class.getDeclaredField(new String(new char[] { 't', 'i', 'm', 'e', 'r' }));
            declaredField.setAccessible(true);
            return (Timer)declaredField.get(TimerSpeed.mc);
        }
        catch (Exception ex) {
            try {
                final Field declaredField2 = Minecraft.class.getDeclaredField(new String(new char[] { 'f', 'i', 'e', 'l', 'd', '_', '7', '1', '4', '2', '8', '_', 'T' }));
                declaredField2.setAccessible(true);
                return (Timer)declaredField2.get(TimerSpeed.mc);
            }
            catch (Exception ex2) {
                return null;
            }
        }
    }
    
    static {
        TimerSpeed.mc = Minecraft.getMinecraft();
    }
    
    public static void stopTmr() {
        tmr().timerSpeed = 1.0f;
    }
}
