package com.csdy.diadema.gula;

import com.csdy.ModMain;
import com.csdy.diadema.ranges.SphereDiademaRange;
import com.csdy.diadema.warden.SonicBoomUtil;
import com.csdy.effect.register.EffectRegister;
import com.csdy.frames.diadema.Diadema;
import com.csdy.frames.diadema.DiademaType;
import com.csdy.frames.diadema.movement.DiademaMovement;
import com.csdy.frames.diadema.range.DiademaRange;
import com.csdy.item.register.ItemRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ModMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GulaDiadema extends Diadema {
    static final double RADIUS = 8;
    private final Player player = getPlayer();

    public GulaDiadema(DiademaType type, DiademaMovement movement) {
        super(type, movement);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override protected void removed() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    private final SphereDiademaRange range = new SphereDiademaRange(this,RADIUS);

    @Override
    public DiademaRange getRange() {
        return range;
    }

    @SubscribeEvent
    public void gula(LivingDeathEvent e) {
        LivingEntity living = e.getEntity();
        if (this.affectingEntities.contains(living)) {
            System.out.println("吃了一个");
            AttributeInstance maxHealthAttr = player.getAttribute(Attributes.MAX_HEALTH);
            double originalMaxHealth = maxHealthAttr.getBaseValue();
            double reducedMaxHealth = originalMaxHealth + living.getMaxHealth()*0.8;
            maxHealthAttr.setBaseValue(reducedMaxHealth);

            AttributeInstance AttackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
            double originalAttack = AttackAttr.getBaseValue();
            double reducedAttack = originalAttack + living.getAttributes().getBaseValue(Attributes.ATTACK_DAMAGE)*0.8;
            AttackAttr.setBaseValue(reducedAttack);

            player.heal(10);
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()+5);
            player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel()+5);
        }
    }
}
