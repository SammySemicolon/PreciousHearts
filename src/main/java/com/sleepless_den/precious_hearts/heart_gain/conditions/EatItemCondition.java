package com.sleepless_den.precious_hearts.heart_gain.conditions;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;

public class EatItemCondition extends HeartGainCondition {
    public static Codec<EatItemCondition> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            ItemStack.ITEM_NON_AIR_CODEC.fieldOf("item").forGetter(EatItemCondition::getItem)
    ).apply(obj, EatItemCondition::new));

    private final Holder<Item> item;

    public EatItemCondition(Holder<Item> item) {
        super(HeartGainConditionRegistry.EAT_ITEM);
        this.item = item;
    }

    public Holder<Item> getItem() {
        return item;
    }
}
