package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Clear implements ICommand {
    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {

        String[] args = messageReceivedEvent.getMessage().getContentRaw().split(" ");

        if(args.length != 2) {
            return;
        }

        if(!isNumber(args[1])) {
            messageReceivedEvent.getChannel().sendMessage("Command Usage: §Clear <amount>").queue();
            return;
        }

        List<Message> messageList = messageReceivedEvent.getChannel().getHistory().retrievePast(Integer.parseInt(args[1]) + 1).complete();
        messageReceivedEvent.getTextChannel().deleteMessages(messageList).queue();
        if(Integer.parseInt(args[1]) == 1) {
            messageReceivedEvent.getTextChannel().sendMessage("You have successfully cleared " + Integer.parseInt(args[1]) + " message !").queue();
            return;
        }
        messageReceivedEvent.getTextChannel().sendMessage("You have successfully cleared " + Integer.parseInt(args[1]) + " messages !").queue();
    }

    private boolean isNumber(String msg) {
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
