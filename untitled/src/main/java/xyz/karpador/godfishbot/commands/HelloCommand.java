package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;


public class HelloCommand extends Command {

	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public String getDescription() {
		return "Тебе скажут привет";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		return new CommandResult("Привет, " + message.getFrom().getFirstName() + " ( ͡° ͜ʖ ͡°)");
	}

}