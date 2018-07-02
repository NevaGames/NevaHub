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

import net.nevagames.tools.chat.fanciful.FancyMessage;
import net.nevagames.tools.players.InventoryUtils;
import net.nevagames.tools.players.PlayerUtils;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class PlayerManagers{

    public static final String SETTINGS_TAG = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Paramètres" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    public static final String MODERATING_TAG = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Modération" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    public static final String SHOPPING_TAG = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Boutique" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    public static final String COSMETICS_TAG = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Cosmétique" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;
    public static final String RULES_TAG = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Règles" + ChatColor.DARK_AQUA + "] " + ChatColor.RESET;

    private static final float WALK_SPEED = 0.20F;
    private static final float FLY_SPEED = 0.15F;

    private final Map<UUID, Location> selections;
    private final List<UUID> hiders;
    private final StaticInventory staticInventory;

    private Hub hub;
    private boolean canBuild;

    public PlayerManagers(Hub hub) {
        this.hub = hub;
        this.selections = new HashMap<> ();
        this.hiders = new ArrayList<> ();
        this.staticInventory = new StaticInventory ();
        this.canBuild = false;

    }
    public void onPlayerLogin(Player player){

            try {

                /* Reset player stats */
                player.setGameMode (GameMode.ADVENTURE);
                player.setWalkSpeed (WALK_SPEED);
                player.setFlySpeed (FLY_SPEED);
                player.setFoodLevel (20);
                player.setHealth (20.0D);

                /* Clear and set the inventory, scoreboard */
                InventoryUtils.cleanPlayer (player);
                this.staticInventory.setInventoryToPlayer (player);
                this.hub.getScoreboardManager ().onLogin (player);

                /* Define player join broadcast message */
                if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIP || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIPPLUS){
                    this.hub.getServer ().broadcastMessage (PlayerUtils.getFullyFormattedPlayerName (player) + ChatColor.YELLOW + " a rejoint le hub !");
                }

                if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIP || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIPPLUS){
                    this.hub.getServer().getScheduler().runTask(this.hub, () -> player.setAllowFlight(true));
                }

                /* Send message to the player when he join the server hub */
                this.hub.getScheduledExecutorService().schedule(() -> {
                    if(!player.isOnline ())return;

                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1.0F, 1.0F);

                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    new FancyMessage ("Hey ! Venez tester notre nouveau jeu Ktp2017 ! ").color(ChatColor.YELLOW)
                            .then("[Cliquez ici]").color(ChatColor.GREEN).style(ChatColor.BOLD).command("/join test")
                            .formattedTooltip(new FancyMessage("Clic pour rejoindre la file d'attente.").color(ChatColor.YELLOW))
                            .send(player);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                }, 10, TimeUnit.SECONDS);

                this.updateHiders(player);
                this.hub.getLogger ().log (Level.INFO, "Handling login from '" + player.getUniqueId() + "'...");

            }catch (Exception exception){
                player.sendMessage(ChatColor.RED + "Une erreur a été détecté lors du chargement de votre joueur, vous devrez peut-être vous reconnecter.");
                exception.printStackTrace();
            }
    }

    public void onPlayerLogout(Player player){
        this.hub.getLogger ().log (Level.INFO, "Handling logout from '" + player.getUniqueId() + "'...");
        this.hub.getScoreboardManager ().onLogout (player);

            if (this.selections.containsKey(player.getUniqueId())){
                this.selections.remove(player.getUniqueId());
            }
    }
    public void addHider(Player hider) {
        this.hiders.add(hider.getUniqueId());

                this.hub.getServer().getOnlinePlayers().stream().filter(player -> (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.VIP || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) != RankUnit.VIPPLUS)).forEach(player ->
                        this.hub.getServer().getScheduler().runTask(this.hub, () -> hider.hidePlayer(player)));
    }

    public void removeHider(Player player){
        if (this.hiders.contains(player.getUniqueId()))
            this.hiders.remove(player.getUniqueId());

        this.hub.getServer().getScheduler().runTask(this.hub, () -> this.hub.getServer().getOnlinePlayers().forEach(player::showPlayer));
    }

    private void updateHiders(Player player) {

            List<UUID> hidersUUIDList = new ArrayList<>();
            hidersUUIDList.addAll(this.hiders);

            for (UUID hiderUUID : hidersUUIDList){
                Player hider = this.hub.getServer().getPlayer(hiderUUID);

                if (hider != null && !hider.equals(player))
                    if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIP || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIPPLUS)
                        this.hub.getServer().getScheduler().runTask(this.hub, () -> hider.hidePlayer(player));
            }
    }
    public void setSelection(Player player, Location selection){
        this.selections.put(player.getUniqueId(), selection);
    }
    public void setBuild(boolean canBuild){
        this.canBuild = canBuild;
    }
    public StaticInventory getStaticInventory(){
        return this.staticInventory;
    }
    public Location getSelection(Player player) {
        return this.selections.getOrDefault (player.getUniqueId ( ), null);
    }
    public boolean canBuild(){
        return this.canBuild;
    }
}

