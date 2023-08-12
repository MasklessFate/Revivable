package reviveplugin.reviveplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReviveALL implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                p.sendMessage(Util.getMessage(Util.getLocale(p),  "OnlyOnline"));
            } else if (Bukkit.getServer().getOnlinePlayers().size() > 1) {
                int numOfPlayers = 0;
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.teleport(p.getLastDeathLocation());
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(Util.getMessage(Util.getLocale(p), "YouRevived"));
                    numOfPlayers++;
                }
                p.sendMessage(ChatColor.YELLOW + "Revived all" + (numOfPlayers - 1) + " players");
            }
        }

        return true;

    }
}
