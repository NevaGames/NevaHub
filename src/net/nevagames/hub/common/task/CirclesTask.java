/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.task;

import net.nevagames.hub.Hub;
import net.nevagames.tools.ParticleEffect;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CirclesTask extends AbstractTask{

    private static final double RADIUS = 0.5D;

    private final List<Location> locations;
    private double i;

    /**
     * The main class of this plugin.
     * @param hub - The hub.
     */

    CirclesTask(Hub hub){
        super(hub);
        this.locations = new ArrayList<>();
        this.task = this.hub.getServer().getScheduler().runTaskTimerAsynchronously(hub, this, 1L, 1L);
    }

    @Override
    public void run(){
        for (Location location : this.locations)
            ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 1, new Location (location.getWorld(), location.getX() + Math.cos(this.i) * RADIUS, location.getY() + 0.15D, location.getZ() + Math.sin(this.i) * RADIUS), 32.0D);

        this.i += 0.25D;

        if (this.i > Math.PI * 2.0D)
            this.i = 0.0D;
    }

    /**
     * Add a location in the list.
     * @param location - The location.
     */

    public void addCircleAt(Location location){
        if (!this.locations.contains(location))
            this.locations.add(location);
    }

    /**
     * Remove a location in the list.
     * @param location - The location.
     */

    public void removeCircleAt(Location location){
        this.locations.remove(location);
    }
}
