package com.sleepless_den.precious_hearts.heart_gain.conditions;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import com.sleepless_den.precious_hearts.registry.*;
import com.sleepless_den.precious_hearts.reload_listener.*;
import com.sleepless_den.precious_hearts.the_cursed_lands.*;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.*;
import net.minecraft.server.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.storage.*;
import net.minecraft.world.level.storage.loot.*;

import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class CompleteAdvancementCondition extends HeartGainCondition {

    public static final HashMap<UUID, PlayerAdvancements> FAKE_ADVANCEMENTS = new HashMap<>();

    private static final Codec<Map<String, Criterion<?>>> CRITERIA_CODEC = Codec.unboundedMap(Codec.STRING, Criterion.CODEC)
            .validate(p_311380_ -> p_311380_.isEmpty() ? DataResult.error(() -> "Advancement criteria cannot be empty") : DataResult.success(p_311380_));
    public static Codec<CompleteAdvancementCondition> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            ResourceLocation.CODEC.fieldOf("advancementId").forGetter(CompleteAdvancementCondition::getAdvancementId)
    ).apply(obj, CompleteAdvancementCondition::new));

    private final ResourceLocation advancementId;

    public CompleteAdvancementCondition(ResourceLocation advancementId) {
        super(HeartGainConditionRegistry.MEET_CRITERION);
        this.advancementId = advancementId;
    }

    public ResourceLocation getAdvancementId() {
        return advancementId;
    }

    public static <T extends SimpleCriterionTrigger.SimpleInstance> void trigger(SimpleCriterionTrigger<T> trigger, ServerPlayer player, Predicate<T> testTrigger) {
        PlayerAdvancements playeradvancements = getFakeAdvancements(player);
        Set<CriterionTrigger.Listener<T>> set = trigger.players.get(playeradvancements);
        boolean isNull = set == null;
        if (!isNull) {
            float f = 0;
        }
        if (set != null && !set.isEmpty()) {
            LootContext lootcontext = EntityPredicate.createContext(player, player);
            List<CriterionTrigger.Listener<T>> list = null;

            for (CriterionTrigger.Listener<T> listener : set) {
                T t = listener.trigger();
                if (testTrigger.test(t)) {
                    Optional<ContextAwarePredicate> optional = t.player();
                    if (optional.isEmpty() || optional.get().matches(lootcontext)) {
                        if (list == null) {
                            list = Lists.newArrayList();
                        }

                        list.add(listener);
                    }
                }
            }

            if (list != null) {
                for (CriterionTrigger.Listener<T> listener1 : list) {
                    listener1.run(playeradvancements);
                }
            }
        }
    }


    public static PlayerAdvancements createFakeAdvancements(ServerPlayer player) {
        UUID uuid = player.getUUID();
        final MinecraftServer server = player.server;
        Path path = server.getWorldPath(LevelResource.PLAYER_ADVANCEMENTS_DIR).resolve(uuid + "_fake.json");
        PlayerAdvancements playeradvancements = new FakePlayerAdvancements(server.getFixerUpper(), server.getPlayerList(), server.getAdvancements(), path, player);
        playeradvancements.stopListening();
        FAKE_ADVANCEMENTS.put(uuid, playeradvancements);

        var advancements = HeartGainConditionsReloadListener.getAllAdvancements(CompleteAdvancementCondition.class);
        for (HeartGainAdvancement<CompleteAdvancementCondition> advancement : advancements) {
            final ResourceLocation id = advancement.getCondition().getAdvancementId();
            var advancementHolder = player.server.getAdvancements().get(id);
            var criteriaList = advancementHolder.value().criteria();
            for (Map.Entry<String, Criterion<?>> entry : criteriaList.entrySet()) {
                var criteria = entry.getValue();
                var criteriaId = entry.getKey();
                var trigger = criteria.trigger();
                var fakeAssCriterion = new CriterionTrigger.Listener(criteria.triggerInstance(), advancementHolder, criteriaId);
                trigger.addPlayerListener(playeradvancements, fakeAssCriterion);
            }
        }
        return playeradvancements;
    }
    public static PlayerAdvancements getFakeAdvancements(ServerPlayer player) {
        UUID uuid = player.getUUID();
        PlayerAdvancements playeradvancements = FAKE_ADVANCEMENTS.get(uuid);
        if (playeradvancements == null) {
            playeradvancements = createFakeAdvancements(player);
        }
        return playeradvancements;
    }
}