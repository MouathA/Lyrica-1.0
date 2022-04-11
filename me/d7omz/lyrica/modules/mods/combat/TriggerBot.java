//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.utils.*;
import me.d7omz.lyrica.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class TriggerBot extends Module
{
    private TimerTriggerBot timer;
    private BooleanValue mobs;
    private EntityLivingBase target;
    private BooleanValue animals;
    private BooleanValue players;
    private NumberValue range;
    
    @Override
    public void onDisable() {
        this.target = null;
    }
    
    public TriggerBot() {
        super("TriggerBot", 0, Category.Combat);
        this.range = new NumberValue("Range", 0.0, 0.0, 6.0);
        this.players = new BooleanValue("Players", true);
        this.mobs = new BooleanValue("Mobs", false);
        this.animals = new BooleanValue("Animals", false);
        this.timer = new TimerTriggerBot();
        this.addValue(this.range);
        this.addBoolean(this.players);
        this.addBoolean(this.mobs);
        this.addBoolean(this.animals);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            final Entity entityHit = this.mc.objectMouseOver.entityHit;
            if (entityHit instanceof EntityPlayer || entityHit instanceof EntityMob || (entityHit instanceof EntityAnimal && entityHit.isEntityAlive())) {
                this.target = this.getTarget((float)this.range.getValue() + 0.5f);
                if (this.target != null && this.timer.hasReached(80.0)) {
                    this.mc.thePlayer.swingItem();
                    this.mc.playerController.attackEntity((EntityPlayer)this.mc.thePlayer, (Entity)this.target);
                    this.timer.reset();
                }
            }
        }
    }
    
    public EntityLivingBase getTarget(final double n) {
        this.target = null;
        double n2 = 100.0;
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            final double n3 = this.mc.thePlayer.getDistanceToEntity(entity);
            if (this.players.getState() && n3 < n && entity != this.mc.thePlayer && entity instanceof EntityPlayer && n2 > n3) {
                this.target = (EntityLivingBase)entity;
                n2 = n3;
            }
            if (this.mobs.getState() && n3 < n && entity != this.mc.thePlayer && entity instanceof EntityMob && n2 > n3) {
                this.target = (EntityLivingBase)entity;
                n2 = n3;
            }
            if (this.animals.getState() && n3 < n && entity != this.mc.thePlayer && entity instanceof EntityAnimal && n2 > n3) {
                this.target = (EntityLivingBase)entity;
                n2 = n3;
            }
        }
        return this.target;
    }
}
