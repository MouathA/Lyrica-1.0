//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.render;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.utils.*;
import me.d7omz.lyrica.*;
import net.minecraft.client.gui.*;

public class G0ui extends Module
{
    public G0ui() {
        super("ClickGUI", 54, Category.Render);
    }
    
    @Override
    public void onEnable() {
        if (Wrapper.getPlayer() != null && Wrapper.getMinecraft().currentScreen == null) {
            Wrapper.getMinecraft().displayGuiScreen((GuiScreen)Lyrica.getClickGUI());
            this.toggle();
        }
    }
}
