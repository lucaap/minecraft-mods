package net.minecraft.entity.ai.attributes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.UUID;

public interface AttributeInstance
{
    Attribute func_111123_a();

    double getBaseValue();

    void setAttribute(double var1);

    Collection func_111122_c();

    /**
     * Returns attribute modifier, if any, by the given UUID
     */
    AttributeModifier getModifier(UUID var1);

    void applyModifier(AttributeModifier var1);

    void removeModifier(AttributeModifier var1);

    @SideOnly(Side.CLIENT)
    void func_142049_d();

    double getAttributeValue();
}
