package com.sleepless_den.precious_hearts.config;

import net.neoforged.neoforge.common.*;
import org.apache.commons.lang3.tuple.*;
import team.lodestar.lodestone.systems.config.*;

import static com.sleepless_den.precious_hearts.PreciousHeartsMod.PRECIOUS_HEARTS;

public class CommonConfig extends LodestoneConfig {

    public static ConfigValueHolder<Integer> HEART_LOSS_ON_DEATH = new ConfigValueHolder<>(PRECIOUS_HEARTS, "common/hearts", (builder ->
            builder.comment("Defines the amount of half hearts a player loses when dying. Set to Zero to disable.")
                    .define("heartLoss", 2)));

    public static ConfigValueHolder<Integer> HEART_GAIN_PER_TASK = new ConfigValueHolder<>(PRECIOUS_HEARTS, "common/hearts", (builder ->
            builder.comment("Defines the amount of half hearts a player gains when completing a task. Set to Zero to disable.")
                    .define("heartGain", 2)));

    public static ConfigValueHolder<Integer> HEART_MINIMUM_AMOUNT = new ConfigValueHolder<>(PRECIOUS_HEARTS, "common/hearts", (builder ->
            builder.comment("Defines the lower limit of health each player can reach by losing hearts. If set to Zero, heartless players will be put into spectator mode.")
                    .define("heartMinimum", 2)));

    public static ConfigValueHolder<Integer> HEART_MAXIMUM_AMOUNT = new ConfigValueHolder<>(PRECIOUS_HEARTS, "common/hearts", (builder ->
            builder.comment("Defines the upper limit of health each player can reach by gaining hearts.")
                    .define("heartMaximum", 20)));

    public static ConfigValueHolder<Integer> HEART_BASE_AMOUNT = new ConfigValueHolder<>(PRECIOUS_HEARTS, "common/hearts", (builder ->
            builder.comment("Defines the base amount of health each player has in half hearts.")
                    .define("heartBase", 10)));

    public CommonConfig(ModConfigSpec.Builder builder) {
        super(PRECIOUS_HEARTS, "common", builder);
    }

    public static final CommonConfig INSTANCE;
    public static final ModConfigSpec SPEC;

    static {
        final Pair<CommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }
}
