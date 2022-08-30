package revivecommand.revivecommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Revive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Players can reive.");
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            p.sendMessage(ChatColor.RED + p.getName() +" you have to specify the player that you want to revive");
            p.sendMessage(ChatColor.GREEN + "To revive: /r <playername>, /rp <playername>, /pr <playername>, /rev <playername>");
        }else if (p.getGameMode() == GameMode.SPECTATOR | args.length == 1) {
             Player  target = Bukkit.getPlayer(args[0]);
                    target.teleport(p.getLastDeathLocation());
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage(ChatColor.GREEN + p.getName() + " got Revived");
                    target.sendMessage(ChatColor.GREEN + p.getName() + " you are revived YAAAAAAY");

                } else {
                    p.sendMessage(ChatColor.RED + p.getName() + " is alive");
        }
        return true;
    }
}

