package com.sleepless_den.precious_hearts.datagen;

import com.sleepless_den.precious_hearts.PreciousHeartsMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = PreciousHeartsMod.PRECIOUS_HEARTS, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PreciousHeartsLangDatagen(output));

    }
}
