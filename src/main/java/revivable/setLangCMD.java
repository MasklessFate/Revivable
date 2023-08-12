package revivable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class setLangCMD implements CommandExecutor, Listener, TabCompleter {



    public setLangCMD(Main plugin) {
        plugin.getCommand("setLang").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Util.getMessage("en", "PlayerOnly"));
            return false;
        }
        Player p = (Player) commandSender;
        if (strings.length > 0 ) {
            p.sendMessage(Util.getMessage(Util.getLocale(p), "NEArgs"));
            return false;
        }
        String fileName = strings[0];
        Util.setLocale(p, fileName);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> langFiles = new ArrayList<>();
        File folder = new File(Main.getInstance().getDataFolder() + "/langs");
        for (File file : folder.listFiles()) {
            langFiles.add(file.getName().split(".yml")[0]);
        }
        return langFiles;
    }
}