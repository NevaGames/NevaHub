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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class ScoreboardManager{
    private final Map<UUID, PersonalScoreboard> scoreboards;
    private final ScheduledFuture glowingTask;
    private final ScheduledFuture reloadingTask;
    private int ipCharIndex;
    private int cooldown;

    public ScoreboardManager(){

        this.scoreboards = new HashMap<>();
        this.ipCharIndex = 0;
        this.cooldown = 0;

        this.glowingTask = Hub.getHubInstance ().getScheduledExecutorService().scheduleAtFixedRate(() ->{
            String ip = this.colorIpAt();

            for (PersonalScoreboard scoreboard : this.scoreboards.values())
                Hub.getHubInstance ().getExecutorMonoThread().execute(() -> scoreboard.setLines(ip));
        }, 60, 80, TimeUnit.MILLISECONDS);

        this.reloadingTask = Hub.getHubInstance ().getScheduledExecutorService().scheduleAtFixedRate(() -> {
            for (PersonalScoreboard scoreboard : this.scoreboards.values())
                Hub.getHubInstance ().getExecutorMonoThread().execute(scoreboard::reloadData);
        }, 30, 30, TimeUnit.SECONDS);
    }


    public void onDisable(){
        this.glowingTask.cancel(true);
        this.reloadingTask.cancel(true);

        this.scoreboards.values().forEach(PersonalScoreboard::onLogout);
    }


    public void onLogin(Player player){
        if (this.scoreboards.containsKey(player.getUniqueId())){
            APIPlugin.log(Level.WARNING, "The players '" + player.getUniqueId().toString() + "' already have a scoreboard!");
            return;
        }

        this.scoreboards.put(player.getUniqueId(), new PersonalScoreboard (Hub.getHubInstance (), player));
        APIPlugin.log(Level.INFO, "Added scoreboard to '" + player.getUniqueId() + "'.");
    }


    public void onLogout(Player player){
        if (this.scoreboards.containsKey(player.getUniqueId())){
            this.scoreboards.get(player.getUniqueId()).onLogout();
            this.scoreboards.remove(player.getUniqueId());

            APIPlugin.log(Level.INFO, "Removed scoreboard to '" + player.getUniqueId() + "'.");
        }
    }

    public void update(Player player){
        if (this.scoreboards.containsKey(player.getUniqueId()))
            this.scoreboards.get(player.getUniqueId()).reloadData();
    }

    private String colorIpAt(){
        String ip = "mc.nevagames.net";

        if (this.cooldown > 0){
            this.cooldown--;
            return ip;
        }

        StringBuilder formattedIp = new StringBuilder();

        if (this.ipCharIndex > 0){
            formattedIp.append(ip.substring(0, this.ipCharIndex - 1));
            formattedIp.append(ChatColor.GOLD).append(ip.substring(this.ipCharIndex - 1, this.ipCharIndex));
        }else{
            formattedIp.append(ip.substring(0, this.ipCharIndex));
        }

        formattedIp.append(ChatColor.RED).append(ip.charAt(this.ipCharIndex));

        if (this.ipCharIndex + 1 < ip.length()){
            formattedIp.append(ChatColor.GOLD).append(ip.charAt(this.ipCharIndex + 1));

            if (this.ipCharIndex + 2 < ip.length())
                formattedIp.append(ChatColor.YELLOW).append(ip.substring(this.ipCharIndex + 2));

            this.ipCharIndex++;
        }else        {
            this.ipCharIndex = 0;
            this.cooldown = 50;
        }

        return formattedIp.toString();
    }
}
