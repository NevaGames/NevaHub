package net.nevagames.hub.common.task;

import net.nevagames.api.APIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RebootTask extends BukkitRunnable {

    private int timer = 10800;
    //10 800

    @Override
    public void run() {

        if(timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage(ChatColor.RED + "§4[§cREBOOT§4] §eLe serveur redémarre dans §c" + timer + " secondes.");
            for (Player p : Bukkit.getOnlinePlayers ()){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 1L, 1L);
            }
        }

        if (timer == 0){
            for (Player player : Bukkit.getOnlinePlayers ()){
                player.kickPlayer ("§cLe serveur redémarre, veuillez patienter !");
            }
            Bukkit.getServer ().shutdown ();
        }
        timer --;

    }
}
