package revivable;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class TranslationPlugin extends JavaPlugin {

    private File configFile = new File("plugins/Revivable/config.yml");
    private Yaml yaml = new Yaml();
    private Translate translate = TranslateOptions.newBuilder().setApiKey("dad8421f02msh0e7f735a357ad09p1b72e4jsn346fa80df33d").build().getService();



    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LocaleChangeListener(), this);
    }

    private class LocaleChangeListener implements Listener {

        @EventHandler
        public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
            String preferredLanguage = event.getNewLocale();
            Map<String, Object> config = loadConfig();
            translateAndReplace(config, preferredLanguage);
            saveConfig(config);
        }
    }

    private Map<String, Object> loadConfig() {
        try (InputStream inputStream = new FileInputStream(configFile)) {
            return yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void translateAndReplace(Map<String, Object> config, String targetLanguage) {
        String[] keysToTranslate = {"PlayerOnly", "NEArgs", "PlayerRevived", "YouRevived", "Alive", "OnlyOnline"};

        for (String key : keysToTranslate) {
            if (config.containsKey(key)) {
                String originalPhrase = config.get(key).toString();
                Translation translation = translate.translate(originalPhrase,
                        Translate.TranslateOption.targetLanguage(targetLanguage));
                String translatedPhrase = translation.getTranslatedText();
                config.put(key, translatedPhrase);
            }
        }
    }

    private void saveConfig(Map<String, Object> config) {
        try (Writer writer = new FileWriter(configFile)) {
            yaml.dump(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

