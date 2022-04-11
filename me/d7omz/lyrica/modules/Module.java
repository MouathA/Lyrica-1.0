//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules;

import me.d7omz.lyrica.values.*;
import net.minecraft.client.*;
import java.util.*;
import me.d7omz.lyrica.utils.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;

public abstract class Module
{
    private Category category;
    private String name;
    private ArrayList<NumberValue> values;
    private ArrayList<BooleanValue> booleans;
    protected Minecraft mc;
    private boolean state;
    private int key;
    
    public void onEnable() {
    }
    
    public static ArrayList<Module> getCategoryModules(final Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : ModuleManager.getModules()) {
            if (e.getCategory() == category) {
                list.add(e);
            }
        }
        return list;
    }
    
    public void addBoolean(final BooleanValue e) {
        this.booleans.add(e);
    }
    
    public Module(final String name, final int key, final Category category) {
        this.mc = Wrapper.getMinecraft();
        this.booleans = new ArrayList<BooleanValue>();
        this.values = new ArrayList<NumberValue>();
        this.name = name;
        this.key = key;
        this.state = false;
        this.category = category;
    }
    
    public void toggle() {
        this.setState(!this.state);
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public ArrayList<NumberValue> getValues() {
        return this.values;
    }
    
    public void onDisable() {
    }
    
    public static Module getModule(final Class<? extends Module> clazz) {
        for (final Module module : ModuleManager.getModules()) {
            if (module.getClass() == clazz) {
                return module;
            }
        }
        return null;
    }
    
    public ArrayList<BooleanValue> getBooleans() {
        return this.booleans;
    }
    
    public void Toggle() {
        if (this.state) {
            this.state = false;
            this.onDisable();
        }
        else {
            this.state = true;
            this.onEnable();
        }
    }
    
    public int getKey() {
        return this.key;
    }
    
    public boolean setToggled(final boolean state) {
        return this.state = state;
    }
    
    public void setState(final boolean state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            FMLCommonHandler.instance().bus().register((Object)this);
            this.onEnable();
        }
        else {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
            FMLCommonHandler.instance().bus().unregister((Object)this);
            this.onDisable();
        }
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void addValue(final NumberValue e) {
        this.values.add(e);
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public enum Category
    {
        Combat;
        
        private static final Category[] $VALUES;
        
        Player, 
        Movement, 
        Render;
        
        static {
            $VALUES = new Category[] { Category.Combat, Category.Movement, Category.Player, Category.Render };
        }
    }
}
