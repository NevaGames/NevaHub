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
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSlow extends AbstractCommand {

    public CommandSlow(Hub hub)
    {
        super(hub);
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] arguments) {
        Player player = (Player) sender;

        if(APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.ADMIN || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.RESP_MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.MODO || APIPlugin.getNevaGamesCore ().getBeanManager ().getPlayerBean ().getRankUnit (player) == RankUnit.DEVELOPER){

            if (arguments.length == 1){
                if (StringUtils.isNumeric(arguments[0])){
                    int slow = Integer.parseInt(arguments[0]);

                    if (slow > 300 || slow < 0){
                        player.sendMessage(PlayerManagers.MODERATING_TAG + ChatColor.RED + "Espacement incorrect ! (Trop grand ou négatif)");
                        return true;
                    }

                    this.hub.getChatManager().setActualSlowDuration(slow);

                    if (slow == 0)
                        this.hub.getServer().broadcastMessage(PlayerManagers.MODERATING_TAG + ChatColor.GREEN + "Le chat n'est plus ralenti.");
                    else
                        this.hub.getServer().broadcastMessage(PlayerManagers.MODERATING_TAG + ChatColor.YELLOW + "Un modérateur a ralenti le chat. Ecart minimum entre deux messages : " + ChatColor.AQUA + slow + " secondes" + ChatColor.YELLOW + ".");
                }else{
                    player.sendMessage(PlayerManagers.MODERATING_TAG + ChatColor.RED + "Veuillez entrer un nombre correct !");
                }
            }else{
                player.sendMessage(PlayerManagers.MODERATING_TAG + ChatColor.GREEN + "Ralentissement actuel du chat : " + ChatColor.GOLD + this.hub.getChatManager().getActualSlowDuration());
            }
        }
        return true;
    }
}
