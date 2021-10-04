package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;
import xyz.karpador.godfishbot.GodfishPollingBot;

public class HelpCommand extends Command {

	private static final String USAGE = "Available commands:\n%s";

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getUsage() {
		return "/help [command]";
	}

	@Override
	public String getDescription() {
		return "Get a list of commands (or more info about one command)";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		if (params == null) {
			StringBuilder commands = new StringBuilder();
			for (Command cmd : GodfishPollingBot.COMMANDS) {
				commands.append(cmd.getUsage());
				if (cmd.isDisabled()) commands.append(" (DISABLED)");
				commands.append("\n");
			}
			return new CommandResult(String.format(USAGE, commands.toString()));
		} else {
			for (Command cmd : GodfishPollingBot.COMMANDS) {
				if (cmd.getName().equals(params))
					return new CommandResult(cmd.getUsage() + "\n" + cmd.getDescription());
			}
			return null;
		}
	}

}