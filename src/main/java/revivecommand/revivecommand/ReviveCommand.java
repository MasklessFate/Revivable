package revivecommand.revivecommand;


import revivecommand.revivecommand.commands.Revive;
import revivecommand.revivecommand.commands.ReviveALL;
import revivecommand.revivecommand.utils.Logger;
import revivecommand.revivecommand.utils.Settings;
import revivecommand.revivecommand.utils.UpdateChecker;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReviveCommand extends JavaPlugin {

    private static Plugin instance;
    public static ReviveCommand plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        plugin = this;
        //PLEASE REPLACE THE RESOURCE ID WITH YOUR SPIGOT RESOURCE
        new UpdateChecker(this, 104765).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.log(Logger.LogLevel.SUCCESS,("Revive Plugin is up to date!"));
            } else {
                Logger.log(Logger.LogLevel.OUTLINE,  "*********************************************************************");
                Logger.log(Logger.LogLevel.WARNING,("Revive Plugin is outdated!"));
                Logger.log(Logger.LogLevel.WARNING,("Newest version: " + version));
                Logger.log(Logger.LogLevel.WARNING,("Your version: " + Settings.VERSION));
                Logger.log(Logger.LogLevel.WARNING,("Please Update Here: " + Settings.PLUGIN_URL));
                Logger.log(Logger.LogLevel.OUTLINE,  "*********************************************************************");			}
        });
        getCommand( "revive").setExecutor(new Revive());
        getCommand( "reviveall").setExecutor(new ReviveALL());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ReviveCommand getPlugin() {
        return plugin;
    }

    public static Plugin getInstance() {
        return instance;
    }
}