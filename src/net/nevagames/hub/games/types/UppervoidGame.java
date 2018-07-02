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

public class UppervoidGame extends AbstractGame {
    public UppervoidGame(Hub hub){
        super(hub);
    }

    @Override
    public String getCodeName(){
        return "uppervoid";
    }

    @Override
    public String getName(){
        return "§6Uppervoid";
    }

    @Override
    public String getCategory(){
        return "Seul contre tous";
    }

    @Override
    public ItemStack getIcon(){
        return new ItemStack(Material.STICK, 1);
    }

    @Override
    public String[] getDescription(){
        return new String[] {
                "§8Affrontez les autres joueurs dans",
                "§8une arène. Faites-les tomber dans le",
                "§8vide à l'aide de vos impitoyables",
                "§8TNT's, mais gare à ne pas tomber",
                "§8à votre tour !"
        };
    }

    @Override
    public String getDevelopers(){
        return  "§6Développeur: FiddlerGun";

    }

    @Override
    public String getWebsiteDescriptionURL(){
        return null;
    }

    @Override
    public int getSlotInMainMenu(){
        return 21;
    }



    @Override
    public Location getLobbySpawn(){
        return new Location(this.hub.getWorld(), -6.5, 82.0, 68.5, 2.8F, 4.6F);
    }

    @Override
    public Location getWebsiteDescriptionSkull(){
        return new Location(this.hub.getWorld(), -1.0, 83.0D, 73.0D, 4.2f, -1.4F);
    }

    @Override
    public State getState(){
        return State.OPENED;
    }

    @Override
    public boolean hasResourcesPack() {
        return false;
    }

    @Override
    public boolean isGroup()
    {
        return false;
    }
}
