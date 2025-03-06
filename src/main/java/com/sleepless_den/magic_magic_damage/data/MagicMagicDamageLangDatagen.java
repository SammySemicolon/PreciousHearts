package com.sleepless_den.magic_magic_damage.data;

import com.sleepless_den.magic_magic_damage.MagicMagicDamageMod;
import net.minecraft.core.registries.*;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MagicMagicDamageLangDatagen extends LanguageProvider {
    public MagicMagicDamageLangDatagen(PackOutput gen) {
        super(gen, MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("effect.magic_magic_damage.armor_corrosion", "Armor Corrosion");

        add("death.attack.magic_mob", "%1$s was slain by %2$s");
        add("death.attack.magic_mob.item", "%1$s was slain by %2$s using %3$s");
    }
}