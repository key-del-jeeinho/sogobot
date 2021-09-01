package com.xylope.sogobot.global.dto;

import com.xylope.sogobot.domain.enroll.entity.User;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorizedUserDto {
    private final Long id;
    private final String name;
    private final String email;
    private final DepartmentType departmentType;

    public AuthorizedUserDto(DomainAuthorizedUserInfoDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.departmentType = dto.getDepartmentType();
    }

    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setDepartmentType(departmentType.name());

        return user;
    }
}
