package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccess
{
    /**
     * Returns the block ID at coords x,y,z
     */
    int getBlockId(int var1, int var2, int var3);

    /**
     * Returns the TileEntity associated with a given block in X,Y,Z coordinates, or null if no TileEntity exists
     */
    TileEntity getBlockTileEntity(int var1, int var2, int var3);

    @SideOnly(Side.CLIENT)
    int func_72802_i(int var1, int var2, int var3, int var4);

    /**
     * Returns the block metadata at coords x,y,z
     */
    int getBlockMetadata(int var1, int var2, int var3);

    @SideOnly(Side.CLIENT)
    float func_72808_j(int var1, int var2, int var3, int var4);

    @SideOnly(Side.CLIENT)

    /**
     * Returns how bright the block is shown as which is the block's light value looked up in a lookup table (light
     * values aren't linear for brightness). Args: x, y, z
     */
    float getLightBrightness(int var1, int var2, int var3);

    /**
     * Returns the block's material.
     */
    Material getBlockMaterial(int var1, int var2, int var3);

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the block at the specified coordinates is an opaque cube. Args: x, y, z
     */
    boolean isBlockOpaqueCube(int var1, int var2, int var3);

    /**
     * Indicate if a material is a normal solid opaque cube.
     */
    boolean isBlockNormalCube(int var1, int var2, int var3);

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the block at the specified coordinates is empty
     */
    boolean isAirBlock(int var1, int var2, int var3);

    @SideOnly(Side.CLIENT)

    /**
     * Gets the biome for a given set of x/z coordinates
     */
    BiomeGenBase getBiomeGenForCoords(int var1, int var2);

    @SideOnly(Side.CLIENT)

    /**
     * Returns current world height.
     */
    int getHeight();

    @SideOnly(Side.CLIENT)
    boolean func_72806_N();

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the block at the given coordinate has a solid (buildable) top surface.
     */
    boolean doesBlockHaveSolidTopSurface(int var1, int var2, int var3);

    /**
     * Return the Vec3Pool object for this world.
     */
    Vec3Pool getWorldVec3Pool();

    /**
     * Is this block powering in the specified direction Args: x, y, z, direction
     */
    int isBlockProvidingPowerTo(int var1, int var2, int var3, int var4);
}
