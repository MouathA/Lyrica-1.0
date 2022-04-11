//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.render;

import me.d7omz.lyrica.utils.*;
import me.d7omz.lyrica.*;
import net.minecraft.client.gui.*;
import me.d7omz.lyrica.modules.mods.combat.*;
import me.d7omz.lyrica.modules.mods.movement.*;
import me.d7omz.lyrica.modules.mods.player.*;
import me.d7omz.lyrica.modules.*;
import java.util.*;

public class Destruct extends Module
{
    public Destruct() {
        super("Destruct", 0, Category.Render);
    }
    
    @Override
    public void onEnable() {
        if (Wrapper.getPlayer() != null) {
            if (Wrapper.getMinecraft().currentScreen == Lyrica.getClickGUI()) {
                Wrapper.getMinecraft().displayGuiScreen((GuiScreen)null);
            }
            this.setName("");
            Module.getModule((Class<? extends Module>)AimAssist.class).setName("");
            Module.getModule((Class<? extends Module>)Clicker.class).setName("");
            Module.getModule((Class<? extends Module>)HitBox.class).setName("");
            Module.getModule((Class<? extends Module>)Reach.class).setName("");
            Module.getModule((Class<? extends Module>)TriggerBot.class).setName("");
            Module.getModule((Class<? extends Module>)Velocity.class).setName("");
            Module.getModule((Class<? extends Module>)Fly.class).setName("");
            Module.getModule((Class<? extends Module>)KeepSprint.class).setName("");
            Module.getModule((Class<? extends Module>)Sprint.class).setName("");
            Module.getModule((Class<? extends Module>)Timer.class).setName("");
            Module.getModule((Class<? extends Module>)CStealer.class).setName("");
            Module.getModule((Class<? extends Module>)Regen.class).setName("");
            Module.getModule((Class<? extends Module>)Spammer.class).setName("");
            Module.getModule(G0ui.class).setName("");
            Module.getModule(Screen.class).setName("");
            for (final Module module : ModuleManager.getModules()) {
                if (module != null && module.getState()) {
                    module.setState(false);
                }
            }
            ModuleManager.getModules().clear();
            this.getValues().clear();
            this.getBooleans().clear();
        }
    }
}
