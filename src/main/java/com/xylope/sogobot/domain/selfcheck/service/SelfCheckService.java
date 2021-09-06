package com.xylope.sogobot.domain.selfcheck.service;

import com.xylope.sogobot.domain.selfcheck.api.SelfCheckAPI;
import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;
import com.xylope.sogobot.domain.selfcheck.exception.WrongStudentInfoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SelfCheckService {
    private final SelfCheckAPI selfCheckAPI;

    public void enroll(StudentInfoDto info) {
        if(!selfCheckAPI.validate(info)) throw new WrongStudentInfoException(info);
        selfCheckAPI.enrollToMacro(info);
    }
}
