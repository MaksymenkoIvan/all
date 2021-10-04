package xyz.karpador.godfishbot.commands;

import org.telegram.telegrambots.api.objects.Message;

public abstract class Command {
    private boolean disabled = false;

    public boolean isDisabled() {
        return disabled;
    }

    public abstract String getName();

    public String getUsage() {
        return "/" + getName();
    }

    public abstract String getDescription();

    public abstract CommandResult getReply(String params, Message message, String myName);

    public void processSendResult(String mediaUrl, String mediaId) {
    }
}