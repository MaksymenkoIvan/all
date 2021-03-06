package org.example;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class App
{
    public static void main( String[] args ) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Bot());
        }catch (TelegramApiException e){
            System.out.println(e);
        }
    }
}
