package com.sleepless_den.precious_hearts.death_absorption;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sleepless_den.precious_hearts.registry.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.entity.*;

public class DeathAbsorptionData {

    public static Codec<DeathAbsorptionData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.fieldOf("deathAbsorption").forGetter(sw -> sw.deathAbsorption)
    ).apply(obj, DeathAbsorptionData::new));

    public static StreamCodec<ByteBuf, DeathAbsorptionData> STREAM_CODEC = ByteBufCodecs.fromCodec(DeathAbsorptionData.CODEC);

    private int deathAbsorption;

    private boolean isDirty;

    public DeathAbsorptionData() {
    }

    public DeathAbsorptionData(int  deathAbsorption) {
        this.deathAbsorption = deathAbsorption;
    }

    public void recoverDeathAbsorption(LivingEntity entity, int amount) {
        var capacity = entity.getAttribute(PreciousHeartsAttributes.DEATH_ABSORPTION_LIMIT);
        int limit = (int) capacity.getValue();
        if (getDeathAbsorption() < limit) {
            setDeathAbsorption(Math.min(deathAbsorption + amount, limit));
        }
    }

    public boolean consumeDeathAbsorption() {
        if (deathAbsorption > 0) {
            setDeathAbsorption(deathAbsorption-1);
            return true;
        }
        return false;
    }

    public void setDeathAbsorption(int deathAbsorption) {
        this.deathAbsorption = Math.max(deathAbsorption, 0);
        isDirty = true;
    }

    public double getDeathAbsorption() {
        return deathAbsorption;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }
}