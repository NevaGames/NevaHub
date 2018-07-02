/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.scoreboard;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.tools.items.NumberUtils;
import net.nevagames.tools.players.RankUnit;
import net.nevagames.tools.scoreboard.ObjectiveSign;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class PersonalScoreboard{
    private final Hub hub;
    private final UUID player;
    private final ObjectiveSign objectiveSign;

    private String formattedRank;
    private long coins;
    private long star;
    private int playercount;
    private SimpleDateFormat dateFormat;

    PersonalScoreboard(Hub hub, Player player){
        this.hub = hub;
        this.player = player.getUniqueId();

        this.objectiveSign = new ObjectiveSign ("sidebar", "NevaGames");
        this.dateFormat = new SimpleDateFormat ("dd/MM/yyyy", Locale.FRANCE);

        this.reloadData();
        this.objectiveSign.addReceiver(player);
    }

    public void onLogout()
    {
        this.objectiveSign.removeReceiver(this.hub.getServer().getOfflinePlayer(this.player));
    }

    public void reloadData(){

        Player username = Bukkit.getPlayer (player);

        this.formattedRank = APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (username).getPrefix ();
        this.coins = APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getCoinsBean (username);
        this.star = APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getStarBean (username);
        this.playercount = Bukkit.getOnlinePlayers ().size ();
    }

    public void setLines(String ip){
        Player username = Bukkit.getPlayer (player);

        this.objectiveSign.setDisplayName(ChatColor.GOLD + "\u2726" + ChatColor.RED + ChatColor.BOLD + " NevaGames " + ChatColor.RESET + ChatColor.GOLD + "\u2726");

        this.objectiveSign.setLine(0, ChatColor.BLUE + "");

        this.objectiveSign.setLine(1, ChatColor.GRAY + "Hub : " + ChatColor.WHITE + "§6#1");

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (username) == RankUnit.JOUEUR){
            this.objectiveSign.setLine(2, ChatColor.GRAY + "Grade : " + "§7Joueur");
        }else{
            this.objectiveSign.setLine(2, ChatColor.GRAY + "Grade : " + this.formattedRank);
        }

        this.objectiveSign.setLine(3, ChatColor.GRAY + "Connecté : §6" + playercount + "/" + Bukkit.getMaxPlayers ());
        this.objectiveSign.setLine(4, ChatColor.GREEN + "");

        this.objectiveSign.setLine(5, ChatColor.GRAY + "Pièces : " + ChatColor.GOLD + NumberUtils.format(this.coins) + " \u26C1");
        this.objectiveSign.setLine(6, ChatColor.GRAY + "Poussières d'" + ChatColor.AQUA + "\u272F" + ChatColor.GRAY + " : " + ChatColor.AQUA + NumberUtils.format(this.star));
        this.objectiveSign.setLine(7, ChatColor.GRAY + "Date : " +  ChatColor.GOLD + this.dateFormat.format(new Date ()));

        this.objectiveSign.setLine(8, ChatColor.RED + "");
        this.objectiveSign.setLine(9, ChatColor.YELLOW + ip);
        this.objectiveSign.updateLines();
    }
}
