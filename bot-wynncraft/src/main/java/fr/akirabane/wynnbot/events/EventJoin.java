package fr.akirabane.wynnbot.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if(!event.getGuild().getId().equals("930410570912501792")) {
            return;
        }
        event.getGuild().getTextChannelById("967063091177210016").sendMessage(event.getMember().getAsMention() + " joined the guild!")
                .queue(res -> {
                    res.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("967071096786534510")).queue();
                });
    }
}
