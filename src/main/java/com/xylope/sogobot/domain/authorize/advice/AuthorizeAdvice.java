package com.xylope.sogobot.domain.authorize.advice;

import com.xylope.sogobot.domain.authorize.exception.AlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.exception.DomainNotFoundException;
import com.xylope.sogobot.domain.authorize.exception.EmailAlreadyEnrolledException;
import com.xylope.sogobot.domain.discord.SogoBot;
import com.xylope.sogobot.infra.exception.EmailSendingFailureException;
import com.xylope.sogobot.infra.service.MailSenderService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice @Slf4j
@RequiredArgsConstructor
public class AuthorizeAdvice {
    private final SogoBot sogoBot;
    private final MailSenderService mailSenderService;
    private final SpringTemplateEngine templateEngine;

    @Value("${bot.admin-email")
    private String adminEmail;

    @ExceptionHandler(ExpiredJwtException.class)
    public String handleTokenExpiredError() {
        return "error/authorize-token-expired";
    }

    @ExceptionHandler(JwtException.class)
    public String handleJwtException() {
        return "error/wrong-token";
    }

    @ExceptionHandler(DomainNotFoundException.class)
    public void handleDomainNotFoundException(DomainNotFoundException e) {
        sogoBot.doWithJda(jda -> {
            Objects.requireNonNull(jda.getUserById(e.getInfo().getId()))
                    .openPrivateChannel()
                    .complete()
                    .sendMessageEmbeds(new EmbedBuilder()
                            .addField(":thinking:   이런 SW마이스터고도 있었나요??", "이메일의 도메인이 SW마이스터고 도메인이 아니에요", false)
                            .setColor(new Color(252, 60, 60))
                            .setFooter("made by 지인호")
                            .build())
                    .complete();
        });
    }

    @ExceptionHandler(Exception.class)
    public void unexpectedExceptionOccurred(Exception e) {
        log.warn("예상치못한 예외가 발생하였습니다!\n" + e.getLocalizedMessage());

        Context context = new Context();

        context.setVariable("exception-class", e.getClass().getSimpleName());
        context.setVariable("exception-reason", e.getLocalizedMessage());
        context.setVariable("exception-stack-trace", Arrays.toString(e.getStackTrace()));
        String content = templateEngine.process("fatal-error-mail-template", context);

        try {
        mailSenderService.sendHtmlEmail(adminEmail, "[소고봇] 오류가 발생하였습니다!", content);
        } catch (MessagingException ex) {
            throw new EmailSendingFailureException(ex);
        }
    }
}
