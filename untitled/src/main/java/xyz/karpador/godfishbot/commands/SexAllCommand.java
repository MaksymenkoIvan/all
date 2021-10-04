package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;
import xyz.karpador.godfishbot.Main;

public class SexAllCommand extends Command {

    private static final String[] SEX_MESSAGES = {
            "Пользователь %2$s выебал всех пользователей этой группы", "Пользователь %2$s поимел всех пользователей этой группы", "Пользователь %2$s жёстко оттрахал всех пользователей этой группы",
            "Пользователь %2$s сделал жёсткий секс со всеми пользователями этой группы", "Пользователь %2$s чпокнул всех пользователей этой группы"
    };

    @Override
    public String getName() {
        return "sexall";
    }

    @Override
    public String getDescription() {
        return "Займись сексом сразу со всеми";
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