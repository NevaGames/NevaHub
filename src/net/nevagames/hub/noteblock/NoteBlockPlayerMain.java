/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.noteblock;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class NoteBlockPlayerMain
{
    public static HashMap<String, List<SongPlayer>> playingSongs = new HashMap<>();
    public static HashMap<String, Byte> playerVolume = new HashMap<>();

    public static boolean isReceivingSong(Player p) {
        return ((playingSongs.get(p.getName()) != null) && (!playingSongs.get(p.getName()).isEmpty()));
    }

    public static void stopPlaying(Player p) {
        if (playingSongs.get(p.getName()) == null) {
            return;
        }
        for (SongPlayer s : playingSongs.get(p.getName())) {
            s.removePlayer(p);
        }
    }

    public static void setPlayerVolume(Player p, byte volume)
    {
        playerVolume.put(p.getName(), volume);
    }

    public static byte getPlayerVolume(Player p)
    {
        Byte b = playerVolume.get(p.getName());

        if (b == null)
        {
            b = 100;
            playerVolume.put(p.getName(), b);
        }

        return b;
    }
}
