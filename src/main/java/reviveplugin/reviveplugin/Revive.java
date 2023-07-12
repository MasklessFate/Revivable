package reviveplugin.reviveplugin;

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
            sender.sendMessage("Only Players can revive.");
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            p.sendMessage(ChatColor.YELLOW + p.getName() +" you have to specify the player that you want to revive");
            p.sendMessage(ChatColor.YELLOW + "To revive: /r <playername>, /rp <playername>, /pr <playername>, /rev <playername>");
        }else if (args.length == 1){
            if   (p.getGameMode() == GameMode.SPECTATOR) {
                Player target = Bukkit.getPlayer(args[0]);
                target.teleport(p.getLastDeathLocation());
                target.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(Util.getMessage(Util.getLocale(), "PlayerRevived"));
                target.sendMessage(Util.getMessage(Util.getLocale(), "YouRevived"));

            } else {
                p.sendMessage(Util.getMessage(Util.getLocale(p), "Alive"));
            }
        }
        return true;
    }
}

