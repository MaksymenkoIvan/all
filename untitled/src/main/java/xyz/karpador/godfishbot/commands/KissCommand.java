package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;
import xyz.karpador.godfishbot.Main;

public class KissCommand extends Command {

	private static final String[] KISS_MESSAGES = {
		"Пользователь %1$s поцеловал пользователя \"%2$s\"", "Пользователь %1$s поцеловал пользователя \"%2$s\" в губки",
		"Пользователь %1$s сексуально поцеловал пользователя \"%2$s\"", "Пользователь %1$s поцеловал ножки пользователю \"%2$s\"",
		"Пользователь %1$s поцеловал пользователя \"%2$s\" ниже пояса", "Пользователь %1$s поцеловал пользователя \"%2$s\" в носик",
	};

	@Override
	public String getName() {
		return "kiss";
	}

	@Override
	public String getUsage() {
		return "/kiss [target]";
	}

	@Override
	public String getDescription() {
		return "Поцелуй кого-то";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		CommandResult result =
			new CommandResult(KISS_MESSAGES[Main.Random.nextInt(KISS_MESSAGES.length)]);
		if (params != null)
			result.text = String.format(result.text, message.getFrom().getFirstName(), params);
		else
			result.text = String.format(result.text, myName, message.getFrom().getFirstName());
		return result;
	}

}