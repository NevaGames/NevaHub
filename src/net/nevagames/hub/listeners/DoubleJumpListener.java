/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.listeners;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.tools.ParticleEffect;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoubleJumpListener implements Listener {
    private final List<UUID> allowed;

    public DoubleJumpListener(){
        this.allowed = new ArrayList<>();
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player p = event.getPlayer ();
       if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (p) == RankUnit.JOUEUR){


        if (Hub.getHubInstance ().parcours.containsKey (p))return;
            if(APIPlugin.getNevaGamesCore ().getModerator_mode ().contains (p))return;
            if (event.getPlayer().getGameMode() != GameMode.ADVENTURE)return;
            if (event.getPlayer().getAllowFlight()) return;

            if (((LivingEntity) event.getPlayer()).isOnGround()){
                event.getPlayer().setAllowFlight(true);
                allowed.add(event.getPlayer().getUniqueId());
            }
        }
    }


    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event){
        Player p = event.getPlayer ();

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (p) == RankUnit.JOUEUR){

        if(Hub.getHubInstance ().parcours.containsKey (p))return;
        if(!allowed.contains(event.getPlayer().getUniqueId()))return;
            if(APIPlugin.getNevaGamesCore ().getModerator_mode ().contains (p)){
            event.setCancelled (true);
            return;
        }

        allowed.remove(event.getPlayer().getUniqueId());

        event.setCancelled(true);

        event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.6D).setY(1.0D));
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1.0F, 1.0F);

        for (int i = 0; i < 20; i++)
            ParticleEffect.CLOUD.display(0.5F, 0.15F, 0.5F, 0.25F, 20, event.getPlayer().getLocation().subtract(0.0F, 0.20F, 0.0F));
            event.getPlayer().setAllowFlight(false);
        }
    }
}

