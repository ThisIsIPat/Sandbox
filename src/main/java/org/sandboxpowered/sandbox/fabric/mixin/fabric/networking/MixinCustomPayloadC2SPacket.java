package org.sandboxpowered.sandbox.fabric.mixin.fabric.networking;

import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import org.sandboxpowered.sandbox.fabric.internal.CustomPayloadPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CustomPayloadC2SPacket.class)
public class MixinCustomPayloadC2SPacket implements CustomPayloadPacket {
    @Shadow
    private Identifier channel;

    @Shadow
    private PacketByteBuf data;

    @Override
    public Identifier channel() {
        return this.channel;
    }

    @Override
    public PacketByteBuf data() {
        return this.data;
    }
}
