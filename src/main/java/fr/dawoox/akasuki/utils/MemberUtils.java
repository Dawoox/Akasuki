package fr.dawoox.akasuki.utils;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;

import java.time.Instant;

/**
 * Util class to help to mange Member
 * @author Dawoox
 * @version 1.0.0
 */
public class MemberUtils {

    /**
     * Get the Member creation date
     * @param member
     * The member
     * @return Instant
     * Instant where the account was created
     * @since 1.0.0
     */
    public static Instant getAccountCreationDate(Member member){
        return Snowflake.of(member.getId().toString().substring(10, 28)).getTimestamp();
    }

}
