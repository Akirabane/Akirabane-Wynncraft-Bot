package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import fr.akirabane.wynnbot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Play implements ICommand {
    @Override
    public String getName() {
        return "Play";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        final TextChannel channel = (TextChannel) messageReceivedEvent.getChannel();
        final Member self = messageReceivedEvent.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()) {
            channel.sendMessage("I need to be in a voice channel to play music.").queue();
            return;
        }

        List<String> args = Arrays.asList(messageReceivedEvent.getMessage().getContentRaw().split(" "));

        if(args.size() != 2) {
            return;
        }

        if(args.size() < 2) {
            messageReceivedEvent.getChannel().sendMessage("Command Usage: §Play <Youtube link | Music name>").queue();
            return;
        }
        final Member member = messageReceivedEvent.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("You need to be in a voice channel to play music.").queue();
            return;
        }

        if(!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel to play music.").queue();
            return;
        }

        String link = String.join(" ", args);

        if(!isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(channel, link);
        messageReceivedEvent.getMessage().delete().complete();
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
