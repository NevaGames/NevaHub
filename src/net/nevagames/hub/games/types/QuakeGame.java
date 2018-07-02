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

public class QuakeGame extends AbstractGame {
    public QuakeGame(Hub hub)
    {
        super(hub);
    }

    @Override
    public String getCodeName()
    {
        return "quake";
    }

    @Override
    public String getName()
    {
        return "§6Quake";
    }

    @Override
    public String getCategory()
    {
        return "Match à mort";
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(Material.DIAMOND_HOE, 1);
    }

    @Override
    public String[] getDescription(){
        return new String[] {
                "§8Même si vous n'êtes pas un fermier,",
                "§8tirez sur vos adversaires avec",
                "§8votre houe !"
        };
    }

    @Override
    public String getDevelopers(){
        return "§6Développeur: FiddlerGun";
    }

    @Override
    public String getWebsiteDescriptionURL()
    {
        return null;
    }

    @Override
    public int getSlotInMainMenu()
    {
        return 23;
    }

    @Override
    public Location getLobbySpawn()
    {
        return new Location(this.hub.getWorld(), -53.748, 97, 42.266, 51.7f, 10.1f);
    }

    @Override
    public Location getWebsiteDescriptionSkull()
    {
        return null;
    }

    @Override
    public State getState()
    {
        return State.LOCKED;
    }

    @Override
    public boolean hasResourcesPack()
    {
        return false;
    }

    @Override
    public boolean isGroup()
    {
        return false;
    }
}
