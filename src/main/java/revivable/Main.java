package reviveable;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        plugin = this;
        loadConfig();
        Util.loadMessages();
        new setLangCMD(this);
        //PLEASE REPLACE THE RESOURCE ID WITH YOUR SPIGOT RESOURCE
        new UpdateChecker(this, 104765).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.log(Logger.LogLevel.SUCCESS, ("Revive Plugin is up to date!"));
            } else {
                Logger.log(Logger.LogLevel.OUTLINE, "*********************************************************************");
                Logger.log(Logger.LogLevel.WARNING, ("Revive Plugin is outdated!"));
                Logger.log(Logger.LogLevel.WARNING, ("Newest version: " + version));
                Logger.log(Logger.LogLevel.WARNING, ("Your version: " + Settings.VERSION));
                Logger.log(Logger.LogLevel.WARNING, ("Please Update Here: " + Settings.SPIGOT_PLUGIN_URL + ","));
                Logger.log(Logger.LogLevel.WARNING, ("Here:" + Settings.CURSEFORGE_URL + ","));
                Logger.log(Logger.LogLevel.WARNING, ("Here:" + Settings.GITHUB_URL));
                Logger.log(Logger.LogLevel.WARNING, (" or Here" + Settings.MODRINTH_URL));
                Logger.log(Logger.LogLevel.OUTLINE, "*********************************************************************");
            }
        });
        getCommand("revive").setExecutor(new Revive());
        getCommand("reviveall").setExecutor(new ReviveALL());
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Main getInstance() {
        return instance;
    }


    public void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }
}