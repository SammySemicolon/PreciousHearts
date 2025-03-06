package com.sleepless_den.precious_hearts.registry;

import com.sleepless_den.precious_hearts.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

public class HeartGainConditionRegistry {

    public static ResourceKey<Registry<HeartGainConditionType>> KEY = ResourceKey.createRegistryKey(PreciousHeartsMod.path("conditions"));
    public static final DeferredRegister<HeartGainConditionType> CONDITIONS = DeferredRegister.create(KEY, PreciousHeartsMod.PRECIOUS_HEARTS);
    public static final Registry<HeartGainConditionType> CONDITIONS_REGISTRY = CONDITIONS.makeRegistry(builder -> builder.sync(true));

    public static final DeferredHolder<HeartGainConditionType, HeartGainConditionType> EAT_ITEM = CONDITIONS.register("eat_item", () -> new HeartGainConditionType(EatItemCondition.CODEC));
    public static final DeferredHolder<HeartGainConditionType, HeartGainConditionType> SPLASH_POTION = CONDITIONS.register("splash_potion", () -> new HeartGainConditionType(SplashPotionCondition.CODEC));
    public static final DeferredHolder<HeartGainConditionType, HeartGainConditionType> KILL_MOB = CONDITIONS.register("kill_mob", () -> new HeartGainConditionType(KillMobCondition.CODEC));

    public static final DeferredHolder<HeartGainConditionType, HeartGainConditionType> MEET_CRITERION = CONDITIONS.register("meet_criteria", () -> new HeartGainConditionType(CompleteAdvancementCondition.CODEC));

}
