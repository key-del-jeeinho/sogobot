package com.xylope.sogobot.domain.selfcheck.exception;

import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class WrongStudentInfoException extends RuntimeException {
    private final StudentInfoDto studentInfoDto;
}
