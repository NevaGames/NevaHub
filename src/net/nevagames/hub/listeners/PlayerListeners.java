/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.listeners;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListeners implements Listener{

    private Hub hub;

    public PlayerListeners (Hub hub){
        this.hub = hub;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        this.hub.getChatManager().onAsyncPlayerChat(event);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event){
        if (!event.getWhoClicked().isOp())
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null)
            return;

        this.hub.getServer().getScheduler().runTaskAsynchronously(this.hub, () -> this.hub.getPlayerManagers().getStaticInventory().doInteraction(player, item));
    }

    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer ();

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.JOUEUR)
            this.hub.getServer().getScheduler().runTask(this.hub, () -> event.getPlayer().setAllowFlight(true));
    }
    @EventHandler
    private void onPlayerGlide(EntityToggleGlideEvent event){
        if (!(event.getEntity() instanceof Player))
            return;

        if (event.isGliding()){
            ItemStack stack = new ItemStack(Material.FEATHER);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ventilateur" + ChatColor.RESET + "" + ChatColor.GRAY + " (Clic-droit)");
            stack.setItemMeta(meta);
            stack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            ((Player) event.getEntity()).getInventory().setItem(3, stack);
        }else{
            ((Player) event.getEntity()).getInventory().setItem(3, new ItemStack(Material.AIR));
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        this.onPlayerGlide(new EntityToggleGlideEvent(event.getPlayer(), false));
    }

    private boolean checkElytra(Player player){

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.VIPPLUS){
            player.getInventory().setChestplate(null);
            this.hub.getPlayerManagers().getStaticInventory().setInventoryToPlayer (player);
            return false;
        }
        return true;
    }
}



