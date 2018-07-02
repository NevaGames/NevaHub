/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.profile.settings;

import net.nevagames.api.APIPlugin;
import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.Hub;
import net.nevagames.hub.gui.profile.GuiProfil;
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class GuiSettings extends AbstractGui {

    @Override
    public String name() {
        return "Paramètres (Page 1)";
    }

    @Override
    public void contents(Player player, Inventory inventory) {

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewPlayer (player) == 0){
                inventory.setItem (10, new ItemBuilder (getSettingPlayer (player)).toItemStack ());
                inventory.setItem (19, new ItemStack (getAccept ().setName ("§6Joueurs: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (10, new ItemBuilder (getSettingPlayer (player)).toItemStack ());
                inventory.setItem (19, new ItemStack (getDeny ().setName ("§6Joueurs: " + ChatColor.RED + "Désactivé").toItemStack ()));
                Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getMsg (player) == 0){
                inventory.setItem (11, new ItemBuilder (getSettingMsg ()).toItemStack ());
                inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Messages privés: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (11, new ItemBuilder (getSettingMsg ()).toItemStack ());
                inventory.setItem (20, new ItemStack (getDeny().setName ("§6Messages privés: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewMention (player) == 0){
                inventory.setItem (12, new ItemBuilder (getSettingMention ()).toItemStack ());
                inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Notifications: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (12, new ItemBuilder (getSettingMention ()).toItemStack ());
                inventory.setItem (21, new ItemStack (getDeny ().setName ("§6Notifications: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getHeartJukeBox (player) == 0){
                inventory.setItem (13, new ItemBuilder (getSettingJukeBox ()).toItemStack ());
                inventory.setItem (22, new ItemStack (getAccept ().setName ("§6Jukebox: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (13, new ItemBuilder (getSettingJukeBox ()).toItemStack ());
                inventory.setItem (22, new ItemStack (getDeny ().setName ("§6Jukebox: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getInteraction (player) == 0){
                inventory.setItem (14, new ItemBuilder (getSettingInteraction ()).toItemStack ());
                inventory.setItem (23, new ItemStack (getAccept ().setName ("§6Intéractions: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (14, new ItemBuilder (getSettingInteraction ()).toItemStack ());
                inventory.setItem (23, new ItemStack (getDeny ().setName ("§6Intéractions: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (player) == 0){
                inventory.setItem (15, new ItemBuilder (getSettingFileAttentes ()).toItemStack ());
                inventory.setItem (24, new ItemStack (getAccept ().setName ("§6Notifications de file d'attente: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (15, new ItemBuilder (getSettingFileAttentes ()).toItemStack ());
                inventory.setItem (24, new ItemStack (getDeny ().setName ("§6Notifications de file d'attente: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getElytra (player) == 0){
                inventory.setItem (16, new ItemBuilder (getSettingElytra ()).toItemStack ());
                inventory.setItem (25, new ItemStack (getAccept ().setName ("§6Ailes: " + ChatColor.GREEN + "Activé").toItemStack ()));
            }else {
                inventory.setItem (16, new ItemBuilder (getSettingElytra ()).toItemStack ());
                inventory.setItem (25, new ItemStack (getDeny ().setName ("§6Ailes: " + ChatColor.RED + "Désactivé").toItemStack ()));
            }

            inventory.setItem (40, new ItemBuilder (getBackIcon ()).toItemStack ());
            inventory.setItem (44, new ItemBuilder (getNextPage ()).toItemStack ());

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        switch (current.getType ()){
            case STAINED_GLASS:

                /* View Player*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewPlayer (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Joueurs: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewPlayer (player, 1);
                        inventory.setItem (19, new ItemStack (getDeny ().setName ("§6Joueurs: " + ChatColor.RED + "Désactivé").toItemStack ()));
                        Bukkit.getOnlinePlayers().forEach(player::hidePlayer);
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewPlayer (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Joueurs: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewPlayer (player, 0);
                        inventory.setItem (19, new ItemStack (getAccept ().setName ("§6Joueurs: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        Bukkit.getOnlinePlayers().forEach(player::showPlayer);
                    }
                }

                    /* View Message*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getMsg (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Messages privés: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setMsgSetting (player, 1);
                            inventory.setItem (20, new ItemStack (getDeny().setName ("§6Messages privés: " + ChatColor.RED + "Désactivé").toItemStack ()));

                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getMsg (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Messages privés: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setMsgSetting (player, 0);
                            inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Messages privés: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        }
                    }

                    /* View Notifications*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewMention (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Notifications: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewMention (player, 1);
                            inventory.setItem (21, new ItemStack (getDeny().setName ("§6Notifications: " + ChatColor.RED + "Désactivé").toItemStack ()));
                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewMention (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Notifications: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewMention (player, 0);
                            inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Notifications: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        }
                    }

                    /* View Jukebox*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getHeartJukeBox (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Jukebox: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setHeartJukeBox (player, 1);
                            inventory.setItem (22, new ItemStack (getDeny().setName ("§6Jukebox: " + ChatColor.RED + "Désactivé").toItemStack ()));
                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getHeartJukeBox (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Jukebox: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setHeartJukeBox (player, 0);
                            inventory.setItem (22, new ItemStack (getAccept ().setName ("§6Jukebox: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        }
                    }

                    /* View Intéractions*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getInteraction (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Intéractions: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setInteration (player, 1);
                            inventory.setItem (23, new ItemStack (getDeny().setName ("§6Intéractions: " + ChatColor.RED + "Désactivé").toItemStack ()));
                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getInteraction (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Intéractions: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setInteration (player, 0);
                            inventory.setItem (23, new ItemStack (getAccept ().setName ("§6Intéractions: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        }
                    }

                    /* View Notifications de file d'attente*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Notifications de file d'attente: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setFileAttente (player, 1);
                            inventory.setItem (24, new ItemStack (getDeny().setName ("§6Notifications de file d'attente: " + ChatColor.RED + "Désactivé").toItemStack ()));
                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFileAttente (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Notifications de file d'attente: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setFileAttente (player, 0);
                            inventory.setItem (24, new ItemStack (getAccept ().setName ("§6Notifications de file d'attente: " + ChatColor.GREEN + "Activé").toItemStack ()));
                        }
                    }

                    /* View Ailes*/
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getElytra (player) == 0){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Ailes: " + ChatColor.GREEN + "Activé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setElytra (player, 1);
                            inventory.setItem (25, new ItemStack (getDeny().setName ("§6Ailes: " + ChatColor.RED + "Désactivé").toItemStack ()));
                            player.getInventory ().setChestplate (null);
                        }
                    }else if (APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getElytra (player) == 1){
                        if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Ailes: " + ChatColor.RED + "Désactivé")){
                            APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setElytra (player, 0);
                            inventory.setItem (25, new ItemStack (getAccept ().setName ("§6Ailes: " + ChatColor.GREEN + "Activé").toItemStack ()));
                            player.getInventory ().setChestplate (getElytra ());
                        }
                    }

                    break;

            case PAPER:
                if (current.getItemMeta ().getDisplayName ().equalsIgnoreCase (ChatColor.YELLOW + "Page 2 »")){
                    Hub.getHubInstance ().open (player, GuiSettingsTwo.class);
                }
                break;
            case EMERALD:
                Hub.getHubInstance ().open (player, GuiProfil.class);
                break;
            default:break;
        }
    }

    @Override
    public int getSize() {
        return 45;
    }

    private static ItemStack getNextPage(){
        ItemStack stack = new ItemStack (Material.PAPER, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName (ChatColor.YELLOW + "Page 2 »");
        stack.setItemMeta (meta);

        return stack;
    }

    protected static ItemStack getPlayerHead(Player player){
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(player.getName ());
        head.setItemMeta(meta);

        return head;
    }

    private static ItemStack getMap(){
        ItemStack map = new ItemStack(Material.MAP, 1);
        ItemMeta itemMeta = map.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        map.setItemMeta(itemMeta);
        return map;
    }

    private ItemStack getElytra(){
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta meta = elytra.getItemMeta();
        meta.spigot().setUnbreakable(true);
        elytra.setItemMeta(meta);
        return elytra;
    }


    private static ItemStack getSettingPlayer(Player player){

        ItemStack stack = new ItemStack (getPlayerHead (player));
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Joueurs");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous verrez",
                ChatColor.GRAY + "les joueurs autour de vous dans le hub. Dans le cas",
                ChatColor.GRAY + "contraire, vous verrez seulement le " + ChatColor.GOLD + "Staff" + ChatColor.GRAY + ",",
                ChatColor.GRAY + "les " + ChatColor.GOLD + "Coupaings" + ChatColor.GRAY + " et vos " + ChatColor.GOLD + "amis" + ChatColor.GRAY + "."));
        stack.setItemMeta (meta);

        return stack;
    }


    private static ItemStack getSettingMsg(){

        ItemStack stack = new ItemStack (Material.PAPER);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Messages privés");
        meta.setLore (Arrays.asList ( ChatColor.GRAY + "Quand cette option est activée, vous pourrez",
                ChatColor.GRAY + "recevoir des messages privés de la part des",
                ChatColor.GRAY + "joueurs. Vos amis pourront quand même vous",
                ChatColor.GRAY + "envoyer des messages."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingMention(){

        ItemStack stack = new ItemStack (getMap ());
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Notifications");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous pourrez",
                ChatColor.GRAY + "recevoir un signal sonore lorsqu'un joueur",
                ChatColor.GRAY + "écrit votre nom dans le chat."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingJukeBox(){

        ItemStack stack = new ItemStack (Material.JUKEBOX);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Jukebox");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous",ChatColor.GRAY + "entenderez la musique du Jukebox dans",
                ChatColor.GRAY + "les hubs."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingInteraction(){

        ItemStack stack = new ItemStack (Material.COOKIE);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Intéractions");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, les",ChatColor.GRAY + "intéractions seront possibles avec votre",ChatColor.GRAY + "joueur, comme par exemple celles avec les",ChatColor.GRAY + "gadgets. Seuls vos " + ChatColor.GOLD + "amis" + ChatColor.GRAY + " pourront tout",ChatColor.GRAY + "de même intéragir avec vous."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingFileAttentes(){

        ItemStack stack = new ItemStack (Material.SIGN);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Notifications de file d'attente");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous",
                ChatColor.GRAY + "recevrez des informations sur votre",ChatColor.GRAY + "statut dans les files d'attente."));
        stack.setItemMeta (meta);

        return stack;
    }


    private static ItemStack getSettingElytra(){

        ItemStack stack = new ItemStack (Material.ELYTRA);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Ailes");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous",
                ChatColor.GRAY + "serez équipé par défaut d'une paire", ChatColor.GRAY + "d'ailes quand vous entrerez dans un",ChatColor.GRAY + "Hub.","",ChatColor.RED + "Cette option est disponible uniquement", ChatColor.RED + "pour les versions supérieures Ã  la 1.9."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemBuilder getAccept(){
        return new ItemBuilder (Material.STAINED_GLASS).setStainedGlassGlassColor (DyeColor.GREEN);
    }

    private static ItemBuilder getDeny(){
        return new ItemBuilder (Material.STAINED_GLASS).setStainedGlassGlassColor (DyeColor.RED);
    }
}
