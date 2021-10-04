package xyz.karpador.godfishbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import xyz.karpador.godfishbot.commands.*;

public class GodfishPollingBot extends TelegramLongPollingBot {

	public static final Command[] COMMANDS = {
		new HelpCommand(), new HelloCommand(), new KissCommand(), new SexAllCommand(),
		new HugCommand(), new BitchslapCommand(), new SexCommand(), new LohCommand(),
		new VoiceCommand("listen", "gromche.mp3"), new RankLoveCommand(),
		new ImageCommand("samec", "/images/samec.jpg", true),
		new ImageCommand("kolya", "/images/kolya.jpg", true),
		new ImageCommand("telochka", "/images/telochka.jpg", true),
	};

	private final HashMap<String, Command> commands;
	private String myName = null;

	public GodfishPollingBot() {
		commands = new HashMap<>();
		for (Command cmd : COMMANDS)
			commands.put(cmd.getName(), cmd);
		if (BotConfig.getInstance().getAlphacodersToken().startsWith("<")
			|| BotConfig.getInstance().getAlphacodersToken().isEmpty()) {
		}
		if (BotConfig.getInstance().getPixabayToken().startsWith("<")
			|| BotConfig.getInstance().getPixabayToken().isEmpty()) {
		}
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (myName == null) {
			try {
				myName = getMe().getFirstName();
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		if (update.hasMessage() && update.getMessage().hasText()) {
			String msgTxt = update.getMessage().getText();
			if (msgTxt.startsWith("/")) {
				int spaceIndex = msgTxt.indexOf(" ");
				String cmdName;
				if (spaceIndex > 0)
					cmdName = msgTxt.substring(1, spaceIndex);
				else
					cmdName = msgTxt.substring(1);
				if (cmdName.contains("@")) {
					String[] cmdParts = cmdName.split("@");
					if (!cmdParts[1].equalsIgnoreCase(getBotUsername()))
						return;
					cmdName = cmdParts[0];
				}
				String params = null;
				if (spaceIndex > 0)
					params = msgTxt.substring(spaceIndex + 1);

				Command cmd = commands.get(cmdName.toLowerCase());
				if (cmd != null) {
					runCommand(cmd, params, update.getMessage());
				}
			}
		}
	}

	private InputStream getImageInputStream(String imageUrl) throws IOException {
		InputStream stream;
		if (!imageUrl.startsWith("http"))
			stream = getClass().getResource(imageUrl).openStream();
		else
			stream = new URL(imageUrl).openStream();
		return stream;
	}

	private void runCommand(Command cmd, String params, Message msg) {
		CommandResult result = cmd.getReply(params, msg, myName);
		if (result != null) {
			if (result.imageUrl != null) {
				// It's an image
				try {
					if (result.isGIF) {
						SendDocument document = new SendDocument()
							.setChatId(msg.getChatId());
							//.setNewDocument("blub.gif", stream);
						if(result.mediaId == null) {
							document.setNewDocument(
								"blub.gif",
								getImageInputStream(result.imageUrl)
							);
						} else {
							document.setDocument(result.mediaId);
						}
						if (result.text != null)
							document.setCaption(result.text);
						if (result.replyToId != -1)
							document.setReplyToMessageId(result.replyToId);
						cmd.processSendResult(result.imageUrl, sendDocument(document).getDocument().getFileId());
					} else {
						SendPhoto photo = new SendPhoto()
							.setChatId(msg.getChatId());
						if(result.mediaId == null) {
							photo.setNewPhoto("photo", getImageInputStream(result.imageUrl));
						} else {
							photo.setPhoto(result.mediaId);
						}
						if (result.text != null)
							photo.setCaption(result.text);
						if (result.replyToId != -1)
							photo.setReplyToMessageId(result.replyToId);
						cmd.processSendResult(result.imageUrl, sendPhoto(photo).getPhoto().get(0).getFileId());
					}
				} catch (IOException | TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (result.audioUrl != null) {
				try {
					SendVoice voice = new SendVoice()
						.setChatId(msg.getChatId());
					if (result.mediaId == null) {
						InputStream stream =
							getClass().getResource(result.audioUrl).openStream();
						voice.setNewVoice("audio.ogg", stream);
					} else {
						voice.setVoice(result.mediaId);
					}
					if (result.text != null)
						voice.setCaption(result.text);
					if (result.replyToId != -1)
						voice.setReplyToMessageId(result.replyToId);
					cmd.processSendResult(result.audioUrl, sendVoice(voice).getVoice().getFileId());
				} catch (IOException | TelegramApiException e) {
					e.printStackTrace();
				}
			} else {
				// It's a text message
				SendMessage message = new SendMessage()
					.setChatId(msg.getChatId())
					.setText(result.text);
				if (result.replyToId != -1)
					message.setReplyToMessageId(result.replyToId);
				try {
					sendMessage(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getBotToken() {
		return BotConfig.getInstance().getTelegramBotToken();
	}

	@Override
	public String getBotUsername() {
		return "godfishbot";
	}

}