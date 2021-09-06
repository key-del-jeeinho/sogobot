package com.xylope.sogobot.domain.selfcheck.api;

import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;

public interface SelfCheckAPI {
    boolean validate(StudentInfoDto studentInfo);
    void enrollToMacro(StudentInfoDto studentInfo);
}
