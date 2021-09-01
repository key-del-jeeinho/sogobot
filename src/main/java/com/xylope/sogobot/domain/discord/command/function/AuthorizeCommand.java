package com.xylope.sogobot.domain.discord.command.function;

import com.xylope.sogobot.domain.authorize.exception.AlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.exception.DomainNotFoundException;
import com.xylope.sogobot.domain.authorize.exception.EmailAlreadyEnrolledException;
import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class AuthorizeCommand extends LeafCommand {
    private final UserAuthorizeService authorizeService;
    public AuthorizeCommand(String prefix, UserAuthorizeService authorizeService) {
        super(prefix);
        this.authorizeService = authorizeService;
    }

    @Override
    public void run(String[] args, User sender, TextChannel channel, int depth) {
        if(args.length < depth+2) {
            sendBadRequestMessage(channel, "이메일을 입력해주세요!");
            return;
        }
        UnauthorizedUserInfoDto dto = new UnauthorizedUserInfoDto(sender.getIdLong(), sender.getName(), args[depth+1]);
        try {
            authorizeService.authorize(dto);
        } catch (AlreadyEnrolledException e) {
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .addField(":confounded:  이런 욕심쟁이!", "이미 인증해놓고선 뭘 또 인증하려그러세요", false)
                    .setColor(new Color(252, 60, 60))
                    .setFooter("made by 지인호")
                    .build())
                    .complete();
            return;
        } catch (EmailAlreadyEnrolledException e) {
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .addField(":thinking:   혹시..아니죠?", "이미 그 이메일로 인증된 누군가가있는데...설마...아니죠?", false)
                    .setColor(new Color(252, 60, 60))
                    .setFooter("made by 지인호")
                    .build())
                    .complete();
            return;
        } catch (DomainNotFoundException e) {
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .addField(":sob:    이런! 학교이메일이아니에요!", "학교에서 발급한 이메일로 다시시도해주세요!", false)
                    .setColor(new Color(252, 60, 60))
                    .setFooter("made by 지인호")
                    .build())
                    .complete();
            return;
        }
        sendVerifyEmailMessage(channel, dto);
    }

    private void sendVerifyEmailMessage(TextChannel channel, UnauthorizedUserInfoDto dto) {
        MessageEmbed message = new EmbedBuilder()
                .addField(":white_check_mark: 이메일이 성공적으로 발송되었습니다!", dto.getEmail() + "메일을 확인해주세요!", false)
                .addField(":thinking: 이메일을 받지 못하셧나요?", "전체 메일함과 스팸메일함을 살펴주세요\n두 메일함에도 없다면 `관리자`에게 문의하세요", false)
                .setColor(new Color(88, 243, 21))
                .setFooter("made by 지인호")
                .build();

        channel.sendMessageEmbeds(message).complete();
    }

    private void sendBadRequestMessage(TextChannel channel, String reason) {
        MessageEmbed message = new EmbedBuilder()
                .addField(":warning: 잘못된 명령어입니다!", reason, false)
                .setColor(new Color(246, 56, 56))
                .setFooter("made by 지인호")
                .build();

        channel.sendMessageEmbeds(message).complete();
    }
}
