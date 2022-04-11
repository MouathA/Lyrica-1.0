//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import net.minecraft.entity.player.*;
import me.d7omz.lyrica.utils.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AimAssist extends Module
{
    private NumberValue distance;
    private BooleanValue Invisibles;
    private NumberValue yawspeed;
    private Random rand;
    private EntityPlayer Entity;
    private NumberValue pitchspeed;
    private NumberValue fov;
    private BooleanValue increaseonstrafe;
    private BooleanValue clickaim;
    
    private EntityPlayer findEntity() {
        if (Wrapper.getWorld() != null) {
            for (final EntityPlayer entityPlayer : Wrapper.getWorld().playerEntities) {
                if (!entityPlayer.getCommandSenderEntity().equals((Object)Wrapper.getPlayer().getCommandSenderEntity()) && this.IsValidEntity(entityPlayer)) {
                    return entityPlayer;
                }
            }
        }
        return null;
    }
    
    @Override
    public void onDisable() {
    }
    
    public AimAssist() {
        super("AimAssist", 0, Category.Combat);
        this.yawspeed = new NumberValue("Horizontal", 0.0, 0.0, 5.0);
        this.pitchspeed = new NumberValue("Vertical", 0.0, 0.0, 5.0);
        this.distance = new NumberValue("Range", 0.0, 0.0, 6.0);
        this.fov = new NumberValue("FOV", 0.0, 0.0, 360.0);
        this.clickaim = new BooleanValue("ClickAim", true);
        this.Invisibles = new BooleanValue("Invisibles", false);
        this.increaseonstrafe = new BooleanValue("Increase on strafe", false);
        this.addValue(this.yawspeed);
        this.addValue(this.pitchspeed);
        this.addValue(this.distance);
        this.addValue(this.fov);
        this.addBoolean(this.clickaim);
        this.addBoolean(this.Invisibles);
        this.addBoolean(this.increaseonstrafe);
    }
    
    private void faceTarget(final Entity entity, final float n, final float n2) {
        final EntityPlayerSP player = Wrapper.getPlayer();
        final float n3 = getAngles(entity)[1];
        final float n4 = getAngles(entity)[0];
        ((EntityPlayer)player).rotationYaw = this.getRotation(((EntityPlayer)player).rotationYaw, n3, n);
        ((EntityPlayer)player).rotationPitch = this.getRotation(((EntityPlayer)player).rotationPitch, n4, n2);
    }
    
    protected float getRotation(final float n, final float n2, final float n3) {
        float wrapAngleTo180_float = MathHelper.wrapAngleTo180_float(n2 - n);
        if (wrapAngleTo180_float > n3) {
            wrapAngleTo180_float = n3;
        }
        if (wrapAngleTo180_float < -n3) {
            wrapAngleTo180_float = -n3;
        }
        return n + wrapAngleTo180_float / 2.0f;
    }
    
    private int fov(final Entity entity) {
        final float[] angles = getAngles(entity);
        if (angles != null) {
            return (int)MathHelper.sqrt_float(Wrapper.getPlayer().rotationYaw - angles[0] * Wrapper.getPlayer().rotationYaw - angles[0] + Wrapper.getPlayer().rotationPitch - angles[1] * Wrapper.getPlayer().rotationPitch - angles[1]);
        }
        return -1;
    }
    
    @SubscribeEvent
    public void clientTick(final TickEvent tickEvent) {
        if (!this.getState()) {
            return;
        }
        if (Wrapper.getPlayer() != null) {
            if (this.Entity == null || Wrapper.getPlayer().getDistanceToEntity((Entity)this.Entity) > this.distance.getValue()) {
                this.Entity = this.findEntity();
            }
            else if (this.IsValidEntity(this.Entity)) {
                float n = (float)(this.yawspeed.getValue() * 3.0);
                float n2 = (float)(this.pitchspeed.getValue() * 3.0);
                if (this.Entity != null && this.IsValidEntity(this.Entity)) {
                    n *= (float)0.05;
                    n2 *= (float)0.02;
                }
                if (this.increaseonstrafe.getState() && Wrapper.getPlayer().moveStrafing != 0.0f && Wrapper.getMinecraft().objectMouseOver == null && Wrapper.getMinecraft().objectMouseOver.entityHit == null) {
                    n += 1.5;
                }
                if (Wrapper.getMinecraft().objectMouseOver != null && Wrapper.getMinecraft().objectMouseOver.entityHit != null) {
                    n = 0.05f;
                    n2 = 0.0f;
                }
                this.faceTarget((Entity)this.Entity, n, n2);
            }
        }
    }
    
    public static float[] getAngles(final Entity entity) {
        final double n = entity.posX - Wrapper.getPlayer().posX;
        final double n2 = entity.posZ - Wrapper.getPlayer().posZ;
        final double n3 = entity.posY - 0.2 + entity.getEyeHeight() - 0.4 - Wrapper.getPlayer().posY;
        final double n4 = MathHelper.sqrt_double(n * n + n2 * n2);
        float n5 = (float)Math.toDegrees(-Math.atan(n / n2));
        final float n6 = (float)(-Math.toDegrees(Math.atan(n3 / n4)));
        if (n2 < 0.0 && n < 0.0) {
            n5 = (float)(90.0 + Math.toDegrees(Math.atan(n2 / n)));
        }
        else if (n2 < 0.0 && n > 0.0) {
            n5 = (float)(-90.0 + Math.toDegrees(Math.atan(n2 / n)));
        }
        return new float[] { n6, n5 };
    }
    
    private boolean IsValidEntity(final EntityPlayer entityPlayer) {
        return (!entityPlayer.isInvisible() || this.Invisibles.getState()) && Wrapper.getMinecraft().currentScreen == null && Wrapper.getPlayer().isEntityAlive() && entityPlayer.isEntityAlive() && Wrapper.getPlayer().getDistanceToEntity((Entity)entityPlayer) <= this.distance.getValue() && (Wrapper.getMinecraft().gameSettings.keyBindAttack.isKeyDown() || !this.clickaim.getState()) && this.fov((Entity)entityPlayer) <= this.fov.getValue();
    }
}
