/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018 /2018 @author FiddlerGun.
 * Created  on 27/2/2018
 * All rights reserved.
 */

package net.nevagames.hub.commands.admin;

import net.nevagames.api.APIPlugin;
import net.nevagames.hub.Hub;
import net.nevagames.hub.commands.AbstractCommand;
import net.nevagames.hub.common.players.PlayerManagers;
import net.nevagames.tools.players.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAnguille extends AbstractCommand {

    public CommandAnguille(Hub hub)
    {
        super(hub);
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] arguments) {
        Player player = (Player) sender;

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.BUILDER){
            if (this.hub.getPlayerManagers().canBuild()){
                this.hub.getPlayerManagers().setBuild(false);
                player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.RED + "Construction désactivée.");
                player.getInventory ().clear ();
                player.setGameMode (GameMode.ADVENTURE);
                this.hub.getPlayerManagers ().getStaticInventory ().setInventoryToPlayer (player);
            }else{
                this.hub.getPlayerManagers().setBuild(true);
                player.sendMessage(PlayerManagers.SETTINGS_TAG + ChatColor.GREEN + "Construction activée.");
                player.getInventory ().clear ();
                player.setGameMode (GameMode.CREATIVE);
            }
        }
        return true;
    }

}
