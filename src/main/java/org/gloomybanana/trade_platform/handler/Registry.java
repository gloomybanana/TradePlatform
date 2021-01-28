package org.gloomybanana.trade_platform.handler;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.gloomybanana.trade_platform.TradePlatform;
import org.gloomybanana.trade_platform.container.SellContainer;

public class Registry {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, TradePlatform.MOD_ID);

    //Container
    public static RegistryObject<ContainerType<SellContainer>> entryListContainer = CONTAINERS_TYPE.register("entry_list_container",()->IForgeContainerType.create(SellContainer::new));
}
