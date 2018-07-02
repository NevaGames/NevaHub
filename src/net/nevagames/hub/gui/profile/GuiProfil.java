/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.profile;

import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.Hub;
import net.nevagames.hub.gui.profile.settings.GuiSettings;
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiProfil extends AbstractGui {

    @Override
    public String name() {
        return "Profil";
    }

    @Override
    public void contents(Player player, Inventory inventory) {

        inventory.setItem (13, new ItemBuilder (createPlayerHead(player)).toItemStack ());
        inventory.setItem (24, new ItemBuilder (Material.DIODE).setName (ChatColor.GOLD + "Paramètres").setLore (ChatColor.GRAY + "Vos préférences sur le serveur" ).toItemStack ());
        inventory.setItem (20, new ItemBuilder (Material.ENCHANTED_BOOK).setName (ChatColor.GOLD + "Statistiques").setLore (ChatColor.GRAY + "Retrouvez vos scores et classements !").toItemStack ());
        inventory.setItem (22, new ItemBuilder (Material.WOOD_BUTTON).setName (ChatColor.GOLD + "Paramètres du ClickMe").setLore (ChatColor.GRAY + "Vos préférences ClickMe sur le serveur" ).toItemStack ());
        inventory.setItem (40,new ItemBuilder (getBackIcon()).toItemStack ());
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        switch (current.getType ()){
            case DIODE:
                if(inventory.getName ().equalsIgnoreCase ("Profil")){
                    Hub.getHubInstance ().open (player, GuiSettings.class);
                }
                break;

            case EMERALD:
                if(inventory.getName ().equalsIgnoreCase ("Profil")){
                    player.closeInventory ();
                }
                break;
            case ENCHANTED_BOOK:
                if(inventory.getName ().equalsIgnoreCase ("Profil")){
                    player.sendMessage( ChatColor.RED + "Procahinement...");
                }
                break;

            case WOOD_BUTTON:
                if(inventory.getName ().equalsIgnoreCase ("Profil")){
                    Hub.getHubInstance ().open (player, GuiClickMeSettings.class);
                }
                break;
        }
    }

    @Override
    public int getSize() {
        return 45;
    }
}
