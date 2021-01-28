package org.gloomybanana.trade_platform;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gloomybanana.trade_platform.handler.Config;
import org.gloomybanana.trade_platform.handler.Registry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("trade_platform")
public class TradePlatform
{
    public static final String MOD_ID = "trade_platform";
    public static final String MOD_NAME = "Trade Platform";
    public static final String MOD_VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger();
    public TradePlatform() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        Registry.CONTAINERS_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Thanks for using Datapack Loottable Maker");
    }
}
