package com.xylope.sogobot.domain.discord.command.function;

import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.domain.discord.property.MessageProperties;
import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;
import com.xylope.sogobot.domain.selfcheck.exception.WrongStudentInfoException;
import com.xylope.sogobot.domain.selfcheck.service.SelfCheckService;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class SelfCheckEnrollCommand extends LeafCommand {
    private final MessageProperties messageProperties;
    private final UserRepository userRepository;
    private final SelfCheckService selfCheckService;

    public SelfCheckEnrollCommand(String prefix, MessageProperties messageProperties, UserRepository userRepository, SelfCheckService selfCheckService) {
        super(prefix);
        this.messageProperties = messageProperties;
        this.userRepository = userRepository;
        this.selfCheckService = selfCheckService;
    }

    @Override
    public void run(String[] args, User sender, MessageChannel channel, int depth) {
        if(!(channel instanceof PrivateChannel)) return;
        if(!(args.length > depth+3)) {//num of input args = 3
            sendBadRequestMessage(channel, "명령어의 인자값을 입력해주세요! (소고야 진단등록 <이름> <생일> <비밀번호>");
            return;
        }

        if(!userRepository.existsById(sender.getIdLong())) {
            sendBadRequestMessage(channel, "회원인증 먼저 해주세요! `소고야 인증`");
        }
        String department = userRepository.getById(sender.getIdLong()).getDepartmentType();
        StudentInfoDto info = new StudentInfoDto(args[depth + 1], DepartmentType.of(department), args[depth + 2], args[depth + 3]);
        try {
            selfCheckService.enroll(info);
        } catch (WrongStudentInfoException e) {
            sendStudentNotFoundMessage(channel);
        }
    }

    private void sendStudentNotFoundMessage(MessageChannel channel) {
        MessageEmbed message = new EmbedBuilder()
                .addField(":warning: 해당 정보에 맞는 학생을 찾을 수 없습니다!", "학생정보를 확인하고 다시시도해주세요", false)
                .setColor(messageProperties.getError().getColor())
                .setFooter(messageProperties.getError().getFooter())
                .build();

        channel.sendMessageEmbeds(message).complete();
    }

    private void sendBadRequestMessage(MessageChannel channel, String reason) {
        MessageEmbed message = new EmbedBuilder()
                .addField(":warning: 잘못된 명령어입니다!", reason, false)
                .setColor(new Color(messageProperties.getError().getColor()))
                .setFooter(messageProperties.getError().getFooter())
                .build();

        channel.sendMessageEmbeds(message).complete();
    }
}
