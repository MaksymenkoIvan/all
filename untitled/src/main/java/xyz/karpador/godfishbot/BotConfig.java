package xyz.karpador.godfishbot;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class BotConfig {
    private static BotConfig instance;

    public static BotConfig getInstance() {
        if (instance == null)
            instance = new BotConfig();
        return instance;
    }

    private String telegramBotToken;
    private String pixabayToken;
    private String gifmeToken;
    private String giphyToken;
    private String alphacodersToken;
    private String mashapeToken;

    public void init() {
        File configFile = new File("config.json");
        if (!configFile.exists()) {
            try {
                Files.copy(new File("config.example.json"), configFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        try {
            String fileContent = FileUtils.readFileToString(configFile, "UTF-8");
            JSONObject configJson = new JSONObject(fileContent);
            JSONObject apiKeys = configJson.getJSONObject("api_tokens");
            telegramBotToken = apiKeys.getString("telegram");
            pixabayToken = apiKeys.getString("pixabay");
            gifmeToken = apiKeys.getString("gifme");
            giphyToken = apiKeys.getString("giphy");
            alphacodersToken = apiKeys.getString("alphacoders");
            mashapeToken = apiKeys.getString("mashape");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getTelegramBotToken() {
        return telegramBotToken;
    }

    public String getPixabayToken() {
        return pixabayToken;
    }

    public String getAlphacodersToken() {
        return alphacodersToken;
    }

}