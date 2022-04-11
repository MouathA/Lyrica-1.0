//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.utils;

import org.lwjgl.opengl.*;

public class Render
{
    public static void drawCheckmark(final float n, final float n2, final int n3) {
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        hexColor(n3);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        GL11.glVertex2d((double)(n + 1.0f), (double)(n2 + 1.0f));
        GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
        GL11.glVertex2d((double)(n + 6.0f), (double)(n2 - 2.0f));
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void hexColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawArrow(float n, float n2, final boolean b, final int n3) {
        GL11.glPushMatrix();
        GL11.glScaled(1.3, 1.3, 1.3);
        if (b) {
            n2 -= 1.5f;
            n += 2.0f;
        }
        n /= (float)1.3;
        n2 /= (float)1.3;
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        hexColor(n3);
        GL11.glLineWidth(2.0f);
        if (b) {
            GL11.glBegin(1);
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d((double)(n + 4.0f), (double)(n2 + 3.0f));
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex2d((double)(n + 4.0f), (double)(n2 + 3.0f));
            GL11.glVertex2d((double)n, (double)(n2 + 6.0f));
            GL11.glEnd();
        }
        else {
            GL11.glBegin(1);
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
            GL11.glVertex2d((double)(n + 6.0f), (double)n2);
            GL11.glEnd();
        }
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawFullCircle(float n, float n2, double n3, final int n4) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        n3 *= 2.0;
        n *= 2.0f;
        n2 *= 2.0f;
        final float n5 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n6 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n4 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glBegin(6);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + Math.cos(i * 3.141592653589793 / 180.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
}
