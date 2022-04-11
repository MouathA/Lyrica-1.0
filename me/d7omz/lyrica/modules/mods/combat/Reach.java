//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import net.minecraft.client.*;
import me.d7omz.lyrica.values.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.*;

public class Reach extends Module
{
    private static double zzzzzD2;
    public static Minecraft mc;
    private NumberValue min;
    private NumberValue max;
    
    @SubscribeEvent
    public void e(final MouseEvent mouseEvent) {
        if (!this.getState()) {
            return;
        }
        Reach.zzzzzD2 = this.max.getValue() + 0.0 * (this.max.getValue() - this.min.getValue());
        final Object[] zzzzz = zzzzz(Reach.zzzzzD2, 0.0, 0.0f);
        if (zzzzz == null) {
            return;
        }
        Reach.mc.objectMouseOver = new MovingObjectPosition((Entity)zzzzz[0], (Vec3)zzzzz[1]);
        Reach.mc.pointedEntity = (Entity)zzzzz[0];
    }
    
    static {
        Reach.mc = Minecraft.getMinecraft();
    }
    
    public Reach() {
        super("Reach", 0, Category.Combat);
        this.min = new NumberValue("Min Range", 0.0, 0.0, 6.0);
        this.max = new NumberValue("Max Range", 0.0, 0.0, 6.0);
        this.addValue(this.min);
        this.addValue(this.max);
    }
    
    @Override
    public void onDisable() {
    }
    
    public static Object[] zzzzz(final double n, final double n2, final float n3) {
        final Entity getRenderViewEntity = Reach.mc.getRenderViewEntity();
        Object o = null;
        if (getRenderViewEntity == null || Reach.mc.theWorld == null) {
            return null;
        }
        Reach.mc.mcProfiler.startSection("pick");
        final Vec3 getPositionEyes = getRenderViewEntity.getPositionEyes(0.0f);
        final Vec3 getLook = getRenderViewEntity.getLook(0.0f);
        final Vec3 addVector = getPositionEyes.addVector(getLook.xCoord * n, getLook.yCoord * n, getLook.zCoord * n);
        Object o2 = null;
        final List getEntitiesWithinAABBExcludingEntity = Reach.mc.theWorld.getEntitiesWithinAABBExcludingEntity(getRenderViewEntity, getRenderViewEntity.getEntityBoundingBox().addCoord(getLook.xCoord * n, getLook.yCoord * n, getLook.zCoord * n).expand(1.0, 1.0, 1.0));
        double n4 = n;
        for (int i = 0; i < getEntitiesWithinAABBExcludingEntity.size(); ++i) {
            final Entity entity = getEntitiesWithinAABBExcludingEntity.get(i);
            if (entity.canBeCollidedWith()) {
                final float getCollisionBorderSize = entity.getCollisionBorderSize();
                final AxisAlignedBB expand = entity.getEntityBoundingBox().expand((double)getCollisionBorderSize, (double)getCollisionBorderSize, (double)getCollisionBorderSize).expand(n2, n2, n2);
                final MovingObjectPosition calculateIntercept = expand.calculateIntercept(getPositionEyes, addVector);
                if (expand.isVecInside(getPositionEyes)) {
                    if (0.0 < n4 || n4 == 0.0) {
                        o = entity;
                        o2 = ((calculateIntercept == null) ? getPositionEyes : calculateIntercept.hitVec);
                        n4 = 0.0;
                    }
                }
                else if (calculateIntercept != null) {
                    final double distanceTo = getPositionEyes.distanceTo(calculateIntercept.hitVec);
                    if (distanceTo < n4 || n4 == 0.0) {
                        if (entity == getRenderViewEntity.ridingEntity) {
                            if (n4 == 0.0) {
                                o = entity;
                                o2 = calculateIntercept.hitVec;
                            }
                        }
                        else {
                            o = entity;
                            o2 = calculateIntercept.hitVec;
                            n4 = distanceTo;
                        }
                    }
                }
            }
        }
        if (n4 < n && !(o instanceof EntityLivingBase) && !(o instanceof EntityItemFrame)) {
            o = null;
        }
        Reach.mc.mcProfiler.endSection();
        if (o == null || o2 == null) {
            return null;
        }
        return new Object[] { o, o2 };
    }
}
