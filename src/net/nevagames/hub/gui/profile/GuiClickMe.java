/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.profile;

import net.nevagames.api.APIPlugin;
import net.nevagames.tools.chat.fanciful.FancyMessage;
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiClickMe implements Listener {

    @EventHandler
    public void onClickMe(PlayerInteractAtEntityEvent e){
        Player player = e.getPlayer ();
        Player target = (Player) e.getRightClicked ();
        Inventory inventory = Bukkit.createInventory (null, 54, "Click Me ! "+ target.getName ());
        e.setCancelled (true);

        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (target) == 1){
            player.sendMessage (ChatColor.RED + "Ce joueur n'accepte pas les intéractions !");
            return;
        }

        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getSneak (target) == 1){
            if (player.isSneaking ()){
                if(e.getRightClicked () instanceof Player){
                    if(e.getRightClicked ().getType () == EntityType.PLAYER){

                        inventory.setItem (13, new ItemBuilder (getPlayerHead (target)).setName (ChatColor.GOLD + target.getName ()).setLore (ChatColor.GRAY + "Rang: " + APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (target).getPrefix ()).toItemStack ());

                        inventory.setItem (20, new ItemBuilder (Material.GOLD_INGOT).setName (getCoinsItemTitle (target)).toItemStack ());
                        inventory.setItem (21, new ItemBuilder (Material.RAW_FISH, 1, (short)3).setName (ChatColor.GREEN + "Envoyer une demande d'ami").toItemStack ());
                        inventory.setItem (22, new ItemBuilder (Material.PAPER).setName (ChatColor.GREEN + "Envoyer un message privé").toItemStack ());
                        inventory.setItem (23, new ItemBuilder (Material.LEASH).setName (ChatColor.GREEN + "Envoyer une demande de groupe").toItemStack ());
                        inventory.setItem (24, new ItemBuilder (Material.NETHER_STAR).setName (getStarItemTitle (target)).toItemStack ());
                        inventory.setItem (31, new ItemBuilder (Material.ENCHANTED_BOOK).setName (ChatColor.GREEN + "Statistiques").toItemStack ());
                        inventory.setItem (49, new ItemBuilder (getBackIcon ()).toItemStack ());
                        player.openInventory (inventory);
                    }
                }
                e.setCancelled (true);
            }
        }

        if(e.getRightClicked () instanceof Player){
            if(e.getRightClicked ().getType () == EntityType.PLAYER){

                inventory.setItem (13, new ItemBuilder (getPlayerHead (target)).setName (ChatColor.GOLD + target.getName ()).setLore (ChatColor.GRAY + "Rang: " + APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (target).getPrefix ()).toItemStack ());

                inventory.setItem (20, new ItemBuilder (Material.GOLD_INGOT).setName (getCoinsItemTitle (target)).toItemStack ());
                inventory.setItem (21, new ItemBuilder (Material.RAW_FISH, 1, (short)3).setName (ChatColor.GREEN + "Envoyer une demande d'ami").toItemStack ());
                inventory.setItem (22, new ItemBuilder (Material.PAPER).setName (ChatColor.GREEN + "Envoyer un message privé").toItemStack ());
                inventory.setItem (23, new ItemBuilder (Material.LEASH).setName (ChatColor.GREEN + "Envoyer une demande de groupe").toItemStack ());
                inventory.setItem (24, new ItemBuilder (Material.NETHER_STAR).setName (getStarItemTitle (target)).toItemStack ());
                inventory.setItem (31, new ItemBuilder (Material.ENCHANTED_BOOK).setName (ChatColor.GREEN + "Statistiques").toItemStack ());
                inventory.setItem (49, new ItemBuilder (getBackIcon ()).toItemStack ());
                player.openInventory (inventory);
            }
        }
        e.setCancelled (true);
    }

    @EventHandler
    public void onClickMeInteract(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked ();
        ItemStack it = e.getCurrentItem();
        if (it.getType() == null || it.getType() ==  Material.AIR)return;
        e.setCancelled (true);

        if(e.getInventory().getName().contains("Click Me")){
            switch(e.getCurrentItem().getType()){

                case RAW_FISH:
                    if(e.getCurrentItem ().getItemMeta().getDisplayName().equals (ChatColor.GREEN + "Envoyer une demande d'ami")){
                        new FancyMessage (ChatColor.YELLOW + "Cliquez sur ").then("[Inviter]").color(ChatColor.GREEN).suggest("/friend add").then(" pour inviter ce joueur en ami.").color(ChatColor.YELLOW).send(player);
                        player.closeInventory ();
                    }
                    break;

                case PAPER:
                    if(e.getCurrentItem ().getItemMeta().getDisplayName().equals (ChatColor.GREEN + "Envoyer un message privé")){
                        new FancyMessage(ChatColor.YELLOW + "Cliquez sur ").then("[Envoyer]").color(ChatColor.GREEN).suggest("/msg").then(" pour envoyer un message privé.").color(ChatColor.YELLOW).send(player);
                    }
                    break;

                case LEASH:
                    if(e.getCurrentItem ().getItemMeta().getDisplayName().equals (ChatColor.GREEN + "Envoyer une demande de groupe")){
                        new FancyMessage(ChatColor.YELLOW + "Cliquez sur ").then("[Inviter]").color(ChatColor.GREEN).suggest("/party invite").then(" pour inviter ce joueur dans votre partie.").color(ChatColor.YELLOW).send(player);
                    }
                    break;

                case ENCHANTED_BOOK:
                    if(e.getCurrentItem ().getItemMeta().getDisplayName().equals (ChatColor.GREEN + "Statistiques")){
                        player.sendMessage(ChatColor.RED + "Prochainement...");
                    }
                    break;

                case EMERALD:
                    if(e.getCurrentItem ().getItemMeta ().getDisplayName ().equals (ChatColor.GREEN + "« Retour")){
                        player.closeInventory ();
                    }
                    break;

                    default:break;
            }
        }
        e.setCancelled (true);
    }

    private static ItemStack getBackIcon(){
        ItemStack stack = new ItemStack(Material.EMERALD, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "« Retour");
        stack.setItemMeta(meta);

        return stack;
    }

    private ItemStack getPlayerHead(Player player){
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(player.getName ());
        head.setItemMeta(meta);

        return head;
    }

    private String getCoinsItemTitle(Player player){
        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewCoins (player) == 0)
            return ChatColor.GOLD + String.valueOf(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getCoinsBean (player)) + " coins";
        else
            return ChatColor.RED + "Cette information est privée !";
    }

    private String getStarItemTitle(Player player){
        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewStar (player) == 0)
            return ChatColor.AQUA + String.valueOf(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getStarBean (player) + " poussières d'\u272F");
        else
            return ChatColor.RED + "Cette information est privée !";
    }
}
