//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.render;

import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import me.d7omz.lyrica.utils.*;
import org.lwjgl.opengl.*;
import me.d7omz.lyrica.modules.*;
import java.awt.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Screen extends Module
{
    public Minecraft mc;
    
    public Screen() {
        super("Hud", 0, Category.Render);
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post post) {
        if (Wrapper.getMinecraft().currentScreen instanceof GuiMainMenu) {
            return;
        }
        if (post.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }
        final int[] array = { 1 };
        GL11.glPushMatrix();
        int n = 14;
        Wrapper.fr.drawStringWithShadow("Lyrica Client v1.0 - By D7oMz", 4.0f, 4.0f, Wrapper.rainbow(array[0] * 300));
        final int[] array2 = array;
        final int n2 = 0;
        ++array2[n2];
        for (final Module module : ModuleManager.getModules()) {
            if (module != null && module != this && module.getState()) {
                Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow(String.valueOf(new StringBuilder().append("- ").append(module.getName())), 2.0f, (float)n, new Color(Wrapper.rainbow(array[0] * 300)).getRGB());
                n += 10;
                final int[] array3 = array;
                final int n3 = 0;
                ++array3[n3];
            }
        }
        GL11.glPopMatrix();
    }
}
