/*
 * This file is a part of the NevaGames project
 * This code is absolutely confidential.
 * Copyright (c) 2018  @author FiddlerGun.
 * Created on 5/3/2018
 * All rights reserved.
 */

package net.nevagames.hub.commands;

import net.nevagames.hub.Hub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand implements CommandExecutor {

	protected final Hub hub;

	public AbstractCommand(Hub hub) {
		this.hub = hub;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		return onCommand(commandSender, s, strings);
	}

	protected abstract boolean onCommand(CommandSender sender, String label, String[] arguments);
}
