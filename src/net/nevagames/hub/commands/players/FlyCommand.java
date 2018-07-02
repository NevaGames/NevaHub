/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.commands.players;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.hub.commands.AbstractCommand;
import net.nevagames.hub.common.players.PlayerManagers;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends AbstractCommand {

    public FlyCommand(Hub hub) {
        super (hub);
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] arguments) {

        Player player = (Player) sender;

        if (APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIPPLUS || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.VIPPLUS){

        if (player.getGameMode() != GameMode.ADVENTURE){
            player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.RED + "Vous ne pouvez pas utiliser cette commande actuellement.");
            return true;
        }

        boolean now = !player.getAllowFlight();
        player.setAllowFlight(now);

        if (now)
            player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.GREEN + "Vous pouvez maintenant voler.");
        else
            player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.GREEN + "Vous ne pouvez plus voler.");
        }else {
            player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.RED + "Vous n'avez pas le grade nécéssaire pour utiliser cette commande.");
        }
        return true;

    }
}
