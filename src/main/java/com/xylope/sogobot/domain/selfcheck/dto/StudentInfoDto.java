package com.xylope.sogobot.domain.selfcheck.dto;

import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentInfoDto {
    private final String name;
    private final DepartmentType department;
    private final String birth;
    private final String password;
}
