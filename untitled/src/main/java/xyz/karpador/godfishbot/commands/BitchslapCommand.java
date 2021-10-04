package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;

public class BitchslapCommand extends Command {

	private String mediaId = null;

	@Override
	public String getName() {
		return "slap";
	}

	@Override
	public String getUsage() {
		return "/slap [target]";
	}

	@Override
	public String getDescription() {
		return "Шлёпни кого-то";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		CommandResult result = new CommandResult();

		if (message.getReplyToMessage() != null) {
			result.replyToId = message.getReplyToMessage().getMessageId();
			result.imageUrl = "/images/bitchslap.gif";
			if(mediaId != null)
				result.mediaId = mediaId;
			result.isGIF = true;
		} else {
			if (params != null) {
				result.text =
					message.getFrom().getFirstName()
						+ " шлёпнул " + params;
			} else {
				result.text =
					message.getFrom().getFirstName()
						+ "был шлёпнут ботом =)";
			}
		}
		return result;
	}

	@Override
	public void processSendResult(String mediaUrl, String mediaId) {
		if (this.mediaId == null)
			this.mediaId = mediaId;
	}
}