package fr.dawoox.akasuki.utils;

import discord4j.common.util.Snowflake;

public enum EmbedActivities {

    YOUTUBE_TOGETHER(Snowflake.of("880218394199220334")),
    WATCH_TOGETHER_DEV(Snowflake.of("880218832743055411")),
    FISHINGTON(Snowflake.of("814288819477020702")),
    CHESS_IN_THE_PARK(Snowflake.of("832012774040141894")),
    CHESS_IN_THE_PARK_DEV(Snowflake.of("832012586023256104")),
    BETRAYAL(Snowflake.of("773336526917861400")),
    DOODLECREW(Snowflake.of("878067389634314250")),
    WORDSNACKS(Snowflake.of("879863976006127627")),
    LETTERTILE(Snowflake.of("879863686565621790")),
    POKER_NIGHT(Snowflake.of("755827207812677713"));

    private final Snowflake ActivitySnowflake;

    EmbedActivities(Snowflake ActivitySnowflake) {
        this.ActivitySnowflake = ActivitySnowflake;
    }

    @Override
    public String toString() {
        return ActivitySnowflake.asString();
    }

}
