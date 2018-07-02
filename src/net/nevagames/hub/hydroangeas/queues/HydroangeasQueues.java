/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.hydroangeas.queues;

import net.nevagames.hub.Hub;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HydroangeasQueues {

    private String game;
    private ArrayList<Player> players = new ArrayList<>();
    private int minPlayer;

    HydroangeasQueues(String game){
        this.game = game;
        Hub.getHubInstance().getQueues().put(game, this);
        this.minPlayer = 2;
    }

    public static HydroangeasQueues getByName(String game){
        return Hub.getHubInstance().getQueues().get(game);
    }

    public static boolean existFor(String game){
        return Hub.getHubInstance().getQueues().containsKey(game);
    }

    public String getGame() {
        return game;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPosition(Player player){
        return players.indexOf(player) + 1;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public int getMinPlayer() {
        return minPlayer;
    }
}
