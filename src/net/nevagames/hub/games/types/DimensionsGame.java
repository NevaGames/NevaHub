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

public class DimensionsGame extends AbstractGame {
    public DimensionsGame(Hub hub)
    {
        super(hub);
    }

    @Override
    public String getCodeName()
    {
        return "dimensions";
    }

    @Override
    public String getName()
    {
        return "§6Dimensions";
    }

    @Override
    public String getCategory()
    {
        return "PvP";
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(Material.EYE_OF_ENDER, 1);
    }

    @Override
    public String[] getDescription(){
        return new String[] {
                "§8Téléportez-vous d'un monde parallèle",
                "§8à un autre et récupérez le maximum de",
                "§8coffres. Ensuite, le combat pourra",
                "§8débuter !"
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
        return 22;
    }

    @Override
    public Location getLobbySpawn(){
        return new Location(this.hub.getWorld(), 29.213, 102, -29.469, -30.3f, -1.2f);
    }

    @Override
    public Location getWebsiteDescriptionSkull(){
        return new Location(this.hub.getWorld(), 0.0, 87.0D, -49.0D, 0.0F, 0.0F);
    }


    @Override
    public AbstractGame.State getState()
    {
        return State.SOON;
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
