package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {
    private final static String BOT_TOKEN = "1326785955:AAExFsRKwDBnINEBUwYOrRrBfHe2k2DHApg";
    private Message massage;

    public void onUpdateReceived(Update update){
        Message message = update.getMessage();
        if (update.hasMessage()){
            sendMassage(massage, "Привет, я бот");
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    public void sendMassage(Message message, String s){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(s);
        try {
            SendMessage sendMessage1 = new SendMessage();
            String a;
            sendMassage(sendMessage1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendMassage(SendMessage sendMessage1) {
    }


    @Override
    public String getBotToken() {
        return null;
    }
}
