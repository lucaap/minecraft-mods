package net.minecraft.world.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;

public interface ISaveFormat
{
    /**
     * Returns back a loader for the specified save directory
     */
    ISaveHandler getSaveLoader(String var1, boolean var2);

    @SideOnly(Side.CLIENT)
    List func_75799_b() throws AnvilConverterException;

    void flushCache();

    @SideOnly(Side.CLIENT)

    /**
     * gets the world info
     */
    WorldInfo getWorldInfo(String var1);

    /**
     * @args: Takes one argument - the name of the directory of the world to delete. @desc: Delete the world by deleting
     * the associated directory recursively.
     */
    boolean deleteWorldDirectory(String var1);

    @SideOnly(Side.CLIENT)
    void func_75806_a(String var1, String var2);

    /**
     * Checks if the save directory uses the old map format
     */
    boolean isOldMapFormat(String var1);

    /**
     * Converts the specified map to the new map format. Args: worldName, loadingScreen
     */
    boolean convertMapFormat(String var1, IProgressUpdate var2);

    @SideOnly(Side.CLIENT)
    boolean func_90033_f(String var1);
}
