package com.sleepless_den.precious_hearts.registry;

import com.sleepless_den.precious_hearts.*;
import net.neoforged.neoforge.attachment.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class AttachmentTypeRegistry {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, PreciousHeartsMod.PRECIOUS_HEARTS);

    public static final Supplier<AttachmentType<PreciousHeartsPlayerData>> PRECIOUS_HEARTS_DATA = ATTACHMENT_TYPES.register(
            "precious_hearts_data", () -> AttachmentType.builder(PreciousHeartsPlayerData::new).serialize(PreciousHeartsPlayerData.CODEC).copyOnDeath().build());

}
