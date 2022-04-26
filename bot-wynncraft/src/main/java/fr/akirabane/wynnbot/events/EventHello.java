package fr.akirabane.wynnbot.events;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHello extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        MessageChannel channel = event.getChannel();
        String content = event.getMessage().getContentRaw();

        switch (content) {
            case "Hi":

            case "hi":

            case "Sup":

            case "sup":

            case "hey":

            case "Hey":
                event.getMessage().addReaction("\uD83D\uDC4B").queue();
                break;

            default:
                break;
        }
    }
}
