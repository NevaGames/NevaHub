/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.commands.moderating;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.hub.commands.AbstractCommand;
import net.nevagames.hub.common.players.PlayerManagers;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShutupCommand extends AbstractCommand {

    public ShutupCommand(Hub hub) {
        super (hub);
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] arguments) {

        Player player = (Player) sender;

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER){

            boolean now = !this.hub.getChatManager().isChatLocked();
            this.hub.getChatManager().setChatLocked(now);

            if (now)
                this.hub.getServer().broadcastMessage(PlayerManagers.MODERATING_TAG + ChatColor.RED + "Le chat a été fermé par un moderating.");
            else
                this.hub.getServer().broadcastMessage(PlayerManagers.MODERATING_TAG + ChatColor.GREEN + "Le chat a été réouvert.");
        }
        return true;
    }
}
