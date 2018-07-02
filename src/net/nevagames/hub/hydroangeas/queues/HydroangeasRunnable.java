/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 7/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.hydroangeas.queues;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HydroangeasRunnable extends BukkitRunnable {

    private Sign sign;

    HydroangeasRunnable(Sign sign) {
        this.sign = sign;
        this.run();
    }

    @Override
    public void run() {
        for(final HydroangeasQueues queues : Hub.getHubInstance().getQueues().values()){
            if (queues.getPlayers().size() >=2){

                new BukkitRunnable() {
                    int current = 0;

                    @Override
                    public void run() {
                        if(current == queues.getPlayers().size()){
                            cancel();
                            Hub.getHubInstance().getQueues().remove(queues);
                        }else {
                            final Player p = queues.getPlayers().get(current);

                            if (p != null){
                                if (APIPlugin.getNevaGamesCore ().getConfig().getStringList("server-names").contains(sign.getLine(1))) {
                                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (p) == 0){
                                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 10.0F, 2.0F);
                                        p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                                        p.sendMessage( ChatColor.RED + "Votre serveur démarre § (Temps estimé 10 secondes)");
                                        p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                                        for (Player player : queues.getPlayers()){
                                            APIPlugin.getNevaGamesCore().getHydroangeas().changeServer(player, sign.getLine(1));
                                            sign.setLine (3,"0 en attente(s)");
                                            sign.update();
                                            }
                                        }
                                    }
                            }
                            queues.removePlayer(p);
                            sign.update();
                        }
                    }
                }.runTaskTimer(Hub.getHubInstance(),35, 0);
                sign.update();
            }else {
                Hub.getHubInstance().getQueues().remove(queues);
                sign.update();
            }
        }
    }

    public Sign getSign() {
        return sign;
    }
}
