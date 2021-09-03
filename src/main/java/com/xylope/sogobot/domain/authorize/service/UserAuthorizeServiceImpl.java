package com.xylope.sogobot.domain.authorize.service;

import com.xylope.sogobot.domain.authorize.exception.AlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.exception.DomainNotFoundException;
import com.xylope.sogobot.domain.authorize.exception.EmailAlreadyEnrolledException;
import com.xylope.sogobot.domain.authorize.util.AuthorizeLinkGeneratorUtil;
import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import com.xylope.sogobot.global.dto.DomainAuthorizedUserInfoDto;
import com.xylope.sogobot.global.dto.UnauthorizedUserInfoDto;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import com.xylope.sogobot.infra.exception.EmailSendingFailureException;
import com.xylope.sogobot.infra.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class UserAuthorizeServiceImpl implements UserAuthorizeService {
    private final MailSenderService mailSenderService;
    private final SpringTemplateEngine templateEngine;
    private final AuthorizeLinkGeneratorUtil authorizeLinkGeneratorUtil;
    private final UserRepository userRepository;

    @Override
    public void authorize(UnauthorizedUserInfoDto info) {
        String email = info.getEmail();
        if(userRepository.existsById(info.getId()))
            throw new AlreadyEnrolledException(info);
        if(userRepository.existsByEmail(email))
            throw new EmailAlreadyEnrolledException(info);

        DepartmentType department;
        department = validateDomain(email);
        sendAuthorizeEmail(info, department);
    }

    private void sendAuthorizeEmail(UnauthorizedUserInfoDto info, DepartmentType department) {
        DomainAuthorizedUserInfoDto user = new DomainAuthorizedUserInfoDto(info, department);
        String link = authorizeLinkGeneratorUtil.generateLink(user);
        sendMail(link, info.getEmail(), info.getName());
    }

    private DepartmentType validateDomain(String email) {
        String domain = email.substring(email.indexOf("@") + 1);

        DepartmentType department = null;
        for (DepartmentType value : DepartmentType.values()) {
            if(domain.equals(value.getDomain()))
                department = value;
        } if(department == null) throw new DomainNotFoundException(domain);
        return department;
    }

    private void sendMail(String link, String email, String name) {
        String title = "[소고봇] 소마고 재학생 이신가요?";
        Context context = new Context();
        context.setVariable("user_name", name);
        context.setVariable("auth_link", link);

        String content = templateEngine.process("authorize-mail-template", context);

        try {
            mailSenderService.sendHtmlEmail(email, title, content);
        } catch (MessagingException e) {
            throw new EmailSendingFailureException(e);
        }
    }
}
