/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.listeners;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class DeveloppeurListeners implements Listener{

    private Hub hub;

    public DeveloppeurListeners(Hub hub){
        this.hub = hub;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer ();
        Block block = e.getClickedBlock();
        //Sign sign = (Sign)block.getState();

        this.hub.getServer ().getScheduler ().runTaskAsynchronously (this.hub, () -> {
            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MINI_DEV){

                if(e.getItem () != null && e.getItem ().getType () == Material.WOOD_AXE){
                    Action action = e.getAction ();

                    if(action == Action.LEFT_CLICK_BLOCK){
                        this.hub.getPlayerManagers ().setSelection (player, e.getClickedBlock ().getLocation ());
                    }
                }
            }
        });
    }
}
