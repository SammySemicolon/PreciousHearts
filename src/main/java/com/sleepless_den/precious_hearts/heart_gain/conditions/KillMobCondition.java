package com.sleepless_den.precious_hearts.heart_gain.conditions;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.entity.*;

public class KillMobCondition extends HeartGainCondition {
    public static Codec<KillMobCondition> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            BuiltInRegistries.ENTITY_TYPE.holderByNameCodec().fieldOf("entity").forGetter(KillMobCondition::getEntity)
    ).apply(obj, KillMobCondition::new));

    private final Holder<EntityType<?>> entity;

    public KillMobCondition(Holder<EntityType<?>> entity) {
        super(HeartGainConditionRegistry.KILL_MOB);
        this.entity = entity;
    }

    public Holder<EntityType<?>> getEntity() {
        return entity;
    }
}
