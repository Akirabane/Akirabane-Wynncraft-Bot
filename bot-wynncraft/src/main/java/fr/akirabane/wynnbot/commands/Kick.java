package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Kick implements ICommand {
    @Override
    public String getName() {
        return "Kick";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {

        final TextChannel channel = (TextChannel) messageReceivedEvent.getChannel();
        final Message message = messageReceivedEvent.getMessage();
        final Member member = messageReceivedEvent.getMember();
        final List<String> args = Arrays.asList(messageReceivedEvent.getMessage().getContentRaw().split(" "));

        if(args.size() < 3 ||message.getMentionedMembers().isEmpty()) {
            channel.sendMessage("Command usage: §Kick <Username> <Reason>").queue();
            return;
        }

        final Member target = message.getMentionedMembers().get(0);

        if(!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You don't have the permission to kick " + target.getAsMention()).queue();
            return;
        }

        final Member selfMember = messageReceivedEvent.getGuild().getSelfMember();

        if(!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("I can't kick " + target.getAsMention()).queue();
            return;
        }

        final String reason = String.join(" ", args.subList(1, args.size()));
        messageReceivedEvent.getGuild()
                .kick(target, reason)
                .reason(reason).queue(
                        (__) -> channel.sendMessage(target.getAsMention() + "has been kicked out from the server.").queue(),
                        (error) -> channel.sendMessageFormat("Could not kick %s", error.getMessage()).queue()
                );
        messageReceivedEvent.getMessage().delete().complete();
    }
}
