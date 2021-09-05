package com.xylope.sogobot.domain.discord.manager;

import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DiscordRoleManager {
    @Setter
    private static SogoBot sogoBot;
    @Value("${bot.server-id}")
    private static String serverId = "881868890366431283";
    public static void addDepartmentRole(DepartmentType departmentType, Long id) {
        sogoBot.doWithJda(jda -> {
            Guild guild = Objects.requireNonNull(jda.getGuildById(serverId));
            Role role = Objects.requireNonNull(jda.getRoleById(departmentType.getRoleId()));

            if(guild.isMember(Objects.requireNonNull(jda.getUserById(id)))) guild
                    .addRoleToMember(id, role).complete();
        });
    }
}
