//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.modules.mods.combat;

import me.d7omz.lyrica.modules.*;
import me.d7omz.lyrica.values.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.*;

public class HitBox extends Module
{
    private MovingObjectPosition moving;
    private Entity pointedEntity;
    private NumberValue expand;
    public static float hitBoxMultiplier;
    
    @SubscribeEvent
    public void onMouse(final MouseEvent mouseEvent) {
        try {
            if (this.moving != null && mouseEvent.button == 0 && mouseEvent.buttonstate) {
                this.mc.objectMouseOver = this.moving;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public HitBox() {
        super("HitBox", 0, Category.Combat);
        this.addValue(this.expand = new NumberValue("Expand", 0.0, 0.0, 1.0));
    }
    
    static {
        HitBox.hitBoxMultiplier = 1.0f;
    }
    
    @Override
    public void onDisable() {
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (!this.getState()) {
            return;
        }
        if (this.mc.theWorld != null) {
            HitBox.hitBoxMultiplier = (float)this.expand.getValue();
            if (this.expand.getValue() == 0.1) {
                HitBox.hitBoxMultiplier = 2.0f;
            }
            if (this.expand.getValue() == 0.2) {
                HitBox.hitBoxMultiplier = 3.0f;
            }
            if (this.expand.getValue() == 0.3) {
                HitBox.hitBoxMultiplier = 4.0f;
            }
            if (this.expand.getValue() == 0.4) {
                HitBox.hitBoxMultiplier = 5.0f;
            }
            if (this.expand.getValue() == 0.5) {
                HitBox.hitBoxMultiplier = 6.0f;
            }
            if (this.expand.getValue() == 0.6) {
                HitBox.hitBoxMultiplier = 7.0f;
            }
            if (this.expand.getValue() == 0.7) {
                HitBox.hitBoxMultiplier = 8.0f;
            }
            if (this.expand.getValue() == 0.8) {
                HitBox.hitBoxMultiplier = 9.0f;
            }
            if (this.expand.getValue() == 0.9) {
                HitBox.hitBoxMultiplier = 10.0f;
            }
            if (this.expand.getValue() == 1.0) {
                HitBox.hitBoxMultiplier = 11.0f;
            }
        }
        this.getMouseOver(1.0f);
    }
    
    private void getMouseOver(final float n) {
        if (Minecraft.getMinecraft().getRenderViewEntity() != null && Minecraft.getMinecraft().theWorld != null) {
            Minecraft.getMinecraft().pointedEntity = null;
            final double n2 = 3.0;
            this.moving = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(n2, n);
            double distanceTo = n2;
            final Vec3 getPositionEyes = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(n);
            if (this.moving != null) {
                distanceTo = this.moving.hitVec.distanceTo(getPositionEyes);
            }
            final Vec3 getLook = Minecraft.getMinecraft().getRenderViewEntity().getLook(n);
            final Vec3 addVector = getPositionEyes.addVector(getLook.xCoord * n2, getLook.yCoord * n2, getLook.zCoord * n2);
            this.pointedEntity = null;
            Vec3 vec3 = null;
            final float n3 = 1.0f;
            final List getEntitiesWithinAABBExcludingEntity = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABBExcludingEntity(Minecraft.getMinecraft().getRenderViewEntity(), Minecraft.getMinecraft().getRenderViewEntity().getEntityBoundingBox().addCoord(getLook.xCoord * n2, getLook.yCoord * n2, getLook.zCoord * n2).expand((double)n3, (double)n3, (double)n3));
            double n4 = distanceTo;
            for (int i = 0; i < getEntitiesWithinAABBExcludingEntity.size(); ++i) {
                final Entity pointedEntity = getEntitiesWithinAABBExcludingEntity.get(i);
                if (pointedEntity.canBeCollidedWith()) {
                    final float n5 = 0.13f * HitBox.hitBoxMultiplier;
                    final AxisAlignedBB expand = pointedEntity.getEntityBoundingBox().expand((double)n5, (double)n5, (double)n5);
                    final MovingObjectPosition calculateIntercept = expand.calculateIntercept(getPositionEyes, addVector);
                    if (expand.isVecInside(getPositionEyes)) {
                        if (0.0 < n4 || n4 == 0.0) {
                            this.pointedEntity = pointedEntity;
                            vec3 = ((calculateIntercept == null) ? getPositionEyes : calculateIntercept.hitVec);
                            n4 = 0.0;
                        }
                    }
                    else if (calculateIntercept != null) {
                        final double distanceTo2 = getPositionEyes.distanceTo(calculateIntercept.hitVec);
                        if (distanceTo2 < n4 || n4 == 0.0) {
                            if (pointedEntity == Minecraft.getMinecraft().getRenderViewEntity().ridingEntity && !pointedEntity.canRiderInteract()) {
                                if (n4 == 0.0) {
                                    this.pointedEntity = pointedEntity;
                                    vec3 = calculateIntercept.hitVec;
                                }
                            }
                            else {
                                this.pointedEntity = pointedEntity;
                                vec3 = calculateIntercept.hitVec;
                                n4 = distanceTo2;
                            }
                        }
                    }
                }
            }
            if (this.pointedEntity != null && (n4 < distanceTo || this.moving == null)) {
                this.moving = new MovingObjectPosition(this.pointedEntity, vec3);
                if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame) {
                    Minecraft.getMinecraft().pointedEntity = this.pointedEntity;
                }
            }
        }
    }
}
