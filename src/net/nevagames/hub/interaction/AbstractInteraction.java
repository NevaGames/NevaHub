/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.interaction;

import net.nevagames.hub.Hub;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class AbstractInteraction implements Listener{
    protected final Hub hub;

    public AbstractInteraction(Hub hub)
    {
        this.hub = hub;
    }

    public abstract void onDisable();

    public abstract void play(Player player);
    public abstract void stop(Player player);

    public abstract boolean hasPlayer(Player player);
}
