package com.sleepless_den.precious_hearts.death_absorption;

import com.sleepless_den.precious_hearts.packets.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.network.*;

public class DeathAbsorptionHandler {

    public static void recoverDeathAbsorption(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        data.recoverDeathAbsorption(player, 2);
        syncDeathAbsorption(player);
    }

    public static boolean preventDeath(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        if (data.consumeDeathAbsorption()) {
            syncDeathAbsorption(player);
            return true;
        }
        return false;
    }

    public static void syncDeathAbsorption(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        if (data.isDirty()) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new SyncDeathAbsorptionDataPayload(player.getId(), data));
            data.setDirty(false);
        }
    }

    public static void markDirty(ServerPlayer player) {
        player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA).setDirty(true);
    }
}