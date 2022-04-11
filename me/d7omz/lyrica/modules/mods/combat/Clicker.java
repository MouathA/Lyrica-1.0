//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import java.lang.reflect.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.item.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.nio.*;

public class Clicker extends Module
{
    private BooleanValue ifc;
    private double drr;
    private static Field bst;
    private long nlu;
    private NumberValue mcj;
    private NumberValue mcc;
    private NumberValue mca;
    private long nld;
    private long nrd;
    private boolean dr;
    private BooleanValue bh;
    private long nd;
    private static Field bff;
    private Method gg;
    private static Field fff;
    private long ne;
    private long nru;
    private Random rand;
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent renderTickEvent) {
        if (!this.getState()) {
            return;
        }
        if (this.mc.currentScreen == null) {
            Mouse.poll();
            if (Mouse.isButtonDown(0)) {
                if (!this.mc.inGameHasFocus) {
                    return;
                }
                if (this.mcj.getValue() > 0.0) {
                    final double n = this.mcj.getValue() * 0.45;
                    if (this.rand.nextBoolean()) {
                        final EntityPlayerSP thePlayer = this.mc.thePlayer;
                        thePlayer.rotationYaw += (float)(this.rand.nextFloat() * n);
                    }
                    else {
                        final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
                        thePlayer2.rotationYaw -= (float)(this.rand.nextFloat() * n);
                    }
                    if (this.rand.nextBoolean()) {
                        final EntityPlayerSP thePlayer3 = this.mc.thePlayer;
                        thePlayer3.rotationPitch += (float)(this.rand.nextFloat() * n * 0.45);
                    }
                    else {
                        final EntityPlayerSP thePlayer4 = this.mc.thePlayer;
                        thePlayer4.rotationPitch -= (float)(this.rand.nextFloat() * n * 0.45);
                    }
                }
                if (this.ifc.getState()) {
                    if (this.mc.thePlayer.getCurrentEquippedItem() == null) {
                        return;
                    }
                    if (!(this.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) && !(this.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                        return;
                    }
                }
                if (this.nld > 0L && this.nlu > 0L) {
                    if (System.currentTimeMillis() > this.nld) {
                        final int getKeyCode = this.mc.gameSettings.keyBindAttack.getKeyCode();
                        KeyBinding.setKeyBindState(getKeyCode, true);
                        KeyBinding.onTick(getKeyCode);
                        s(0, true);
                        this.vcx();
                    }
                    else if (System.currentTimeMillis() > this.nlu) {
                        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                        s(0, false);
                    }
                }
                else {
                    this.vcx();
                }
                if (this.bh.getState() && Mouse.isButtonDown(1)) {
                    if (this.nrd > 0L && this.nru > 0L) {
                        if (System.currentTimeMillis() > this.nrd) {
                            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                            KeyBinding.onTick(this.mc.gameSettings.keyBindUseItem.getKeyCode());
                            s(1, true);
                            this.vcxx();
                        }
                        else if (System.currentTimeMillis() > this.nru) {
                            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
                            s(1, false);
                        }
                    }
                    else {
                        this.vcxx();
                    }
                }
                else {
                    this.nru = 0L;
                    this.nrd = 0L;
                }
            }
            else {
                this.nru = 0L;
                this.nrd = 0L;
                this.nlu = 0L;
                this.nld = 0L;
            }
        }
    }
    
    public Clicker() {
        super("Clicker", 0, Category.Combat);
        this.mcc = new NumberValue("Min CPS", 0.0, 0.0, 20.0);
        this.mca = new NumberValue("Max CPS", 0.0, 0.0, 20.0);
        this.mcj = new NumberValue("Jitter", 0.0, 0.0, 2.0);
        this.bh = new BooleanValue("Block Hit", true);
        this.ifc = new BooleanValue("Weapon Only", false);
        this.addValue(this.mcc);
        this.addValue(this.mca);
        this.addValue(this.mcj);
        this.addBoolean(this.bh);
        this.addBoolean(this.ifc);
        this.rand = new Random();
        try {
            this.gg = GuiScreen.class.getDeclaredMethod("mouseClicked", Integer.TYPE, Integer.TYPE, Integer.TYPE);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
    
    private void vcxx() {
        this.nrd = System.currentTimeMillis() + (int)Math.round(1000.0 / 4.0);
        this.nru = System.currentTimeMillis() + 20L + 30L;
    }
    
    private void vcx() {
        final double n = this.mcc.getValue() + 3.0;
        final double n2 = this.mca.getValue() + 3.0;
        if (n > n2) {
            return;
        }
        long n3 = (int)Math.round(1000.0 / (n + 0.0 * (n2 - n)));
        if (System.currentTimeMillis() > this.nd) {
            if (!this.dr) {
                this.dr = true;
                this.drr = 1.1;
            }
            else {
                this.dr = false;
            }
            this.nd = System.currentTimeMillis() + 500L + 1500L;
        }
        if (this.dr) {
            n3 *= (long)this.drr;
        }
        if (System.currentTimeMillis() > this.ne) {
            n3 += 200L;
            this.ne = System.currentTimeMillis() + 500L + 1500L;
        }
        this.nld = System.currentTimeMillis() + n3;
        this.nlu = System.currentTimeMillis() + n3 / 2L - 10L;
    }
    
    private void c(final GuiScreen obj) {
        final int i = Mouse.getX() * obj.width / this.mc.displayWidth;
        final int j = obj.height - Mouse.getY() * obj.height / this.mc.displayHeight - 1;
        try {
            this.gg.setAccessible(true);
            this.gg.invoke(obj, i, j, 0);
            this.gg.setAccessible(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void s(final int i, final boolean b) {
        final MouseEvent mouseEvent = new MouseEvent();
        Clicker.fff.setAccessible(true);
        try {
            Clicker.fff.set(mouseEvent, i);
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        Clicker.fff.setAccessible(false);
        Clicker.bst.setAccessible(true);
        try {
            Clicker.bst.set(mouseEvent, b);
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        Clicker.bst.setAccessible(false);
        MinecraftForge.EVENT_BUS.post((Event)mouseEvent);
        try {
            Clicker.bff.setAccessible(true);
            final ByteBuffer byteBuffer = (ByteBuffer)Clicker.bff.get(null);
            Clicker.bff.setAccessible(false);
            byteBuffer.put(i, (byte)(b ? 1 : 0));
        }
        catch (IllegalAccessException ex3) {
            ex3.printStackTrace();
        }
    }
    
    static {
        try {
            Clicker.fff = MouseEvent.class.getDeclaredField("button");
        }
        catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        try {
            Clicker.bst = MouseEvent.class.getDeclaredField("buttonstate");
        }
        catch (NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
        try {
            Clicker.bff = Mouse.class.getDeclaredField("buttons");
        }
        catch (NoSuchFieldException ex3) {
            ex3.printStackTrace();
        }
    }
}
