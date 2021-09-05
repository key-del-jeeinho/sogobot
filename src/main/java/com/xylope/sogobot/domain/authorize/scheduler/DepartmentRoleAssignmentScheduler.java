package com.xylope.sogobot.domain.authorize.scheduler;

import com.xylope.sogobot.domain.discord.manager.DiscordRoleManager;
import com.xylope.sogobot.domain.enroll.dto.UserDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentRoleAssignmentScheduler {
    private static final int userPool = 10;
    private final List<UserDto> userQueue;

    public DepartmentRoleAssignmentScheduler() {
        userQueue = new ArrayList<>();
    }

    @Scheduled(fixedDelay = 1000 * 60)
    public void assignmentRole() {
        if(userQueue.isEmpty()) return;
        for (int i = 0; i < userQueue.size(); i++) {
            if(i > userPool) break;
            UserDto user = userQueue.get(i);
            DiscordRoleManager.addDepartmentRole(user.getDepartmentType(), user.getId());
            userQueue.remove(user);
        }
    }

    public void addUser(UserDto user) {
        userQueue.add(user);
    }
}
