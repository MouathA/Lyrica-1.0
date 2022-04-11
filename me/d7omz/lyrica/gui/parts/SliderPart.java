//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.gui.parts;

import me.d7omz.lyrica.gui.component.*;
import me.d7omz.lyrica.values.*;
import me.d7omz.lyrica.modules.mods.render.*;
import me.d7omz.lyrica.utils.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import com.ibm.icu.math.*;

public class SliderPart extends Component
{
    private ModulesPart parent;
    private boolean hovered;
    private NumberValue value;
    private int x;
    private Screen color;
    private int offset;
    private int y;
    
    public void render() {
        final int[] array = { 1 };
        final int rainbow = Wrapper.rainbow(array[0] * 300);
        final int n = (int)(this.value.getValue() / this.value.getMax() * 70.0);
        Gui.drawRect(this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() - 2, this.parent.parent.getY() + this.offset + 12, new Color(20, 20, 20, 150).getRGB());
        Gui.drawRect(this.parent.parent.getX() + 5, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() - 6, this.parent.parent.getY() + this.offset + 8, new Color(80, 80, 80, 120).getRGB());
        Gui.drawRect(this.parent.parent.getX() + 4, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 4 + n + 2, this.parent.parent.getY() + this.offset + 8, new Color(rainbow).getRGB());
        final int[] array2 = array;
        final int n2 = 0;
        ++array2[n2];
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getMinecraft().fontRendererObj.drawString(String.valueOf(String.valueOf(new StringBuilder().append(this.value.getName()).append(" : ").append(this.value.getValue()))), this.parent.parent.getX() * 2 + 12, (this.parent.parent.getY() + this.offset - 1) * 2 + 6, -1);
        GL11.glPopMatrix();
    }
    
    public boolean isMouseOnButtonD(final int n, final int n2) {
        return n > this.x && n < this.x + (this.parent.parent.getWidth() / 2 + 1) && n2 > this.y && n2 < this.y + 12;
    }
    
    public void updateComponent(final int n, final int n2) {
        this.hovered = (this.isMouseOnButtonD(n, n2) || this.isMouseOnButtonI(n, n2));
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        if (this.hovered && this.parent.open && Mouse.isButtonDown(0)) {
            this.value.setValue(this.round((n - this.parent.parent.getX()) / (double)(this.parent.parent.getWidth() - 1) * this.value.getMax(), 1));
        }
    }
    
    public SliderPart(final NumberValue value, final ModulesPart parent, final int offset) {
        this.value = value;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    private double round(final double n, final int n2) {
        return new BigDecimal(n).setScale(n2, 4).doubleValue();
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (this.isMouseOnButtonD(n, n2) && n3 == 0 && this.parent.open) {
            final NumberValue value = this.value;
            value.setValue(Math.round((value.getValue() - 0.1) * 10.0) / 10.0);
        }
        if (this.isMouseOnButtonI(n, n2) && n3 == 0 && this.parent.open) {
            final NumberValue value2 = this.value;
            value2.setValue(Math.round((value2.getValue() + 0.1) * 10.0) / 10.0);
        }
    }
    
    public boolean isMouseOnButtonI(final int n, final int n2) {
        return n > this.x + this.parent.parent.getWidth() / 2 && n < this.x + this.parent.parent.getWidth() && n2 > this.y && n2 < this.y + 12;
    }
}
