/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.task;

import net.nevagames.hub.Hub;
import net.nevagames.hub.common.manager.AbstractManager;
import org.bukkit.entity.Player;

public class TaskManagers extends AbstractManager {

    private final CirclesTask circlesTask;
    private final AdvertisingTask advertisingTask;

    public TaskManagers(Hub hub) {
        super (hub);

        this.circlesTask = new CirclesTask (hub);
        this.advertisingTask = new AdvertisingTask (hub);
    }

    @Override
    public void onDisable() {
        this.circlesTask.cancel();
        this.advertisingTask.cancel();
    }

    /**
     *  Load the player task when the player join.
     * @param player - The player.
     */

    @Override
    public void onLogin(Player player) {
        this.advertisingTask.addPlayer(player);
    }

    /**
     * Logout the player task when the player leave.
     * @param player - The player.
     */

    @Override
    public void onLogout(Player player) {
        this.advertisingTask.removePlayer(player);
    }

    /**
     * Get the circles task.
     * @return circlesTask.
     */

    public CirclesTask getCirclesTask() {
        return circlesTask;
    }

    public AdvertisingTask getAdvertisingTask() {
        return advertisingTask;
    }
}
