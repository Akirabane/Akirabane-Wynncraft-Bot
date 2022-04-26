package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import fr.akirabane.wynnbot.lavaplayer.GuildMusicManager;
import fr.akirabane.wynnbot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Leave implements ICommand {
    @Override
    public String getName() {
        return "Leave";
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

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild());

        musicManager.scheduler.repeating = false;
        musicManager.scheduler.queue.clear();
        musicManager.audioPlayer.stopTrack();

        final AudioManager audioManager = messageReceivedEvent.getGuild().getAudioManager();
        audioManager.closeAudioConnection();

        messageReceivedEvent.getMessage().delete().complete();
        channel.sendMessage("Bot disconnected successfully.").queue();
    }
}
