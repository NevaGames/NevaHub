/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.main;

import net.nevagames.api.APIPlugin;
import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.Hub;
import net.nevagames.hub.games.GamesTypeManager;
import net.nevagames.tools.items.ItemBuilder;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GuiMain extends AbstractGui {

    private Hub hub;
    private GamesTypeManager gamesTypeManager;

    public GuiMain(Hub hub) {
        this.hub = hub;
        this.gamesTypeManager = this.hub.getGamesTypeManager();
    }

    @Override
    public String name() {
        return "Menu Principal";
    }

    @Override
    public void contents(Player player, Inventory inventory) {

        inventory.setItem(9, new ItemBuilder (Material.DIAMOND).setName(ChatColor.GOLD + "Zone " + ChatColor.GREEN + "VIP").setLore(makeButtonLore(new String[] { "Testez les jeux avant tout le monde !" }, false, true)).toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.BED).setName(ChatColor.GOLD + "Spawn").setLore(makeButtonLore(new String[] { "Retournez au spawn grâce à la magie", "de la téléportation céleste !" }, false, true)).toItemStack());
        inventory.setItem(26, new ItemBuilder(Material.PACKED_ICE).setName(ChatColor.GOLD + "Parcours du ciel").setLore(makeButtonLore(new String[] { "En espérant que vous atteignez le", "paradis..."}, false, true)).toItemStack());
        inventory.setItem(27, new ItemBuilder(Material.EMPTY_MAP).setName(ChatColor.GOLD + "Informations").setLore(getInformationLore()).toItemStack());
        inventory.setItem(35, new ItemBuilder(Material.ENDER_CHEST).setName(ChatColor.GOLD + "Changer de hub").setLore(makeButtonLore(new String[] { "Cliquez pour ouvrir l'interface" }, true, false)).toItemStack());
        inventory.setItem ( 17,new ItemBuilder (Material.IRON_FENCE).setName ("§c6Evènement").setLore (ChatColor.GRAY + "Aucun évènement en cours...").toItemStack ());


        ItemBuilder uppervoid = new ItemBuilder(this.gamesTypeManager.getUppervoidGame().getIcon()).setName(this.gamesTypeManager.getUppervoidGame().getName()).setLore("§8Affrontez les autres joueurs dans","§8une arène. Faites-les tomber dans le","§8vide à l'aide de vos impitoyables","§8TNT's, mais gare à ne pas tomber","§8à votre tour !",this.gamesTypeManager.getUppervoidGame().getDevelopers());

        inventory.setItem(this.gamesTypeManager.getUppervoidGame().getSlotInMainMenu(), uppervoid.toItemStack());

        inventory.setItem(this.gamesTypeManager.getDimensionsGame().getSlotInMainMenu(), new ItemBuilder(this.gamesTypeManager.getDimensionsGame().getIcon()).setName(this.gamesTypeManager.getDimensionsGame().getName()).setLore("§8Téléportez-vous d'un monde parallèle","§8à un autre et récupérez le maximum de","§8coffres. Ensuite, le combat pourra","§8débuter !",this.gamesTypeManager.getDimensionsGame().getDevelopers() ).toItemStack());

        inventory.setItem(this.gamesTypeManager.getQuakeGame().getSlotInMainMenu(), new ItemBuilder(this.gamesTypeManager.getQuakeGame().getIcon()).setName(this.gamesTypeManager.getQuakeGame().getName()).setLore( "§8Même si vous n'êtes pas un fermier,","§8tirez sur vos adversaires avec","§8votre houe !",this.gamesTypeManager.getQuakeGame().getDevelopers() ).toItemStack());

        inventory.setItem(this.gamesTypeManager.getBackEndGame().getSlotInMainMenu(), new ItemBuilder(this.gamesTypeManager.getBackEndGame().getIcon()).setName(this.gamesTypeManager.getBackEndGame().getName()).setLore("§8Téléportez-vous d'un monde parallèle","§8à un autre et récupérez le maximum de","§8coffres. Ensuite, le combat pourra","§8débuter !", this.gamesTypeManager.getBackEndGame().getDevelopers()).toItemStack());

        inventory.setItem(this.gamesTypeManager.getTntZoneGame().getSlotInMainMenu(), new ItemBuilder(this.gamesTypeManager.getTntZoneGame().getIcon()).setName(this.gamesTypeManager.getTntZoneGame().getName()).setLore("§8La Zone de tous nos jeux Tnt","§8sont réunis !","","§8\u2B29 TntRun","§8\u2B29 BowSpleef","§8\u2B29 TntTag", this.gamesTypeManager.getTntZoneGame().getDevelopers()).toItemStack());

        inventory.setItem ( 32,new ItemBuilder (Material.IRON_FENCE).setName ("§cProchainement").setLore (ChatColor.RED + "Prochainement...").toItemStack ());
        inventory.setItem ( 30,new ItemBuilder (Material.IRON_FENCE).setName ("§cProchainement").setLore (ChatColor.RED + "Prochainement...").toItemStack ());

        inventory.setItem ( 24,new ItemBuilder (Material.BURNING_FURNACE).setName ("§cJe ne sais pas encore").setLore ("§aC'est juste pour faire beau").toItemStack ());
            }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        if (current == null)return;


        switch (current.getType ()){
            case STICK:
                player.teleport(this.gamesTypeManager.getUppervoidGame().getLobbySpawn());
                break;

            case DIAMOND_HOE:
                player.teleport(this.gamesTypeManager.getQuakeGame().getLobbySpawn());
                player.sendMessage(ChatColor.RED + "Ce jeu n'est pas disponible.");
                break;

            case TNT:
                player.teleport(this.gamesTypeManager.getTntZoneGame().getLobbySpawn());
                break;

            case EYE_OF_ENDER:
                player.teleport(this.gamesTypeManager.getDimensionsGame().getLobbySpawn());
                player.sendMessage(ChatColor.RED + "Ce jeu n'est pas disponible.");
                break;

            case DIAMOND_SWORD:
                player.sendMessage(ChatColor.RED + "Ce jeu n'est pas disponible.");
                break;

            case PACKED_ICE:
                    player.teleport (new Location (player.getWorld (), 36.192, 103, -42.505 ,-137.8f, 11.5f));
                    player.sendRawMessage ("§3[§bParkour§3] §fVous approchez du §bJump du ciel§f.");
                    player.sendMessage ("Difficultée: §6✪✪✪✪");
                break;

            case  BED:
                player.teleport(new Location (player.getWorld(), -11.616, 100, -1.707, -179.6f, 9.8f ));
                break;

            case DIAMOND:
                    if(APIPlugin.getNevaGamesCore().getBeanManager().getPlayerBean().getRankUnit (player) == RankUnit.JOUEUR){
                        player.sendMessage(ChatColor.RED + "Devenez VIP pour avoir accès a la zone.");
                        return;
                    }
                    player.teleport(new Location (player.getWorld(), 37.615, 210, 458.646, -179.6f, -6.2f));
                break;

            case  ENDER_CHEST:
                if(current.getItemMeta ().getDisplayName ().equals ("§6§lChanger de Hub")){

                    /*
                     *  Todo
                     */
                }
                break;

            default:break;
        }

    }

    @Override
    public int getSize() {
        return 45;
    }

    private static String[] makeButtonLore(String[] description, boolean clickOpen, boolean clickTeleport){
        List<String> lore = new ArrayList<>();
        String[] loreArray = new String[] {};

        if (description != null){
            for (String string : description)
                lore.add(ChatColor.GRAY + string);

            if (clickOpen || clickTeleport)
                lore.add("");
        }

        if (clickOpen)
            lore.add(ChatColor.DARK_GRAY + "\u25B6 Cliquez pour ouvrir le menu");

        if (clickTeleport)
            lore.add(ChatColor.DARK_GRAY + "\u25B6 Cliquez pour être téléporté");

        return lore.toArray(loreArray);
    }
}
