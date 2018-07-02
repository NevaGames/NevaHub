/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.cosmetics;

import net.nevagames.api.gui.AbstractGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiParticles extends AbstractGui {

    @Override
    public String name() {
        return "Particles";
    }

    @Override
    public void contents(Player player, Inventory inventory) {

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

    }

    @Override
    public int getSize() {
        return 54;
    }
}
