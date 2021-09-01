package com.xylope.sogobot.domain.enroll.service;

import com.xylope.sogobot.domain.enroll.repository.UserRepository;
import com.xylope.sogobot.global.dto.AuthorizedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class EnrollService {
    private final UserRepository userRepository;
    public void enrollUser(AuthorizedUserDto user) {
        userRepository.save(user.toEntity());
    }
}
