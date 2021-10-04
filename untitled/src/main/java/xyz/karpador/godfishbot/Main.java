package xyz.karpador.godfishbot;

import java.util.Random;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
	public static final Random Random = new Random();

	public static void main(String[] args) {
		System.out.println("Initializing ApiContext...");
		ApiContextInitializer.init();

		System.out.println("Creating BotsApi...");
		TelegramBotsApi botsApi = new TelegramBotsApi();

		System.out.println("Loading config file...");
		BotConfig.getInstance().init();

		if (BotConfig.getInstance().getTelegramBotToken().startsWith("<")) {
			System.err.println("Fatal: No Telegram Bot API Token defined in config file!");
			System.err.println("Please refer to the documentation.");
			System.exit(0);
		}

		try {
			System.out.println("Starting bot...");
			botsApi.registerBot(new GodfishPollingBot());
			System.out.println("Started!");
		} catch (TelegramApiException e) {
			System.out.println("Start failed:");
			e.printStackTrace();
		}
	}
}