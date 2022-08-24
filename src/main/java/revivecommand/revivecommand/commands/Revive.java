package revivecommand.revivecommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Revive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Player can reive.");
            return true;
        }

        Player player = (Player) sender;


        for (Player p : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode() == GameMode.SPECTATOR) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("Someone got Revived");
            } else {
                player.sendMessage("This Player is alive");
            }
            }
        return true;
    }
}
