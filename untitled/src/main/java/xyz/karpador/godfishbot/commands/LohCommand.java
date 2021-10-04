package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;

public class LohCommand extends Command{
    @Override
    public String getName() {
        return "click_on_me";
    }

    @Override
    public String getDescription() {
        return "Тебе скажут что-то не приятное";
    }

    @Override
    public CommandResult getReply(String params, Message message, String myName) {
        return new CommandResult( message.getFrom().getFirstName() + ", ТЫ ЛОХ Галимый");
    }
}