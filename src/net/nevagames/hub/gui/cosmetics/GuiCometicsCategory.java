/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.gui.cosmetics;

import net.nevagames.api.gui.AbstractGui;
import net.nevagames.hub.Hub;
import net.nevagames.tools.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiCometicsCategory extends AbstractGui {

    @Override
    public String name() {
        return "Caverne aux trésors";
    }

    @Override
    public void contents(Player player, Inventory inventory) {


        inventory.setItem (0, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.GRAY).toItemStack ());
        inventory.setItem (9, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.GRAY).toItemStack ());
        inventory.setItem (18, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.GRAY).toItemStack ());
        inventory.setItem (27, new ItemBuilder (Material.IRON_FENCE).toItemStack ());

        inventory.setItem (1, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIGHT_BLUE).toItemStack ());
        inventory.setItem (10, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIGHT_BLUE).toItemStack ());
        inventory.setItem (19, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIGHT_BLUE).toItemStack ());
        inventory.setItem (28, new ItemBuilder (Material.BLAZE_POWDER).setName (ChatColor.YELLOW + "Humeurs ").setLore (ChatColor.GRAY + "Montrez-nous comment vous vous sentez !").toItemStack ());

        inventory.setItem (2, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIME).toItemStack ());
        inventory.setItem (11, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIME).toItemStack ());
        inventory.setItem (20, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIME).toItemStack ());
        inventory.setItem (29, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.LIME).toItemStack ());
        inventory.setItem (38, new ItemBuilder (Material.SADDLE).setName (ChatColor.GREEN + "Montures ").setLore (ChatColor.GRAY + "Un fidèle compagnon qui vous suit",                ChatColor.GRAY + "oû que vous soyez !").toItemStack ());

        inventory.setItem (4, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.PURPLE).toItemStack ());
        inventory.setItem (13, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.PURPLE).toItemStack ());
        inventory.setItem (22, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.PURPLE).toItemStack ());
        inventory.setItem (31, new ItemBuilder (Material.JUKEBOX).setName (ChatColor.WHITE + "Jukebox").setLore (ChatColor.GRAY + "Devenez un véritable DJ !").toItemStack ());

        inventory.setItem (6, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.RED).toItemStack ());
        inventory.setItem (15, new ItemBuilder (Material.SKULL_ITEM).setName (ChatColor.AQUA + "Déguisements").setLore ( ChatColor.GRAY + "Entrez dans la peau d'un autre",                ChatColor.GRAY + "personnage !").toItemStack ());

        inventory.setItem (7, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.YELLOW).toItemStack ());
        inventory.setItem (16, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.YELLOW).toItemStack ());
        inventory.setItem (25, new ItemBuilder (Material.FIREWORK).setName (ChatColor.BLUE + "Gadgets").setLore (ChatColor.GRAY + "Animez le serveur en exposant vos",
                ChatColor.GRAY + "nombreux gadgets venant du futur !").toItemStack ());

        inventory.setItem (8, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.BLUE).toItemStack ());
        inventory.setItem (17, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.BLUE).toItemStack ());
        inventory.setItem (26, new ItemBuilder (Material.STAINED_GLASS_PANE).setStainedGlassPanelGlassColor (DyeColor.BLUE).toItemStack ());
        inventory.setItem (35, new ItemBuilder (Material.CLAY_BALL).toItemStack ());


        inventory.setItem (49, getBackIcon ());

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack current) {

        if (current == null)return;

        switch (current.getType ()){
            case IRON_FENCE:
                if(inventory.getName ().equals ("Caverne aux trésors")){
                    player.closeInventory ();
                }
                break;

            case BLAZE_POWDER:
                if(inventory.getName ().equals ("Caverne aux trésors")){
                    Hub.getHubInstance ().open (player, GuiParticles.class);
                }
                break;

            case SADDLE:
                if(inventory.getName ().equals ("Caverne aux trésors")){

                }



            case EMERALD:
                if(inventory.getName ().equals ("Caverne aux trésors")){
                    player.closeInventory ();
                }

                break;



            default:break;
        }

    }

    @Override
    public int getSize() {
        return 54;
    }


}



