package xyz.karpador.godfishbot.commands;

public class CommandResult {
    public String imageUrl = null;
    public String audioUrl = null;
    public String text = null;
    public boolean isGIF = false;
    public int replyToId = -1;
    public String mediaId = null;

    public CommandResult() {
    }

    public CommandResult(String text) {
        this.text = text;
    }
}