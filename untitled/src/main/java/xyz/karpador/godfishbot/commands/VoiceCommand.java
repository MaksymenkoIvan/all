package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;

public class VoiceCommand extends Command {

	private final String name, fileName;
	private String mediaId = null;

	public VoiceCommand(String cmdName, String fileName) {
		name = cmdName;
		this.fileName = fileName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return "Получи голосовое сообщение";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		CommandResult result = new CommandResult();
		result.audioUrl = "/audio/" + fileName;
		result.mediaId = mediaId;
		return result;
	}

	@Override
	public void processSendResult(String audioUrl, String mediaId) {
		this.mediaId = mediaId;
	}

}