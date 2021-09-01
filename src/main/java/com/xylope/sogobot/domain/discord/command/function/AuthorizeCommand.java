package com.xylope.sogobot.domain.discord.command.function;

import com.xylope.sogobot.domain.authorize.controller.AuthorizeController;
import com.xylope.sogobot.domain.discord.command.LeafCommand;
import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import com.xylope.sogobot.domain.authorize.service.UserAuthorizeService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class AuthorizeCommand extends LeafCommand {
    private final AuthorizeController authorizeController;
    public AuthorizeCommand(String prefix, AuthorizeController authorizeController) {
        super(prefix);
        this.authorizeController = authorizeController;
    }

    @Override
    public void run(String[] args, User sender, TextChannel channel, int depth) {
        if(args.length < depth+2) {
            sendBadRequestMessage(channel, "이메일을 입력해주세요!");
            return;
        }

        //와 씨... 나천잰가
        //TODO 2021.08.31 | 이후 로직을 AuthorizeController 에 위임한다 | 지인호
        // 이러면 exception handler 또한 사용할 수있고 두개의 컨트롤러를 수평이아닌 수직적으로 연결할 수 있다!!
        UnauthorizedUserInfoDto dto = new UnauthorizedUserInfoDto(sender.getIdLong(), sender.getName(), args[depth+1]);
        authorizeController.startAuthorize(dto);
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
