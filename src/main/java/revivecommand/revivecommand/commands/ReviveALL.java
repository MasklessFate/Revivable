package revivecommand.revivecommand.commands;

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
            Player player = (Player) sender;
            if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                player.sendMessage(ChatColor.GREEN + "You are the only one online");
            } else if (Bukkit.getServer().getOnlinePlayers().size() > 1) {
                int numOfPlayers = 0;
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.teleport(p.getLastDeathLocation());
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(ChatColor.GREEN + p.getName() + " got Revived");
                    p.sendMessage(ChatColor.GREEN + p.getName() + " you are revived YAAAAAAY");
                    numOfPlayers++;
                }
                player.sendMessage(ChatColor.YELLOW + "Revived all" + (numOfPlayers - 1) + " players");
            }
        }

        return true;

    }
}
