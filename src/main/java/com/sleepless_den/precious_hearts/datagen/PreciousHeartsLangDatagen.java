package com.sleepless_den.precious_hearts.datagen;

import com.sleepless_den.precious_hearts.PreciousHeartsMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class PreciousHeartsLangDatagen extends LanguageProvider {
    public PreciousHeartsLangDatagen(PackOutput gen) {
        super(gen, PreciousHeartsMod.PRECIOUS_HEARTS, "en_us");
    }

    @Override
    protected void addTranslations() {
        addHeartLossMessages(
                "If I had a dollar for your every death I'd have... Wait no, I lost count",
                "Karma! Well... Maybe, I don't know. These messages are random",
                "Your consciousness fades and rises again and yet",
                "You feel you've lost something core to you",
                "A part of your soul seems to have vanished",
                "This is going to be a terrible night...",
                "You've been daring one too many times",
                "The world rejects your very influence",
                "Apparently rock bottom has a basement",
                "One step closer to endless darkness",
                "An ominous feeling looms over you",
                "You are overwhelmed with pain...",
                "We'll pretend we didn't see that",
                "Perhaps, it was meant to happen",
                "Be honest, that was your fault",
                "It is not yet over, but still",
                "Another few steps down below",
                "Nothing more. Nothing less",
                "Do not let it consume you",
                "The world is not so kind",
                "Death comes for us all",
                "Be luckier next time",
                "You feel weakened",
                "A grave setback",
                "Ash and Bones, all that'll be left of you %1$s",
                "I hope you fall into a ditch %1$s",
                "That was a grave mistake, %1$s",
                "You're not doing so hot %1$s",
                "You must endure this, %1$s",
                "Do better next time %1$s",
                "That was not wise, %1$s",
                "C'mon %1$s...",
                "Damn, %1$s",
                "Overconfidence is a slow and insidious killer",
                "On the brink, facing the abyss",
                "DO IT AGAIN! AGAIN! AGAIN!!",
                "Roll the credits",
                "OUCH",
                "Lock in %1$s!",
                "Yikes...");
        addHeartGainMessages(
                "Good one %1$s! ... But just one",
                "You feel invigorated...",
                "The air seems... Fresher",
                "You feel stronger... Somehow",
                "Don't let your guard down now, %1$s",
                "A victory! Perhaps a turning point",
                "GLOOOORY!!",
                "DO WHAT COMES NATURAL",
                "Locked in");
    }

    public void addHeartLossMessages(String... message) {
        int i = 0;
        for (String s : message) {
            addHeartLossMessage(s, i);
            i++;
        }
    }

    public void addHeartLossMessage(String message, int id) {
        add("message.precious_hearts.drain." + id, message);
    }

    public void addHeartGainMessages(String... message) {
        int i = 0;
        for (String s : message) {
            addHeartGainMessage(s, i);
            i++;
        }
    }

    public void addHeartGainMessage(String message, int id) {
        add("message.precious_hearts.gain." + id, message);
    }
}