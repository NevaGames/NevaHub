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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BackEndGame extends AbstractGame {

    public BackEndGame(Hub hub){
        super(hub);
    }

    @Override
    public String getCodeName(){
        return "arcade";
    }

    @Override
    public String getName(){
        return "§6Arcade";
    }

    @Override
    public String getCategory(){
        return "Seul contre tous";
    }

    @Override
    public ItemStack getIcon(){
        return new ItemStack(Material.SLIME_BLOCK, 1);
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
    public String getWebsiteDescriptionURL(){
        return null;
    }

    @Override
    public int getSlotInMainMenu(){
        return 20;
    }


    @Override
    public Location getLobbySpawn(){
        return new Location(Bukkit.getWorlds().get(0), -80.437,102.06250,-1.678,95.5f,1.5f);
    }

    @Override
    public Location getWebsiteDescriptionSkull(){
        return null;
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
    public boolean isGroup(){
        return false;
    }
}
