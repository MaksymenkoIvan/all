package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;
import xyz.karpador.godfishbot.Main;

public class SexCommand extends Command {

    private static final String[] SEX_MESSAGES = {
            "Пользователь %1$s выебал пользователя \"%2$s\"", "Пользователь %1$s поимел пользователя \"%2$s\"", "Пользователь %1$s жёстко оттрахал пользователя \"%2$s\"",
            "Пользователь %1$s сделал жёсткий секс с пользователем \"%2$s\"", "Пользователь %1$s чпокнул пользователя \"%2$s\""
    };

    @Override
    public String getName() {
        return "sex";
    }

    @Override
    public String getUsage() {
        return "/sex [target]";
    }


    @Override
    public String getDescription() {
        return "Займись с кем-то сексом";
    }

    @Override
    public CommandResult getReply(String params, Message message, String myName) {
        CommandResult result =
                new CommandResult(SEX_MESSAGES[Main.Random.nextInt(SEX_MESSAGES.length)]);
        if (params != null)
            result.text = String.format(result.text, message.getFrom().getFirstName(), params);
        else
            result.text = String.format(result.text, myName, message.getFrom().getFirstName());
        return result;
    }

}