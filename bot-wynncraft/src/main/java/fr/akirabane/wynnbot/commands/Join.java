package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Join implements ICommand {
    @Override
    public String getName() {
        return "Join";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        final TextChannel channel = (TextChannel) messageReceivedEvent.getChannel();
        final Member self = messageReceivedEvent.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if(selfVoiceState.inAudioChannel()) {
            channel.sendMessage("I'm already in a voice channel").queue();
            return;
        }

        final Member member = messageReceivedEvent.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("You need to be in a voice channel to run the music.").queue();
            return;
        }

        final AudioManager audioManager = messageReceivedEvent.getGuild().getAudioManager();
        final VoiceChannel memberChannel = (VoiceChannel) memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
        messageReceivedEvent.getMessage().delete().complete();
    }
}
