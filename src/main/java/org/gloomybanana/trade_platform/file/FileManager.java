package org.gloomybanana.trade_platform.file;

import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.gloomybanana.trade_platform.handler.Config;

import java.io.File;

public class FileManager {
    public static File getWorldFolder(World world) {
        File worldPath;
        Boolean enalbeCustomPath = Config.ENABLE_CUSTOM_PATH.get();
        String customFolderPath = Config.PATH.get();
        if(enalbeCustomPath){
            return world.getServer().getDataDirectory().toPath().resolve(customFolderPath).toFile();
        }
        String worldName = world.toString();
        String folderName = worldName.toString().substring(worldName.indexOf("[")+1,worldName.indexOf("]"));
        if (FMLEnvironment.dist.isDedicatedServer()) {
            worldPath = world.getServer().getDataDirectory().toPath().resolve(folderName).toFile();
        } else {
            worldPath = world.getServer().getDataDirectory().toPath().resolve("saves").resolve(folderName).toFile();
        }
        if (!worldPath.exists()) {
            worldPath.mkdirs();
        }
        return worldPath;
    }
}
