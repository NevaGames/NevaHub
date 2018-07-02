/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.profile;

import net.nevagames.api.APIPlugin;
import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.Hub;
import net.nevagames.hub.gui.profile.settings.GuiSettings;
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiClickMeSettings extends AbstractGui {

    @Override
    public String name() {
        return "Paramètres du ClickMe";
    }

    @Override
    public void contents(Player player, Inventory inventory) {

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 0){
            inventory.setItem (10, new ItemBuilder (accesClickMe ()).toItemStack ());
            inventory.setItem (19, new ItemStack (getAccept ().setName ("§6ClickMe: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (10, new ItemBuilder (accesClickMe ()).toItemStack ());
            inventory.setItem (19, new ItemStack (getDeny ().setName ("§6ClickMe: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }
        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getStats (player) == 0){
            inventory.setItem (11, new ItemBuilder (getStatistique ()).toItemStack ());
            inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Vue des statistiques: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (11, new ItemBuilder (getStatistique ()).toItemStack ());
            inventory.setItem (20, new ItemStack (getDeny ().setName ("§6Vue des statistiques: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewCoins (player) == 0){
            inventory.setItem (12, new ItemBuilder (getSettingCoins ()).toItemStack ());
            inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Vue des coins: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (12, new ItemBuilder (getSettingCoins ()).toItemStack ());
            inventory.setItem (21, new ItemStack (getDeny ().setName ("§6Vue des coins: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewStar (player) == 0){
            inventory.setItem (13, new ItemBuilder (getSettingStar ()).toItemStack ());
            inventory.setItem (22, new ItemStack (getAccept ().setName ("§6Vue de la poussière d'\u272F: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (13, new ItemBuilder (getSettingStar ()).toItemStack ());
            inventory.setItem (22, new ItemStack (getDeny ().setName ("§6Vue de la poussière d'\u272F: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getSneak (player) == 0){
            inventory.setItem (16, new ItemBuilder (getSneak ()).toItemStack ());
            inventory.setItem (25, new ItemStack (getAccept ().setName ("§6Clic gauche: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (16, new ItemBuilder (getSneak ()).toItemStack ());
            inventory.setItem (25, new ItemStack (getDeny ().setName ("§6Clic gauche: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        inventory.setItem (40, new ItemBuilder (getBackIcon ()).toItemStack ());
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        switch (current.getType ()){
            case  EMERALD:
                Hub.getHubInstance ().open (player, GuiSettings.class);
                break;

            case STAINED_GLASS:

                /* View Coins*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewCoins (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue des coins: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewCoins (player, 1);
                        inventory.setItem (21, new ItemStack (getDeny ().setName ("§6Vue des coins: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewCoins (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue des coins: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewCoins (player, 0);
                        inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Vue des coins: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* View Star*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewStar (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue de la poussière d'\u272F: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewStar (player, 1);
                        inventory.setItem (22, new ItemStack (getDeny ().setName ("§6Vue de la poussière d'\u272F: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewStar (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue de la poussière d'\u272F: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewStar (player, 0);
                        inventory.setItem (22, new ItemStack (getAccept ().setName ("§6Vue de la poussière d'\u272F: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* Sneak */
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getSneak (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Clic gauche: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setSneak (player, 1);
                        inventory.setItem (25, new ItemStack (getDeny ().setName ("§6Clic gauche: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getSneak (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Clic gauche: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setSneak (player, 0);
                        inventory.setItem (25, new ItemStack (getAccept ().setName ("§6Clic gauche: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* ClickMe */
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6ClickMe: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setClickMe (player, 1);
                        inventory.setItem (19, new ItemStack (getDeny ().setName ("§6ClickMe: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6ClickMe: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setClickMe (player, 0);
                        inventory.setItem (19, new ItemStack (getAccept ().setName ("§6ClickMe: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* ClickMe */
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getStats (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue des statistiques: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setStats (player, 1);
                        inventory.setItem (20, new ItemStack (getDeny ().setName ("§6Vue des statistiques: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getStats (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Vue des statistiques: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setStats (player, 0);
                        inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Vue des statistiques: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }
                break;
                default:break;
        }
    }

    @Override
    public int getSize() {
        return 45;
    }

    private ItemStack accesClickMe(){
        ItemStack stack = new ItemStack (Material.WOOD_BUTTON, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6ClickMe");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, votre ClickMe", ChatColor.GRAY + "sera activé. Les joueurs pourront y accéder en", ChatColor.GRAY + "cliquant sur vous dans les hubs ou via la",
                ChatColor.GRAY + "commande " + ChatColor.GOLD + "/click <pseudo>" + ChatColor.GRAY + "."));
        stack.setItemMeta (meta);
        return stack;

    }

    private ItemStack getSettingCoins(){
        ItemStack stack = new ItemStack (Material.GOLD_INGOT, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Vue des pièces");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, les joueurs",ChatColor.GRAY + "pourront voir votre nombre de coins dans", ChatColor.GRAY + "votre ClickMe."));
        stack.setItemMeta (meta);
        return stack;
    }

    private ItemStack getSettingStar(){
        ItemStack stack = new ItemStack (Material.SUGAR, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Vue de la poussiÃ¨re d'\u272F");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, les joueurs",ChatColor.GRAY + "pourront voir votre nombre de poussiÃ¨re d'\u272F",ChatColor.GRAY + "dans votre ClickMe."));
        stack.setItemMeta (meta);
        return stack;
    }

    private ItemStack getStatistique(){
        ItemStack stack = new ItemStack (Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Vue des statistiques");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activÃ©e, les joueurs",ChatColor.GRAY + "pourront voir vos statistiques des jeux dans", ChatColor.GRAY + "votre ClickMe."));

        stack.setItemMeta (meta);
        return stack;
    }

    private ItemStack getSneak(){
        ItemStack stack = new ItemStack (Material.IRON_SWORD, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Clic gauche");
        meta.setLore (Arrays.asList (  ChatColor.GRAY + "Quand cette option est activée, vous",ChatColor.GRAY + "accéderez au ClickMe des joueurs en faisant",ChatColor.GRAY + "un clic gauche dessus tout en sneakant."));

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
