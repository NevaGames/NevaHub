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
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiSettingsTwo extends AbstractGui {

    @Override
    public String name() {
        return "Paramètres (Page 2)";
    }

    @Override
    public void contents(Player player, Inventory inventory) {


        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewChat (player) == 0){
            inventory.setItem (10, new ItemBuilder (getSettingChat ()).toItemStack ());
            inventory.setItem (19, new ItemStack (getAccept ().setName ("§6Chat: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (10, new ItemBuilder (getSettingChat ()).toItemStack ());
            inventory.setItem (19, new ItemStack (getDeny ().setName ("§6Chat: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getParty (player) == 0){
            inventory.setItem (11, new ItemBuilder (getSettingParty ()).toItemStack ());
            inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Demandes de groupe: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (11, new ItemBuilder (getSettingParty ()).toItemStack ());
            inventory.setItem (20, new ItemStack (getDeny ().setName ("§6Demandes de groupe: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFiend (player) == 0){
            inventory.setItem (12, new ItemBuilder (getSettingFriend ()).toItemStack ());
            inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Demandes d'amis: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (12, new ItemBuilder (getSettingFriend ()).toItemStack ());
            inventory.setItem (21, new ItemStack (getDeny ().setName ("§6Demandes d'amis: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 0){
            inventory.setItem (16, new ItemBuilder (getSettingClickMe ()).toItemStack ());
            inventory.setItem (25, new ItemStack (getAccept ().setName ("§6ClickMe: " + ChatColor.GREEN + "Activé").toItemStack ()));
        }else {
            inventory.setItem (16, new ItemBuilder (getSettingClickMe ()).toItemStack ());
            inventory.setItem (25, new ItemStack (getDeny ().setName ("§6ClickMe: " + ChatColor.RED + "Désactivé").toItemStack ()));
        }

        inventory.setItem (40, new ItemBuilder (getBackIcon ()).toItemStack ());
        inventory.setItem (36, new ItemBuilder (getBackPage ()).toItemStack ());

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        switch (current.getType ()){
            case EMERALD:
                player.closeInventory ();
                break;

            case PAPER:
                if (current.getItemMeta ().getDisplayName ().equalsIgnoreCase (ChatColor.YELLOW + "« Page 1")){
                    Hub.getHubInstance ().open (player, GuiSettings.class);
                }
                break;

            case STAINED_GLASS:

                /* View Chat*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewChat (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Chat: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewChat (player, 1);
                        inventory.setItem (19, new ItemStack (getDeny ().setName ("§6Chat: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getViewChat (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Chat: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setViewChat (player, 0);
                        inventory.setItem (19, new ItemStack (getAccept ().setName ("§6Chat: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* View Party*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getParty (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Demandes de groupe: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setPartySetting (player, 1);
                        inventory.setItem (20, new ItemStack (getDeny ().setName ("§6Demandes de groupe: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getParty (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Demandes de groupe: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setPartySetting (player, 0);
                        inventory.setItem (20, new ItemStack (getAccept ().setName ("§6Demandes de groupe: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* View Friend*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFiend (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Demandes d'amis: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setFriendSetting (player, 1);
                        inventory.setItem (21, new ItemStack (getDeny ().setName ("§6Demandes d'amis: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getFiend (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6Demandes d'amis: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setFriendSetting (player, 0);
                        inventory.setItem (21, new ItemStack (getAccept ().setName ("§6Demandes d'amis: " + ChatColor.GREEN + "Activé").toItemStack ()));
                    }
                }

                /* View ClickMe*/
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 0){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6ClickMe: " + ChatColor.GREEN + "Activé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setClickMe (player, 1);
                        inventory.setItem (25, new ItemStack (getDeny ().setName ("§6ClickMe: " + ChatColor.RED + "Désactivé").toItemStack ()));
                    }
                }else if(APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().getClickMe (player) == 1){
                    if(current.getItemMeta ().getDisplayName ().equalsIgnoreCase ("§6ClickMe: " + ChatColor.RED + "Désactivé")){
                        APIPlugin.getNevaGamesCore ().getBeanManager ().getSettings ().setClickMe (player, 0);
                        inventory.setItem (25, new ItemStack (getAccept ().setName ("§6ClickMe: " + ChatColor.GREEN + "Activé").toItemStack ()));
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

    private static ItemStack getBackPage(){
        ItemStack stack = new ItemStack (Material.PAPER, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName (ChatColor.YELLOW + "« Page 1");
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingChat(){

        ItemStack stack = new ItemStack (Material.BOOK_AND_QUILL);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Chat");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, vous pourrez", ChatColor.GRAY + "voir les messages des joueurs dans le chat."));
        stack.setItemMeta (meta);

        return stack;
    }

    private static ItemStack getSettingParty(){
        ItemStack stack = new ItemStack (Material.LEASH, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Demandes de groupe");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, les joueurs", ChatColor.GRAY + "pourront vous envoyer des demandes de groupe.",ChatColor.GRAY + "Vos amis pourront quand même vous inviter",ChatColor.GRAY + "dans un groupe."));

        stack.setItemMeta (meta);
        return stack;
    }


    private static ItemStack getSettingFriend(){
        ItemStack stack = new ItemStack (Material.RAW_FISH, 1, (short) 3);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6Demandes d'amis");
        meta.setLore (Arrays.asList (ChatColor.GRAY + "Quand cette option est activée, les joueurs", ChatColor.GRAY + "pourront vous envoyer des demandes d'amis."));

        stack.setItemMeta (meta);
        return stack;

    }

    private static ItemStack getSettingClickMe(){
        ItemStack stack = new ItemStack (Material.WOOD_BUTTON, 1);
        ItemMeta meta = stack.getItemMeta ();
        meta.setDisplayName ("§6ClickMe");
        meta.setLore (Arrays.asList ( ChatColor.GRAY + "En cliquant, vous accéderez à un menu",ChatColor.GRAY + "avec les diffÃ©rents paramètres du ClickMe.", ChatColor.GRAY + "Avec celui-ci vous pourrez accéder aux",ChatColor.GRAY + "statistiques des joueurs en faisant un", ChatColor.GRAY + "clic-gauche sur ceux-ci."));

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
