package com.jagrosh.jmusicbot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CharCommand extends Command {

    private final static Logger LOG = LoggerFactory.getLogger("CharCommand");

    private static final String API_URL = "https://api.tibiadata.com/v4/character/";
    private OkHttpClient client = new OkHttpClient();

    public CharCommand(Bot bot) {
        this.name = "char";
        this.help = "Retrieve character info";
    }

    @Override
    protected void execute (CommandEvent event) {

        String charName = event.getArgs().trim();
        if (charName.isEmpty()) {
            event.reply("Please enter a character name");
            return;
        }

        String url = API_URL + charName;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e){
                event.reply("Failed to fetch character data");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    event.reply("Character not found or API error");
                    return;
                }

                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                JSONObject characterData = json.getJSONObject("character");
                JSONObject character = characterData.getJSONObject("character");

                String name = character.getString("name");
                String vocation = character.getString("vocation");
                int level = character.getInt("level");

                LOG.info("Retrieved info about character named: " + name);
                event.reply(String.format("Name: %s\nLevel: %d\nVocation: %s", name, level, vocation));
            }
        });
    }
}
