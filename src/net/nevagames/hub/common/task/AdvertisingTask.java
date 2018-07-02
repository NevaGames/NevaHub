/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.task;

import net.nevagames.hub.Hub;
import net.nevagames.tools.bossbar.BossBarAPI;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AdvertisingTask extends BukkitRunnable{

    private static final List<String> LINES;
    private final BossBar bossBar;
    private int i;
    private Hub hub;

    public AdvertisingTask(Hub hub){
        this.hub = hub;
        this.bossBar = BossBarAPI.getBar(ChatColor.YELLOW + "NevaGames", BarColor.RED, BarStyle.SOLID, 0.0D).getValue();
        this.bossBar.setProgress(0.0D);

        this.i = 0;

        this.hub.getServer().getScheduler().runTaskTimer(hub, this, 20L * 5, 20L * 5);
    }

    @Override
    public void run(){
        this.bossBar.setTitle(LINES.get(this.i));

        this.i++;

        if (this.i == LINES.size())
            this.i = 0;
    }

    /**
     * Add player in boss bar aerialist.
     * @param player - The player who view the boss bar.
     */

    public void addPlayer(Player player){
        this.bossBar.addPlayer(player);
    }

    /**
     * Remove player in boss bar aerialist.
     * @param player - The player who view the boss bar.
     */

    public void removePlayer(Player player){
        this.bossBar.removePlayer(player);
    }

    static {
        LINES = new ArrayList<>();
        LINES.add(ChatColor.YELLOW + "Toutes les informations sur " + ChatColor.RED + "www.nevagames.net" + ChatColor.YELLOW + " !");
        LINES.add(ChatColor.YELLOW + "NevaGames est sur Twitter : " + ChatColor.AQUA + "@NevaGames_MC" + ChatColor.YELLOW + " !");
        LINES.add(ChatColor.YELLOW + "NevaGames est sur YouTube : " + ChatColor.RED + "@NevaGames" + ChatColor.YELLOW + " !");
        LINES.add(ChatColor.YELLOW + "Venez discuter sur TeamSpeak : " + ChatColor.GREEN + "ts.nevagames.net" + ChatColor.YELLOW + " !");
    }
}
