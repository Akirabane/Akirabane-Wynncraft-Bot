package fr.akirabane.wynnbot.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventLeave extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        if(!event.getGuild().getId().equals("930410570912501792")) {
            return;
        }
        event.getGuild().getTextChannelById("967063091177210016").sendMessage(event.getMember().getAsMention() + " left the guild!").queue();
    }
}
