package com.sleepless_den.precious_hearts.registry;

import com.sleepless_den.precious_hearts.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.systems.attribute.*;

import static team.lodestar.lodestone.registry.common.LodestoneAttributes.registerAttribute;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class PreciousHeartsAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, PreciousHeartsMod.PRECIOUS_HEARTS);

    public static final DeferredHolder<Attribute, Attribute> DEATH_ABSORPTION_LIMIT = registerAttribute(ATTRIBUTES,
            LodestoneRangedAttribute.create(PreciousHeartsMod.path("death_absorption_limit"), 0.0D, 0.0D, 2048.0D).setSyncable(true));

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(e -> {
            for (DeferredHolder<Attribute, ? extends Attribute> entry : ATTRIBUTES.getEntries()) {
                event.add(e, entry);
            }
        });
    }
}