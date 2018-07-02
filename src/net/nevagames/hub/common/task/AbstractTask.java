/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.task;


import net.nevagames.hub.Hub;
import org.bukkit.scheduler.BukkitTask;

public abstract class AbstractTask implements Runnable{

    public final Hub hub;
    BukkitTask task;

    AbstractTask(Hub hub)
    {
        this.hub = hub;
    }

    void cancel(){
        this.task.cancel();
    }
}
