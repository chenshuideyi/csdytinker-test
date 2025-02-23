package com.csdyms.core;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@SuppressWarnings("unused")
public final class CoreMethod {
    public static float getHealth(LivingEntity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 20.0F;
            case csdykill -> 0.0F;
            case normal -> entity.getHealth();
        };
    }

    public static float getMaxHealth(LivingEntity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 20.0F;
            case csdykill -> 0.0F;
            case normal -> entity.getHealth();
        };
    }

    public static int getDeathTime(LivingEntity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 0;
            case csdykill, normal -> entity.deathTime;
        };
    }

    public static int getHurtTime(LivingEntity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 0;
            case csdykill, normal -> entity.hurtTime;
        };
    }

    public static int getHurtDuration(LivingEntity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 0;
            case csdykill, normal -> entity.hurtDuration;
        };
    }

    public static Entity.RemovalReason getRemovalReason(Entity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> null;
            case csdykill -> Entity.RemovalReason.KILLED;
            case normal -> entity.removalReason;
        };
    }

    public static boolean isRemoved(Entity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> false;
            case csdykill -> true;
            case normal -> entity.isRemoved();
        };
    }

    public static boolean isAlive(Entity entity) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> true;
            case csdykill -> false;
            case normal -> entity.isAlive();
        };
    }

    public static boolean isDeadOrDying(LivingEntity living) {
        return switch (EntityUntil.getCategory(living)) {
            case csdy -> false;
            case csdykill -> true;
            case normal -> living.isDeadOrDying();
        };
    }

    public static boolean shouldDestroy(Entity.RemovalReason reason) {
        return reason != null && reason.shouldDestroy();
    }

    public static boolean shouldSave(Entity.RemovalReason reason) {
        return reason == null || reason.shouldSave();
    }

    public static float getHealth(Entity entity, Float original) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 20.0F;
            case csdykill -> 0.0F;
            case normal -> original;
        };
    }

    public static float getMaxHealth(Entity entity, Float original) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> 20.0F;
            case csdykill -> 0.0F;
            case normal -> original;
        };
    }


    public static boolean isAlive(Entity entity, Boolean original) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> true;
            case csdykill -> false;
            case normal -> original;
        };
    }

    public static boolean isDeadOrDying(Entity entity, Boolean original) {
        return switch (EntityUntil.getCategory(entity)) {
            case csdy -> false;
            case csdykill -> true;
            case normal -> original;
        };
    }
}
