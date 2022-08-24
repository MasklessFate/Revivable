package revivecommand.revivecommand;

import org.bukkit.plugin.java.JavaPlugin;
import revivecommand.revivecommand.commands.Revive;

public final class ReviveCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand( "revive").setExecutor(new Revive());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
