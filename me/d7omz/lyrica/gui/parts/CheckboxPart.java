package me.d7omz.lyrica.gui.parts;

import me.d7omz.lyrica.gui.component.*;
import me.d7omz.lyrica.values.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import me.d7omz.lyrica.utils.*;
import org.lwjgl.opengl.*;

public class CheckboxPart extends Component
{
    private int x;
    private ModulesPart parent;
    private int y;
    private BooleanValue op;
    private int offset;
    private boolean hovered;
    
    public void updateComponent(final int n, final int n2) {
        this.hovered = this.isMouseOnButton(n, n2);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    public CheckboxPart(final BooleanValue op, final ModulesPart parent, final int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    public void render() {
        final int[] array = { 1 };
        final int rainbow = Wrapper.rainbow(array[0] * 300);
        Gui.drawRect(this.parent.parent.getX() + 1, this.parent.parent.getY() + 12 + this.offset, this.parent.parent.getX() - 2 + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset, new Color(20, 20, 20, 150).getRGB());
        Gui.drawRect(this.parent.parent.getX() + 3, this.parent.parent.getY() + this.offset + 9, this.parent.parent.getX() - 5 + this.parent.parent.getWidth() - 63, this.parent.parent.getY() + this.offset + 1, new Color(0, 0, 0, 150).getRGB());
        if (this.op.getState()) {
            Render.drawCheckmark((float)(this.parent.parent.getX() + 4), (float)(this.parent.parent.getY() + this.offset + 4), new Color(rainbow).getRGB());
            final int[] array2 = array;
            final int n = 0;
            ++array2[n];
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow(this.op.getName(), (float)((this.parent.parent.getX() + 15) * 2), (float)((this.parent.parent.getY() + this.offset + 4) * 2 - 2), -1);
        GL11.glPopMatrix();
    }
    
    public boolean isMouseOnButton(final int n, final int n2) {
        return n > this.x && n < this.x + 105 && n2 > this.y && n2 < this.y + 12;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (this.isMouseOnButton(n, n2) && n3 == 0 && this.parent.open) {
            this.op.toggle();
        }
    }
}
