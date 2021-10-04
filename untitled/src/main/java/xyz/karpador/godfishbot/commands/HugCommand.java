package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;
import xyz.karpador.godfishbot.Main;

public class HugCommand extends Command {

	private static final String[] HUG_MESSAGES = {
		"Пользователь %1$s обнял пользователя \"%2$s\"", "Пользователь %1$s очень крепко обнял пользователя \"%2$s\"",
		"Пользователь %2$s получил обнимашки от пользователя \"%1$s\""
	};

	@Override
	public String getName() {
		return "hug";
	}

	@Override
	public String getUsage() {
		return "/hug [target]";
	}

	@Override
	public String getDescription() {
		return "Обмини кого-то или получи обнимашки";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		CommandResult result =
				new CommandResult(
						HUG_MESSAGES[Main.Random.nextInt(HUG_MESSAGES.length)]
				);
		if (params != null) {
			result.text = String.format(result.text, message.getFrom().getFirstName(), params);
		} else {
			result.text = String.format(result.text, myName, message.getFrom().getFirstName());
		}
		return result;
	}

}