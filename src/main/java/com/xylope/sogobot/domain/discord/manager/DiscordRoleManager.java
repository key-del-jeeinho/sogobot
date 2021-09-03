package com.xylope.sogobot.domain.discord.manager;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class DiscordRoleManager {
    @Setter
    private static SogoBot sogoBot;
    @Value("${bot.server-id}")
    private static String serverId = "881868890366431283";
    public static void addDepartmentRole(DepartmentType departmentType, Long id) {
        sogoBot.doWithJda(jda -> jda.getGuildById(serverId)
                .addRoleToMember(id, jda.getRoleById(departmentType.getRoleId())).complete());
    }
}
