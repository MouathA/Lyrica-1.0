//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.gui.parts;

import me.d7omz.lyrica.gui.component.*;
import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import me.d7omz.lyrica.utils.*;
import me.d7omz.lyrica.modules.mods.render.*;

public class ModulesPart extends Component
{
    private boolean isHovered;
    public boolean open;
    public Frame parent;
    private Screen color;
    private boolean binding;
    public int offset;
    public Module mod;
    private ArrayList<Component> subcomponents;
    
    public ModulesPart(final Module mod, final Frame parent, final int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        int n = offset + 14;
        if (!mod.getValues().isEmpty()) {
            final Iterator<NumberValue> iterator = mod.getValues().iterator();
            while (iterator.hasNext()) {
                this.subcomponents.add(new SliderPart(iterator.next(), this, n));
                n += 12;
            }
        }
        if (!mod.getBooleans().isEmpty()) {
            final Iterator<BooleanValue> iterator2 = mod.getBooleans().iterator();
            while (iterator2.hasNext()) {
                this.subcomponents.add((Component)new CheckboxPart((BooleanValue)iterator2.next(), this, n));
                n += 12;
            }
        }
    }
    
    public void keyTyped(final char c, final int key) {
        if (this.binding) {
            if (key == 14) {
                this.mod.setKey(0);
                this.binding = false;
                return;
            }
            this.mod.setKey(key);
            this.binding = false;
            if (key == 42) {
                this.mod.setKey(0);
                this.binding = false;
            }
        }
    }
    
    public void render() {
        final int[] array = { 1 };
        final int rainbow = Wrapper.rainbow(array[0] * 300);
        if (this.isHovered && Mouse.isButtonDown(2)) {
            this.binding = true;
        }
        Gui.drawRect(this.parent.getX() + 1, this.parent.getY() - 2 + this.offset, this.parent.getX() + this.parent.getWidth() - 2, this.parent.getY() + 12 + this.offset, this.isHovered ? new Color(50, 50, 50, 150).getRGB() : new Color(15, 15, 15, 255).getRGB());
        Wrapper.drawCenteredString(this.binding ? "" : this.mod.getName(), this.parent.getX() + 35, this.parent.getY() + 1 + this.offset, this.mod.getState() ? new Color(rainbow).getRGB() : new Color(150, 150, 150).getRGB());
        final int[] array2 = array;
        final int n = 0;
        ++array2[n];
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow(this.binding ? String.valueOf(new StringBuilder().append("Bind : [").append(Keyboard.getKeyName(this.mod.getKey())).append("]")) : "", (float)(this.parent.getX() * 2 + 5), (float)((this.parent.getY() + this.offset) * 2 + 6), new Color(150, 150, 150).getRGB());
        GL11.glPopMatrix();
        if (!this.mod.getBooleans().isEmpty()) {
            Render.drawArrow((float)(this.parent.getX() + 65), (float)(this.parent.getY() + this.offset + 2), this.open, new Color(150, 150, 150).getRGB());
        }
        if (!this.mod.getValues().isEmpty()) {
            Render.drawArrow((float)(this.parent.getX() + 65), (float)(this.parent.getY() + this.offset + 2), this.open, new Color(150, 150, 150).getRGB());
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            final Iterator<Component> iterator = this.subcomponents.iterator();
            while (iterator.hasNext()) {
                iterator.next().render();
            }
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 2 && this.parent.open) {
            this.binding = !this.binding;
        }
        if (this.isMouseOnButton(n, n2) && n3 == 0) {
            final Module mod = this.mod;
            if (!Module.getModule(G0ui.class).getState()) {
                this.mod.setState(!this.mod.getState());
            }
        }
        if (this.isMouseOnButton(n, n2) && n3 == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        final Iterator<Component> iterator = this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().mouseClicked(n, n2, n3);
        }
    }
    
    public boolean isMouseOnButton(final int n, final int n2) {
        return n > this.parent.getX() && n < this.parent.getX() + this.parent.getWidth() && n2 > this.parent.getY() + this.offset && n2 < this.parent.getY() + 12 + this.offset;
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
        int off = this.offset + 12;
        final Iterator<Component> iterator = this.subcomponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOff(off);
            off += 12;
        }
    }
    
    public int getHeight() {
        if (this.open) {
            return 12 * (this.subcomponents.size() + 1);
        }
        return 12;
    }
    
    public void updateComponent(final int n, final int n2) {
        this.parent.refresh();
        this.isHovered = this.isMouseOnButton(n, n2);
        if (!this.subcomponents.isEmpty()) {
            final Iterator<Component> iterator = this.subcomponents.iterator();
            while (iterator.hasNext()) {
                iterator.next().updateComponent(n, n2);
            }
        }
    }
}
