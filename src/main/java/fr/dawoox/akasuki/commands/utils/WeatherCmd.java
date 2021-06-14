package fr.dawoox.akasuki.commands.utils;

import com.sun.tools.javac.util.List;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.utils.API.WeatherAPI;
import fr.dawoox.akasuki.utils.json.WeatherBody;

import java.time.Instant;

public class WeatherCmd extends BaseCmd {

    public WeatherCmd() {
        super(CommandCategory.UTILS, CommandPermission.USER, List.of("weather", "w", "meteo"));
    }

    @Override
    public void execute(Context context) {
        final String city = context.requireArg();

        WeatherBody weather = WeatherAPI.requestWeather(city);

        if (weather == null) {
            context.getChannel().createEmbed(embedCreateSpec -> {
                embedCreateSpec.setColor(Color.DEEP_LILAC)
                        .setDescription("Nah, this city does not seems to exist")
                        .setFooter("Akasuki", null)
                        .setTimestamp(Instant.now());
            }).block();
        } else {
            try {
                context.getChannel().createEmbed(embedCreateSpec -> {
                    embedCreateSpec.setColor(Color.DEEP_LILAC)
                            .setAuthor("Météo : " + city, null, "https:"+weather.current.condition.icon)
                            .setDescription(weather.current.condition.text)
                            .addField("Température", ":thermometer: " + String.valueOf(weather.current.temp_c) + "°C"
                                    +"\n :small_orange_diamond: " + String.valueOf(weather.current.feelslike_c) + "°C", true)
                            .addField("Informations", ":cloud_tornado: " + String.valueOf(weather.current.wind_kph) + " km/h"
                                    +"\n :droplet: " + String.valueOf(weather.current.humidity) + " %", true)
                            .setFooter("Akasuki / Powered by WeatherAPI.com", null)
                            .setTimestamp(Instant.now());
                }).block();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
