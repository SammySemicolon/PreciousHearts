package com.sleepless_den.magic_magic_damage.data;

import com.sleepless_den.magic_magic_damage.MagicMagicDamageMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new MagicMagicDamageLangDatagen(output));
        generator.addProvider(event.includeClient(), new MagicMagicDamageTypeTags(output, provider, helper));
    }
}
