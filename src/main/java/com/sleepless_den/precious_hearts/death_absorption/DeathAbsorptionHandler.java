package com.sleepless_den.precious_hearts.death_absorption;

import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;

public class DeathAbsorptionHandler {

    public static void recoverDeathAbsorption(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        data.recoverDeathAbsorption(player, 2);
    }

    public static boolean preventDeath(ServerPlayer player) {
        var data = player.getData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA);
        if (data.consumeDeathAbsorption()) {
            return true;
        }
        return false;
    }
}
