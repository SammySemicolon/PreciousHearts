package com.sleepless_den.precious_hearts.reload_listener;

import com.google.gson.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import com.sleepless_den.precious_hearts.heart_gain.conditions.*;
import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.*;
import net.minecraft.util.profiling.*;
import net.neoforged.neoforge.event.*;

import java.util.*;

public class HeartGainConditionsReloadListener extends SimpleJsonResourceReloadListener {
    public static Map<ResourceLocation, HeartGainAdvancement<?>> HEART_GAIN_ADVANCEMENTS = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();

    public HeartGainConditionsReloadListener() {
        super(GSON, "heart_gain_conditions");
    }

    public static void register(AddReloadListenerEvent event) {
        event.addListener(new HeartGainConditionsReloadListener());
    }

    @SuppressWarnings("unchecked")
    public static <T extends HeartGainCondition> List<HeartGainAdvancement<T>> getAllAdvancements(Class<T> conditionType) {
        return HEART_GAIN_ADVANCEMENTS.values().stream()
                .filter(advancement -> conditionType.isAssignableFrom(advancement.getCondition().getClass()))
                .map(advancement -> (HeartGainAdvancement<T>) advancement)
                .toList();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        HEART_GAIN_ADVANCEMENTS.clear();
        for (int i = 0; i < objectIn.size(); i++) {
            ResourceLocation location = (ResourceLocation) objectIn.keySet().toArray()[i];
            JsonObject object = objectIn.get(location).getAsJsonObject();
            ResourceLocation id = ResourceLocation.parse(object.getAsJsonPrimitive("id").getAsString());

            var condition = HeartGainCondition.parseCondition(object, getRegistryLookup());
            HEART_GAIN_ADVANCEMENTS.put(id, new HeartGainAdvancement(condition, id));
        }
    }
}