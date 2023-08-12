package revivable;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.Map;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslationPlugin implements Listener {

    private File configFile = new File("plugins/Revivable/config.yml");
    private Yaml yaml = new Yaml();
    private Translate translate = TranslateOptions.getDefaultInstance().getService();
    
    public TranslationPlugin() {
        // Register events
    }

    @EventHandler
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        String preferredLanguage = event.getNewLocale();

        Map<String, Object> config = loadConfig();
        translateAndReplace(config, preferredLanguage);
        saveConfig(config);
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
        String[] keysToTranslate = {"PlayerOnly", "NEArgs", "LangNoExist", /* ... */};

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
