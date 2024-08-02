package com.grenades.weapons.item;

import net.minecraft.world.item.Item;

/**
 * A basic item class that implements {@link IAmmo} to indicate this item is ammunition
 *
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class AmmoItem extends Item implements IAmmo
{
    public AmmoItem(Properties properties)
    {
        super(properties);
    }
}
