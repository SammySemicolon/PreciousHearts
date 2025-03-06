package com.sleepless_den.magic_magic_damage.data;

import com.sleepless_den.magic_magic_damage.*;
import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;

import java.util.concurrent.*;

public class MagicMagicDamageTypeTags extends DamageTypeTagsProvider {

    public MagicMagicDamageTypeTags(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MagicMagicDamageMod.MAGIC_MAGIC_DAMAGE, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
                .remove(DamageTypes.WITHER)
                .remove(DamageTypes.MAGIC);

        tag(Tags.DamageTypes.IS_MAGIC)
                .add(MagicMagicDamageDamageTypes.MAGIC_MOB_ATTACK)
                .add(DamageTypes.FIREBALL)
                .add(DamageTypes.UNATTRIBUTED_FIREBALL)
                .add(DamageTypes.WITHER)
                .add(DamageTypes.WITHER_SKULL)
                .addOptional(ResourceLocation.parse("irons_spellbooks:fire_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:ice_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:lightning_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:holy_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:ender_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:blood_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:evocation_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:eldritch_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:nature_magic"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:heartstop"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:dragon_breath_pool"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:fire_field"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:posion_cloud"));
    }
}