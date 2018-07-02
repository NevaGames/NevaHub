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
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class QueueListeners implements Listener{

    @EventHandler
    public void onPlayerInteractQueue(PlayerInteractEvent e){
        final Player player = e.getPlayer();
        Block block = e.getClickedBlock();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(block.getTypeId() == 68 || block.getType() == Material.SIGN){

                Sign sign = (Sign)block.getState();

                    if (APIPlugin.getNevaGamesCore ().getConfig().getStringList("server-names").contains(sign.getLine(1))){

                        if (!HydroangeasQueues.existFor(sign.getLine(1))){
                            HydroangeasQueues hydroangeasQueues = new HydroangeasQueues(sign.getLine(1));
                        }

                        HydroangeasQueues hydroangeasQueues = HydroangeasQueues.getByName(sign.getLine(1));

                        if(hydroangeasQueues.getPlayers().contains(player)){
                            hydroangeasQueues.removePlayer(player);
                            sign.update();

                            sign.setLine (3, hydroangeasQueues.getPlayers ().size () + " en attente(s)");
                            sign.update ();

                            //player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                            //player.sendMessage("§cRetiré de la file d'attente §csur la map §6" + sign.getLine(1) + "§c!");
                            //player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                            if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (player) == 0){
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 0.8F);
                                APIPlugin.getNevaGamesCore ().getActionBarAPI ().sendActionBar (player, ChatColor.RED + "Retiré de la file d'attente sur la map: §6" + sign.getLine (1)+ "§c!");
                            }
                            sign.update();

                            if (hydroangeasQueues.getPlayers().size() == 0){
                                Hub.getHubInstance().getQueues().remove(hydroangeasQueues);
                                sign.update();
                            }
                            return;
                        }

                        hydroangeasQueues.addPlayer(player);

                        sign.setLine (3, hydroangeasQueues.getPlayers ().size () + " en attente(s)");
                        sign.update ();

                        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (player) == 0){
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.5F);
                            APIPlugin.getNevaGamesCore ().getActionBarAPI ().sendActionBar (player, "§6"+ sign.getLine (1) + ": §ePlace dans la file: §b" + hydroangeasQueues.getPosition (player)+ " §eJoueur minimun:§c " + hydroangeasQueues.getMinPlayer ());

                        }
                        //player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                        //player.sendMessage("§aAjouté à la file d'attente, §asur la map "+ sign.getLine(1) +" §a!");
                        //player.sendMessage("§eVous êtes actuellement à la place §c"+ hydroangeasQueues.getPosition(player) + " §edans la file." );
                        //player.sendMessage("§eIl faut au moins §c" + hydroangeasQueues.getMinPlayer() + " joueurs §epour que le serveur se lance !");
                        //player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                        sign.update();

                        new HydroangeasRunnable(sign);
                        }
                    }
                }
            }
        }


