package com.xylope.sogobot.global.dto;

import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainAuthorizedUserInfoDto {
    private final Long id;
    private final String name;
    private final String email;
    private final DepartmentType departmentType;

    public DomainAuthorizedUserInfoDto(UnauthorizedUserInfoDto dto, DepartmentType departmentType) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.departmentType = departmentType;
    }
}
