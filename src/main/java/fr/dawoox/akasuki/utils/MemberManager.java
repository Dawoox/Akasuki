package fr.dawoox.akasuki.utils;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;

import java.time.Instant;

public class MemberManager {

    public static Instant getAccountCreationDate(Member member){
        return Snowflake.of(member.getId().toString().substring(10, 28)).getTimestamp();
    }

}
