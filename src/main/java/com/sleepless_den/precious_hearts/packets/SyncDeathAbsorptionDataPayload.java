package com.sleepless_den.precious_hearts.packets;

import com.sleepless_den.precious_hearts.death_absorption.*;
import com.sleepless_den.precious_hearts.registry.*;
import net.minecraft.network.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.network.handling.*;
import team.lodestar.lodestone.systems.network.*;

public class SyncDeathAbsorptionDataPayload extends OneSidedPayloadData {
    private final int entityId;
    private final DeathAbsorptionData data;

    public SyncDeathAbsorptionDataPayload(int entityId, DeathAbsorptionData data) {
        this.entityId = entityId;
        this.data = data;
    }

    public SyncDeathAbsorptionDataPayload(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.data = DeathAbsorptionData.STREAM_CODEC.decode(buf);
    }

    public void handle(IPayloadContext context) {
        Entity entity = context.player().level().getEntity(entityId);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.setData(PreciousHeartsAttachmentTypes.DEATH_ABSORPTION_DATA, data);
        }
    }

    @Override
    public void serialize(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        DeathAbsorptionData.STREAM_CODEC.encode(buf, data);
    }
}