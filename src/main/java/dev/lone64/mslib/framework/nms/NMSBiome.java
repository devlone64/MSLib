package dev.lone64.mslib.framework.nms;

import com.cryptomorin.xseries.XBiome;
import org.bukkit.block.Biome;

public class NMSBiome {

    public static Biome getBiome(XBiome biome) {
        return biome.getBiome();
    }

    public static XBiome or(XBiome biome, XBiome orBiome) {
        return biome.or(orBiome);
    }

    public static boolean isSupported(XBiome biome) {
        return biome.isSupported();
    }

}