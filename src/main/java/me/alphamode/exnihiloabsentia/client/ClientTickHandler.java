package me.alphamode.exnihiloabsentia.client;

import net.minecraft.client.Minecraft;

public final class ClientTickHandler {

    private ClientTickHandler() {}

    public static int ticksInGame = 0;
    public static float partialTicks = 0;


    public static void renderTick(float renderTickTime) {
        partialTicks = renderTickTime;
    }

    public static void clientTickEnd(Minecraft mc) {
        if (!mc.isPaused()) {
            ticksInGame++;
            partialTicks = 0;
        }

    }

}