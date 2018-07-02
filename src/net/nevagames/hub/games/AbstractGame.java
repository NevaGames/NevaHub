/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 31/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.games;

import net.nevagames.hub.Hub;
import net.nevagames.tools.chat.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractGame{
    public enum State { OPENED, NEW, POPULAR, SOON, LOCKED }

    protected final Hub hub;

    public AbstractGame(Hub hub){
        this.hub = hub;
    }

    public abstract String getCodeName();
    public abstract String getName();
    public abstract String getCategory();
    public abstract ItemStack getIcon();
    public abstract String[] getDescription();
    public abstract String getDevelopers();
    public abstract String getWebsiteDescriptionURL();
    public abstract int getSlotInMainMenu();
    public abstract Location getLobbySpawn();
    public abstract Location getWebsiteDescriptionSkull();
    public abstract State getState();
    public abstract boolean hasResourcesPack();
    public abstract boolean isGroup();

    public void showRulesWarning(Player player){
        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        new FancyMessage ("Il s'agit de votre première partie sur ce jeu ! Nous vous conseillons d'aller d'abord lire les règles en").color(ChatColor.GREEN)
                .then("cliquant ici").color(ChatColor.GREEN).style(ChatColor.BOLD).link(this.getWebsiteDescriptionURL())
                .then(" pour accéder aux règles du jeu.").style(ChatColor.GREEN)
                .send(player);

        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }
}
