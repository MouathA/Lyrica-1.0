package me.d7omz.lyrica.gui.component;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.gui.parts.*;
import java.util.*;
import me.d7omz.lyrica.utils.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class Frame
{
    private int x;
    public int dragX;
    public Module.Category category;
    private int barHeight;
    private int width;
    public int dragY;
    public boolean open;
    public ArrayList<Component> components;
    private boolean isDragging;
    private int y;
    
    public int getY() {
        return this.y;
    }
    
    public Frame(final Module.Category category) {
        this.components = new ArrayList<Component>();
        this.category = category;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int barHeight = this.barHeight;
        for (final Module module : Module.getCategoryModules(this.category)) {
            if (!module.getName().equalsIgnoreCase("ClickGUI")) {
                this.components.add(new ModulesPart(module, this, barHeight));
                barHeight += 12;
            }
        }
    }
    
    public void updatePosition(final int n, final int n2) {
        if (this.isDragging) {
            this.setX(n - this.dragX);
            this.setY(n2 - this.dragY);
        }
    }
    
    public boolean isWithinHeader(final int n, final int n2) {
        return n >= this.x && n <= this.x + this.width && n2 >= this.y && n2 <= this.y + this.barHeight;
    }
    
    public void refresh() {
        int barHeight = this.barHeight;
        for (final Component component : this.components) {
            component.setOff(barHeight);
            barHeight += component.getHeight();
        }
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        final int[] array = { 1 };
        final int rainbow = Wrapper.rainbow(array[0] * 300);
        this.width = 80;
        Gui.drawRect(this.x - 1, this.y - 3, this.x + this.width + 1, this.y + this.barHeight - 2, new Color(rainbow).getRGB());
        GL11.glPushMatrix();
        Wrapper.getMinecraft().fontRendererObj.drawString(this.category.name(), this.x + 2, this.y, -1);
        Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow("                               Lyrica v1.0 - By D7oMz", 300.0f, 450.0f, rainbow);
        final int[] array2 = array;
        final int n = 0;
        ++array2[n];
        if (this.open) {
            fontRenderer.drawString("-", this.x + 70, (int)(this.y + 1.5), -1);
        }
        else {
            fontRenderer.drawString("+", this.x + 70, this.y + 1, -1);
        }
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            final Iterator<Component> iterator = this.components.iterator();
            while (iterator.hasNext()) {
                iterator.next().render();
            }
        }
    }
    
    public void setDrag(final boolean isDragging) {
        this.isDragging = isDragging;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public int getX() {
        return this.x;
    }
}
