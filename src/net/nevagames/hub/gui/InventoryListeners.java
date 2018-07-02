/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui;

import net.nevagames.hub.Hub;
import net.nevagames.hub.gui.cosmetics.GuiCometicsCategory;
import net.nevagames.hub.gui.main.GuiMain;
import net.nevagames.hub.gui.profile.GuiProfil;
import net.nevagames.hub.gui.shop.GuiShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        ItemStack it = event.getItem();

        if(it != null && it.getType() == Material.COMPASS){
            Hub.getHubInstance ().open(player, GuiMain.class);
        }

        if(it != null && it.getType () == Material.GOLD_INGOT){
            Hub.getHubInstance ().open (player, GuiShop.class);
        }

        if(it != null && it.getType () == Material.ENDER_CHEST){
            Hub.getHubInstance ().open (player, GuiCometicsCategory.class);
        }
        if(it != null && it.getType () == Material.SKULL_ITEM){
            Hub.getHubInstance ().open (player, GuiProfil.class);
        }
        event.setCancelled (true);    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(event.getCurrentItem() == null) return;
        Hub.getHubInstance ( ).registeredMenus.values().stream().filter(menu -> inv.getName().equalsIgnoreCase(menu.name())).forEach(menu -> menu.onClick(player, inv, current));
        event.setCancelled (true);
    }
}
