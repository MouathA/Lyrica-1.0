//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica;

import me.d7omz.lyrica.gui.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.d7omz.lyrica.utils.*;
import org.lwjgl.input.*;
import me.d7omz.lyrica.modules.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mod(modid = "Lyrica", clientSideOnly = true, version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class Lyrica
{
    private static ClickGUI clickGUI;
    private static ModuleManager moduleManager;
    public static Lyrica INSTANCE;
    
    @Mod.EventHandler
    public void fmlInitialization(final FMLInitializationEvent fmlInitializationEvent) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        Lyrica.moduleManager = new ModuleManager();
        Lyrica.clickGUI = new ClickGUI();
    }
    
    public static ClickGUI getClickGUI() {
        return Lyrica.clickGUI;
    }
    
    public static Lyrica getINSTANCE() {
        return Lyrica.INSTANCE;
    }
    
    @SubscribeEvent
    public void keyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Wrapper.getPlayer() != null) {
            if (!Keyboard.getEventKeyState()) {
                return;
            }
            for (final Module module : ModuleManager.getModules()) {
                if (module.getKey() == Keyboard.getEventKey()) {
                    module.setState(!module.getState());
                }
            }
        }
    }
}
