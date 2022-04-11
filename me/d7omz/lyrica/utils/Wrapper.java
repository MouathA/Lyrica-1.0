package me.d7omz.lyrica.utils;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;

public class Wrapper
{
    public static Minecraft mc;
    public static FontRenderer fr;
    
    public static int rainbow(final int n) {
        return Color.getHSBColor((float)(Math.ceil((System.currentTimeMillis() + n) / 20.0) % 360.0 / 360.0), 0.8f, 0.7f).getRGB();
    }
    
    public static GameSettings getGameSettings() {
        return getMinecraft().gameSettings;
    }
    
    public static void drawCenteredString(final String s, final int n, final int n2, final int n3) {
        getMinecraft().fontRendererObj.drawString(s, n - getMinecraft().fontRendererObj.getStringWidth(s) / 2, n2, n3);
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().thePlayer;
    }
    
    public static PlayerControllerMP getPlayerController() {
        return getMinecraft().playerController;
    }
    
    static {
        Wrapper.mc = Minecraft.getMinecraft();
        Wrapper.fr = Minecraft.getMinecraft().fontRendererObj;
    }
    
    public static WorldClient getWorld() {
        return getMinecraft().theWorld;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
}
