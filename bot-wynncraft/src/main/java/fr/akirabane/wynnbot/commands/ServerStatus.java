package fr.akirabane.wynnbot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ServerStatus implements ICommand {
    @Override
    public String getName() {
        return "ServerStatus";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {

        BufferedReader reader;
        String line;
        StringBuilder resData = new StringBuilder();

        HttpURLConnection co = null;
        try {
            URL url = new URL("https://mcapi.us/server/status?ip=play.wynncraft.com");
            co = (HttpURLConnection) url.openConnection();

            co.setRequestMethod("GET");
            co.setConnectTimeout(5000);
            co.setReadTimeout(5000);

            int resCode = co.getResponseCode();

            if(resCode > 299) {
                reader = new BufferedReader(new InputStreamReader(co.getErrorStream()));
                while((line = reader.readLine()) != null) {
                    resData.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(co.getInputStream()));
                while((line =reader.readLine()) != null) {
                    resData.append(line);
                }
                reader.close();
            }

            boolean isOnline = parseOnline(String.valueOf(resData));
            String maxPlayers = String.valueOf(parseMaxPlayers(String.valueOf(resData)));
            String nowPlayers = String.valueOf(parseNowPlayers(String.valueOf(resData)));
            String serverVersion = String.valueOf(parseServerVersion(String.valueOf(resData)));

            EmbedBuilder eb = new EmbedBuilder();
            Date date = new Date();

            int min = 0;
            int max = 255;
            int random_intA = (int)Math.floor(Math.random()*(max-min+1));
            int random_intB = (int)Math.floor(Math.random()*(max-min+1));
            int random_intC = (int)Math.floor(Math.random()*(max-min+1));

            eb.setTitle(serverVersion, null);
            eb.setColor(new Color(random_intA, random_intB, random_intC));
            if(isOnline) {
                eb.addField("Server status", "Online", true);
            } else {
                eb.addField("Server status", "offline", true);
            }
            eb.addField("Playing", nowPlayers + "/" + maxPlayers, true);
            eb.setAuthor(messageReceivedEvent.getMessage().getAuthor().getName());
            eb.setThumbnail("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
            eb.setImage("https://cdn.wynncraft.com/img/updates/1.20/y_logo_gr.png");
            eb.setFooter(date.toString());

            messageReceivedEvent.getMessage().getChannel().sendMessageEmbeds(eb.build()).queue();
            messageReceivedEvent.getMessage().delete().complete();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            co.disconnect();
        }

    }
    public static boolean parseOnline(String resBody) {
        JSONObject data = new JSONObject(resBody);
        boolean isOnline = data.getBoolean("online");
        return isOnline;
    }

    public static long parseMaxPlayers(String resBody){
        JSONObject data = new JSONObject(resBody);
        JSONObject data2 = data.getJSONObject("players");
        long maxPlayers = data2.getLong("max");
        return maxPlayers;
    }

    public static long parseNowPlayers(String resBody){
        JSONObject data = new JSONObject(resBody);
        JSONObject data2 = data.getJSONObject("players");
        long nowPlayers = data2.getLong("now");
        return nowPlayers;
    }

    public static String parseServerVersion(String resBody){
        JSONObject data = new JSONObject(resBody);
        JSONObject data2 = data.getJSONObject("server");
        String serverVersion = data2.getString("name");
        return serverVersion;
    }
}
