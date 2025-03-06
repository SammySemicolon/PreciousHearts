package com.sleepless_den.precious_hearts.heart_gain;

import com.mojang.datafixers.util.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;

import java.util.*;

public final class HeartGainAdvancement<T extends HeartGainCondition> {
    private final T condition;
    private final ResourceLocation id;

    public HeartGainAdvancement(T condition, ResourceLocation id) {
        this.condition = condition;
        this.id = id;
    }

    public T getCondition() {
        return condition;
    }

    public ResourceLocation getId() {
        return id;
    }
}
