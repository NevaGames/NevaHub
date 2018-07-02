/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.commands.admin;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.hub.commands.AbstractCommand;
import net.nevagames.tools.chat.Titles;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEvacuate extends AbstractCommand {
    private boolean lock;
    private int timer;

    public CommandEvacuate(Hub hub){
        super(hub);

        this.lock = false;
        this.timer = 60;
    }
    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] arguments) {

        Player player = (Player) sender;

            if (APIPlugin.getNevaGamesCore().getBeanManager().getPlayerBean().getRankUnit(player) != RankUnit.ADMIN){
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
                return true;
            }

        if (this.lock)
            return true;

        this.lock = true;

        if (arguments.length != 1){
            sender.sendMessage(ChatColor.RED + "Usage: /evacuate <serveur de destination>");
            return true;
        }

        this.hub.getServer().getScheduler().runTaskTimerAsynchronously(this.hub, () ->{
            if (this.timer == 60 || this.timer == 30 || this.timer == 10 || (this.timer <= 5 && this.timer > 0))
                this.hub.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Votre hub va redémarrer dans " + ChatColor.AQUA + ChatColor.BOLD + this.timer + " seconde" + (this.timer > 1 ? "s" : ""));

            this.hub.getServer().getOnlinePlayers().stream().filter(p -> this.timer > 0).forEach(p ->{
                if (this.timer <= 5)
                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 0.8F, 1.8F);
                else if (this.timer > 5 && this.timer <= 30)
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 0.8F, 1.0F);

                Titles.sendTitle(p, 0, 22, 0, ChatColor.RED + "Attention !", ChatColor.GOLD + "Votre hub va redémarrer dans " + ChatColor.AQUA + this.timer + " seconde" + (this.timer > 1 ? "s" : ""));
            });

            if (this.timer == 0){
                this.hub.getServer().broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Fermeture du hub...");

                for (Player p : this.hub.getServer().getOnlinePlayers())
                    p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.9F, 1.0F);
            }else if (this.timer == -1){
                for (Player p : this.hub.getServer().getOnlinePlayers()){
                    APIPlugin.getNevaGamesCore().getHydroangeas().connect(p, arguments[0]);
                }

                this.hub.getServer().getScheduler().runTaskLaterAsynchronously(this.hub, () -> this.hub.getServer().shutdown(), 3 * 20L);
            }

            this.timer--;
        }, 20L, 20L);

        return true;
    }
}
