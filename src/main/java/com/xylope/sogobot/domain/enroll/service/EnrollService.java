package com.xylope.sogobot.domain.enroll.service;

import com.xylope.sogobot.domain.authorize.exception.AlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.scheduler.DepartmentRoleAssignmentScheduler;
import com.xylope.sogobot.domain.discord.manager.DiscordMessageManager;
import com.xylope.sogobot.domain.discord.property.MessageProperties;
import com.xylope.sogobot.domain.enroll.dto.UserDto;
import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class EnrollService {
    private final MessageProperties properties;
    private final UserRepository userRepository;
    private final DepartmentRoleAssignmentScheduler roleAssignmentScheduler;

    public void enrollUser(UserDto user) {
        if(userRepository.existsById(user.getId())) throw new AlreadyEnrolledException();
        userRepository.save(user.toEntity());
        roleAssignmentScheduler.addUser(user);
        sendEnrollSuccessMessage(user);
    }

    private void sendEnrollSuccessMessage(UserDto user) {
        MessageEmbed message = new EmbedBuilder()
                .addField("인증이 완료됬어요!", "이제 소마고만의 여러 컨텐츠를 이용할 수 있습니다!", false)
                .setDescription("1분이 지나도 역할이 부여되지 않으면 관리자에게 문의해주세요")
                .setColor(properties.getInfo().getColor())
                .setFooter(properties.getInfo().getFooter())
                .build();
        DiscordMessageManager.sendPrivateMessage(user.getId(), message);
    }
}
