package com.xylope.sogobot.domain.discord.property;

import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String token;
    private String commandPrefix;
    private String adminEmail;
    private Long serverId;
    private List<DepartmentRole> roles;

    @Getter
    public static class DepartmentRole {
        private DepartmentType Department;
        @Setter
        private Long roleId;

        public void setDepartment(String department) {
            Department = DepartmentType.of(department);
        }
    }
}
