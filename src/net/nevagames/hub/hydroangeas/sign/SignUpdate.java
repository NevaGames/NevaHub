/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.hydroangeas.sign;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignUpdate implements Listener{


    @EventHandler
    public void onSignChange(SignChangeEvent e){

        //Uppervoid Jurrasic
        if(e.getLine (0).equalsIgnoreCase ("uppervoid j")){
            e.setLine(0, "UpperVoid");
            e.setLine(1, "Jurrasic");
            e.setLine(2, "§2»Jouez«");
            e.setLine(3, "0 en attente(s)");
        }
        // Uppervoid Ice Pick
        if(e.getLine (0).equalsIgnoreCase ("uppervoid i")){
            e.setLine(0, "UpperVoid");
            e.setLine(1, "IcePick");
            e.setLine(2, "§2»Jouez«");
            e.setLine(3, "0 en attente(s)");
        }

        // Ktp2017
        if (e.getLine(0).equalsIgnoreCase("ktp2017")){
            e.setLine(0, "Arcade");
            e.setLine(1, "Ktp2017");
            e.setLine(2, "§2»Jouez«");
            e.setLine(3, "0 en attente(s)");
        }

        // TimberMan
        if (e.getLine(0).equalsIgnoreCase("timberman")){
            e.setLine(0, "Arcade");
            e.setLine(1, "Timberman");
            e.setLine(2, "§2»Jouez«");
            e.setLine(3, "0 en attente(s)");
        }

        // BowSpleef
        if (e.getLine(0).equalsIgnoreCase("bowspleef")){
            e.setLine(0, "BowSpleef");
            e.setLine(1, "Snow");
            e.setLine(2, "§2»Jouez«");
            e.setLine(3, "0 en attente(s)");
        }
    }
}
