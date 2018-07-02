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
import net.nevagames.hub.common.task.AdvertisingTask;
import net.nevagames.tools.chat.Titles;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Level;

public class PlayerConnection implements Listener {

    private Hub hub;
    public PlayerConnection(Hub hub){
        this.hub = hub;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer ();

        try {
            this.hub.getTaskManager ().getAdvertisingTask ().addPlayer (player);
            this.hub.getPlayerManagers ().onPlayerLogin (player);
            player.setPlayerListName(APIPlugin.getNevaGamesCore().getBeanManager().getPlayerBean().getRankUnit(player).getPrefix ()  + player.getName());

            player.setCustomName(APIPlugin.getNevaGamesCore().getBeanManager().getPlayerBean().getRankUnit(player).getPrefix() + player.getName());
            player.setCustomNameVisible(true);

            e.setJoinMessage (null);

            Titles.sendTitle(player, 25, 100, 25,  "§6\u2726"+"§cNevaGames"+"§6\u2726",  "§eAmusement et détente §c!");
            player.teleport(new Location (player.getWorld(), -11.466, 100, -1.592, 1.4f, 5.9f));

            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            player.sendMessage("§6Bienvenue sur §cNevaGames §6!");
            player.sendMessage("§4");
            player.sendMessage("§cServeur en bêta merci de votre compréhension.");
            player.sendMessage("§3");
            player.sendMessage("§cLe temps de rechargement entre chaque coup ajouté par la 1.9 est retiré des jeux PvP de NevaGames et ce dernier restera le même qu'en 1.8.");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        }catch (Exception exception){
            player.sendMessage(ChatColor.RED + "Une erreur a été détecté lors du chargement de votre joueur, vous devrez peut-être vous reconnecter.");
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        try {
            this.hub.getTaskManager ().getAdvertisingTask ().removePlayer (player);
            this.hub.getPlayerManagers ().onPlayerLogout (player);
            e.setQuitMessage(null);
        }catch (Exception exeption){
            APIPlugin.getNevaGamesCore().getLogger().log(Level.INFO, "Une erreur a été détectée lors de la sauvegarde du profil de " + player.getName() + ". Ses statistique n'ont peux être pas ete enregistrées !");
            exeption.printStackTrace();
        }
    }
}
