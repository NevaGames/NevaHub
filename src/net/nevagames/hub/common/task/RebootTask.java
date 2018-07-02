package net.nevagames.hub.common.task;

import net.nevagames.api.APIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RebootTask extends BukkitRunnable {

    private int timer = 60;
    //14400

    @Override
    public void run() {


        APIPlugin.log ("Reboot lancé avec succes.");

        if(timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage(ChatColor.RED + "[REBOOT] Le serveur redémarre dans " + timer + "secondes");
            for (Player p : Bukkit.getOnlinePlayers ()){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10f, 1f);
            }
        }

        if (timer == 0){
            for (Player player : Bukkit.getOnlinePlayers ()){
                player.kickPlayer ("§cLe serveur redémarre, veuillez patienter !");
            }
            Bukkit.getServer ().shutdown ();
        }
        APIPlugin.log (timer + " secondes");
        timer --;

    }
}
