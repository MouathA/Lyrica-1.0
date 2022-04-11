package me.d7omz.lyrica.gui;

import net.minecraft.client.gui.*;
import me.d7omz.lyrica.gui.component.*;
import java.util.*;
import me.d7omz.lyrica.modules.*;

public class ClickGUI extends GuiScreen
{
    public ArrayList<Frame> frames;
    
    protected void mouseClicked(final int n, final int n2, final int n3) {
        for (final Frame frame : this.frames) {
            if (frame.isWithinHeader(n, n2) && n3 == 0) {
                frame.setDrag(true);
                frame.dragX = n - frame.getX();
                frame.dragY = n2 - frame.getY();
            }
            if (frame.isWithinHeader(n, n2) && n3 == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                final Iterator<Component> iterator2 = frame.getComponents().iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().mouseClicked(n, n2, n3);
                }
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        for (final Frame frame : this.frames) {
            frame.renderFrame(this.fontRendererObj);
            frame.updatePosition(n, n2);
            final Iterator<Component> iterator2 = frame.getComponents().iterator();
            while (iterator2.hasNext()) {
                iterator2.next().updateComponent(n, n2);
            }
        }
    }
    
    protected void keyTyped(final char c, final int n) {
        for (final Frame frame : this.frames) {
            if (frame.isOpen() && n != 1 && !frame.getComponents().isEmpty()) {
                final Iterator<Component> iterator2 = frame.getComponents().iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().keyTyped(c, n);
                }
            }
        }
        if (n == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    public ClickGUI() {
        this.frames = new ArrayList<Frame>();
        int x = 5;
        Module.Category[] values;
        for (int length = (values = Module.Category.values()).length, i = 0; i < length; ++i) {
            final Frame e = new Frame(values[i]);
            e.setX(x);
            this.frames.add(e);
            x += e.getWidth() + 1;
        }
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        final Iterator<Frame> iterator = this.frames.iterator();
        while (iterator.hasNext()) {
            iterator.next().setDrag(false);
        }
    }
}
