/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.games.types;

import net.nevagames.hub.Hub;
import net.nevagames.hub.games.AbstractGame;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TNTZoneGame extends AbstractGame {

    public TNTZoneGame(Hub hub) {
        super(hub);
    }

    @Override
    public String getCodeName() {
        return "tntzone";
    }

    @Override
    public String getName() {
        return "§6Zone TNT";
    }

    @Override
    public String getCategory() {
        return "Seul contre tous";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.TNT, 1);
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "§8La Zone de tous nos jeux Tnt",
                "§8sont réunis !",
                "",
                "§8\u2B29 TntRun",
                "§8\u2B29 BowSpleef",
                "§8\u2B29 TntTag"
        };
    }

    @Override
    public String getDevelopers() {
        return "§6Développeur: FiddlerGun";
    }

    @Override
    public String getWebsiteDescriptionURL() {
        return null;
    }

    @Override
    public int getSlotInMainMenu() {
        return 13;
    }

    @Override
    public Location getLobbySpawn() {
      return new Location(this.hub.getWorld(), 30.839, 105, 36.204, -91.8f, -4.7f);
    }

    @Override
    public Location getWebsiteDescriptionSkull() {
        return null;
    }

    @Override
    public State getState() {
        return State.POPULAR;
    }

    @Override
    public boolean hasResourcesPack() {
        return false;
    }

    @Override
    public boolean isGroup() {
        return false;
    }
}
