package me.alphamode.exnihiloabsentia.client;

import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class AbsentiaTextures {
    public static final String PREFIX_MISC = ExNihiloAbsentia.MOD_ID + ":textures/misc/";

    public static final String MISC_SKYBOX = PREFIX_MISC + "skybox.png";
    public static final String MISC_RAINBOW = PREFIX_MISC + "rainbow.png";
    public static final String MISC_PLANET = PREFIX_MISC + "planet";


    public static TextureAtlasSprite getBlockTexture(Block block) {
        ResourceLocation id = Registry.BLOCK.getKey(block);
        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
    }

    public static Color getBlockColor(Block block, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos) {
        return new Color(Minecraft.getInstance().getBlockColors().getColor(block.defaultBlockState(), level, pos, 0));
    }

    public static Color getBlockColor(Block block) {
        return new Color(Minecraft.getInstance().getBlockColors().getColor(block.defaultBlockState(), null, null, 0));
    }
}
