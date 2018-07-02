/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.players;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.hub.common.manager.AbstractManager;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

public class ChatManager extends AbstractManager {
    private final Map<UUID, Date> lastPlayerMessages;
    private final List<UUID> chatDisablers;

    private int actualSlowDuration;
    private boolean chatLocked;

    public ChatManager(Hub hub){
        super(hub);

        this.lastPlayerMessages = new HashMap<>();
        this.chatDisablers = new ArrayList<>();

        this.actualSlowDuration = 0;
        this.chatLocked = false;
    }

    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        if (event.isCancelled())
            return;

            final Player player = event.getPlayer ();

        if (this.chatLocked){
            if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO){
                event.getPlayer().sendMessage(PlayerManagers.MODERATING_TAG + ChatColor.YELLOW + "Attention, le chat a été désactivé par un moderating.");
            }else{
                event.setCancelled(true);
                event.getPlayer().sendMessage(PlayerManagers.MODERATING_TAG + ChatColor.RED + "Le chat a été désactivé par un moderating.");
            }
        }else if (this.actualSlowDuration > 0 && APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.MODO)
            if (!this.hasPlayerTalked(event.getPlayer())){
                this.actualizePlayerLastMessage(event.getPlayer());
            } else {
                Date lastMessage = this.getLastPlayerMessageDate(event.getPlayer());
                Date actualMessage = new Date(lastMessage.getTime() + this.getActualSlowDuration() * 1000);
                Date current = new Date();

                if (actualMessage.after(current)){
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "Le chat est actuellement ralenti.");

                    double whenNext = Math.floor((actualMessage.getTime() - current.getTime()) / 1000);
                    event.getPlayer().sendMessage(ChatColor.GOLD + "Prochain message autorisé dans : " + (int) whenNext + " secondes");
                }else{
                    this.actualizePlayerLastMessage(event.getPlayer());
                }
            }
        }

    @Override
    public void onDisable(){
        this.lastPlayerMessages.clear();
        this.chatDisablers.clear();
    }

    @Override
    public void onLogin(Player player){

        /**
         * TODO verifier si le joueur a activé/désactivé le chat.
         */
    }

    @Override
    public void onLogout(Player player){
        if (this.hasChatDisabled(player))
            this.enableChatFor(player);
    }

    private void enableChatFor(Player player){
        this.chatDisablers.remove(player.getUniqueId());
    }

    public void disableChatFor(Player player){
        if (!this.chatDisablers.contains(player.getUniqueId()))
            this.chatDisablers.add(player.getUniqueId());
    }

    public void setChatLocked(boolean flag){
        this.chatLocked = flag;
    }

    public void setActualSlowDuration(int time){
        this.actualSlowDuration = time;
    }

    public int getActualSlowDuration(){
        return this.actualSlowDuration;
    }

    public boolean isChatLocked(){
        return this.chatLocked;
    }

    private void actualizePlayerLastMessage(Player player){
        this.lastPlayerMessages.put(player.getUniqueId(), new Date());
    }

    private Date getLastPlayerMessageDate(Player player){
        return this.lastPlayerMessages.getOrDefault(player.getUniqueId(), null);
    }

    private boolean hasPlayerTalked(Player player){
        return this.lastPlayerMessages.containsKey(player.getUniqueId());
    }

    private boolean hasChatDisabled(Player player){
        return this.chatDisablers.contains(player.getUniqueId());
    }
}
