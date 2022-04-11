//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules;

import java.util.*;
import me.d7omz.lyrica.modules.mods.render.*;
import me.d7omz.lyrica.modules.mods.movement.*;
import me.d7omz.lyrica.modules.mods.combat.*;
import me.d7omz.lyrica.modules.mods.player.*;

public class ModuleManager
{
    private static ArrayList<Module> modules;
    
    public static ArrayList<Module> getModules() {
        return ModuleManager.modules;
    }
    
    static {
        (ModuleManager.modules = new ArrayList<Module>()).add((Module)new G0ui());
        ModuleManager.modules.add((Module)new Screen());
        ModuleManager.modules.add((Module)new Destruct());
        ModuleManager.modules.add((Module)new Sprint());
        ModuleManager.modules.add((Module)new KeepSprint());
        ModuleManager.modules.add((Module)new Fly());
        ModuleManager.modules.add((Module)new Timer());
        ModuleManager.modules.add((Module)new TriggerBot());
        ModuleManager.modules.add((Module)new Clicker());
        ModuleManager.modules.add((Module)new Reach());
        ModuleManager.modules.add((Module)new AimAssist());
        ModuleManager.modules.add((Module)new HitBox());
        ModuleManager.modules.add((Module)new Velocity());
        ModuleManager.modules.add((Module)new Regen());
        ModuleManager.modules.add((Module)new CStealer());
        ModuleManager.modules.add((Module)new Spammer());
    }
}
