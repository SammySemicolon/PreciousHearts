package com.sleepless_den.precious_hearts.heart_gain.conditions;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.core.*;
import net.minecraft.world.effect.*;

public class SplashPotionCondition extends HeartGainCondition {
    public static Codec<SplashPotionCondition> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            MobEffect.CODEC.fieldOf("effect").forGetter(SplashPotionCondition::getEffect),
            Codec.INT.fieldOf("amplifier").forGetter(SplashPotionCondition::getAmplifier)
    ).apply(obj, SplashPotionCondition::new));

    private final Holder<MobEffect> effect;
    private final int amplifier;

    public SplashPotionCondition(Holder<MobEffect> effect, int amplifier) {
        super(HeartGainConditionRegistry.SPLASH_POTION);
        this.effect = effect;
        this.amplifier = amplifier;
    }

    public Holder<MobEffect> getEffect() {
        return effect;
    }

    public int getAmplifier() {
        return amplifier;
    }
}