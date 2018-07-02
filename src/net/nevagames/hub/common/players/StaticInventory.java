/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.common.players;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.tools.games.EnumGame;
import net.nevagames.tools.players.GlowEffect;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;

public class StaticInventory {

    private final HashMap<Integer, ItemStack> items;

    public StaticInventory(){
        this.items = new HashMap<>();
        this.items.put(0, buildItemStack(Material.COMPASS, 1, 0, createTitle("Menu principal"), null));
        this.items.put(1, buildItemStack(Material.SKULL_ITEM, 1, 3, createTitle("Profil"), null));
        this.items.put(7, buildItemStack(Material.ENDER_CHEST, 1, 0, createTitle("Caverne aux trésors"), null));
        this.items.put(8, buildItemStack(Material.GOLD_INGOT, 1, 0, createTitle("Boutique"), null));
    }

    /**
     * Method to give the interaction with a item in inventory.
     * @param player - The player who click on item.
     * @param stack -  The item who player click
     */

    public void doInteraction(Player player, ItemStack stack){


        switch (stack.getType()){

            case BARRIER:
                if(Hub.getHubInstance ().parcours.containsKey (player)){
                    Hub.getHubInstance ().parcours.remove (player);
                    player.teleport(new Location(player.getWorld(), -11.616, 109, -1.707, -179.6f, 9.8f ));
                    player.sendMessage ( EnumGame.PARCOURS.getName () + " §fVous avez abondonné le §bJump du Ciel§f.");
                    player.getInventory ().clear ();

                    this.setInventoryToPlayer (player);
                }
                break;

            case ELYTRA:
                if (stack.getEnchantments().isEmpty()){
                    ItemStack elytra = new ItemStack(Material.ELYTRA);
                    ItemMeta meta = elytra.getItemMeta();
                    meta.spigot().setUnbreakable(true);
                    elytra.setItemMeta(meta);

                    player.getInventory().setChestplate(elytra);
                    this.setInventoryToPlayer(player);
                }else{
                    player.getInventory().setChestplate(null);
                    this.setInventoryToPlayer(player);
                }

                player.playSound(player.getLocation(), Sound.ENTITY_HORSE_SADDLE, 1F, 1F);
                break;

            case FEATHER:
                if (player.isGliding() && player.getVelocity().lengthSquared() != 0){
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.JOUEUR){
                        player.sendMessage(ChatColor.RED + "Devenez VIP pour utiliser le booster.");
                        return;
                    }
                    Vector velocity = player.getVelocity().add(player.getLocation().getDirection().normalize().multiply(1.5D));
                    ((CraftPlayer)player).getHandle().motX = velocity.getX();
                    ((CraftPlayer)player).getHandle().motY = velocity.getY();
                    ((CraftPlayer)player).getHandle().motZ = velocity.getZ();
                    ((CraftPlayer)player).getHandle().velocityChanged = true;
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 2F, 2F);
                }

            default:break;
        }
    }

    /**
     *
     * @param player
     */

    public void setInventoryToPlayer(Player player){

        for (int slot : this.items.keySet()) {
            if (this.items.get(slot).getType() == Material.SKULL_ITEM) {
                SkullMeta meta = (SkullMeta) this.items.get(slot).getItemMeta();
                meta.setOwner(player.getName());
                this.items.get(slot).setItemMeta(meta);
            }
            player.getInventory().setItem(slot, this.items.get(slot));
        }

        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.JOUEUR || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIP || APIPlugin.getNevaGamesCore().getBeanManager().getSettings().getElytra(player) == 1){
            player.getInventory().setItem(4, new ItemStack (Material.AIR));
        }else{
            ItemStack itemStack;

            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA){
                itemStack = buildItemStack(Material.ELYTRA, 1, 0, createTitle("Désactiver les ailes"), null);
                GlowEffect.addGlow(itemStack);
            }else{
                itemStack = buildItemStack(Material.ELYTRA, 1, 0, createTitle("Activer les ailes"), null);
            }
            player.getInventory().setItem(4, itemStack);
        }
        player.getInventory().setHeldItemSlot(0);
    }

    /**
     *
     * @param text
     * @return
     */

    private static String createTitle(String text){
        return ChatColor.GOLD + "" + ChatColor.BOLD + text + ChatColor.RESET + "" + ChatColor.GRAY + " (Clic-droit)";
    }

    /**
     *
     * @param material
     * @param quantity
     * @param data
     * @param name
     * @param lores
     * @return
     */

    private static ItemStack buildItemStack(Material material, int quantity, int data, String name, String[] lores){
        ItemStack stack = new ItemStack(material, quantity, (short) data);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);

        if (lores != null)
            meta.setLore(Arrays.asList(lores));
            stack.setItemMeta(meta);
            return stack;
    }
}
