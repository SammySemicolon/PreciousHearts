package com.sleepless_den.precious_hearts.the_cursed_lands;

import com.mojang.datafixers.*;
import net.minecraft.advancements.*;
import net.minecraft.server.*;
import net.minecraft.server.level.*;
import net.minecraft.server.players.*;

import java.nio.file.*;

public class FakePlayerAdvancements extends PlayerAdvancements {
    public FakePlayerAdvancements(DataFixer dataFixer, PlayerList playerList, ServerAdvancementManager manager, Path playerSavePath, ServerPlayer player) {
        super(dataFixer, playerList, manager, playerSavePath, player);
    }

    @Override
    public boolean award(AdvancementHolder advancement, String criterionKey) {
        final boolean award = super.award(advancement, criterionKey);

        AdvancementProgress advancementprogress = this.getOrStartProgress(advancement);
        advancementprogress.revokeProgress(criterionKey);
        registerListeners(advancement);
        progressChanged.clear();
        return award;
    }
}