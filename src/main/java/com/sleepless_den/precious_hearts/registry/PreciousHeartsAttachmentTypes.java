package com.sleepless_den.precious_hearts.registry;

import com.sleepless_den.precious_hearts.*;
import com.sleepless_den.precious_hearts.death_absorption.*;
import com.sleepless_den.precious_hearts.heart_gain.*;
import net.neoforged.neoforge.attachment.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class PreciousHeartsAttachmentTypes {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, PreciousHeartsMod.PRECIOUS_HEARTS);

    public static final Supplier<AttachmentType<HeartGainPlayerData>> HEART_GAIN_DATA = ATTACHMENT_TYPES.register(
            "heart_gain_data", () -> AttachmentType.builder(HeartGainPlayerData::new).serialize(HeartGainPlayerData.CODEC).copyOnDeath().build());

    public static final Supplier<AttachmentType<DeathAbsorptionData>> DEATH_ABSORPTION_DATA = ATTACHMENT_TYPES.register(
            "death_absorption_data", () -> AttachmentType.builder(i -> new DeathAbsorptionData()).serialize(DeathAbsorptionData.CODEC).copyOnDeath().build());

}
