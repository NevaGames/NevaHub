/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class EventBus implements EntryPoints{
    private List<AbstractManager> managers;

    public EventBus()
    {
        this.managers = new ArrayList<>();
    }

    void registerManager(AbstractManager manager){
        this.managers.add(manager);
    }

    @Override
    public void onDisable()
    {
        this.managers.forEach(AbstractManager::onDisable);
    }

    @Override
    public void onLogin(Player player)
    {
        this.managers.forEach(manager -> manager.onLogin(player));
    }

    @Override
    public void onLogout(Player player)
    {
        this.managers.forEach(manager -> manager.onLogout(player));
    }
}
