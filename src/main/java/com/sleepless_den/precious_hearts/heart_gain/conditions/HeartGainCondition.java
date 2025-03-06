package com.sleepless_den.precious_hearts.heart_gain.conditions;

import com.google.gson.*;
import com.mojang.serialization.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;

public class HeartGainCondition {

    private final Holder<HeartGainConditionType> type;

    public HeartGainCondition(Holder<HeartGainConditionType> type) {
        this.type = type;
    }

    public Holder<HeartGainConditionType> getType() {
        return type;
    }

    public static HeartGainCondition parseCondition(JsonObject jsonObject, HolderLookup.Provider provider) {
        var type = HeartGainConditionType.getType(ResourceLocation.parse(jsonObject.get("type").getAsString()));
        final DataResult<? extends HeartGainCondition> parse = type.codec.parse(RegistryOps.create(JsonOps.INSTANCE, provider), jsonObject);
        final HeartGainCondition heartGainCondition = parse.result().orElseThrow();
        return heartGainCondition;
    }

}