package com.xylope.sogobot.domain.enroll.dto;

import com.xylope.sogobot.domain.enroll.entity.User;
import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;
import com.xylope.sogobot.global.dto.AuthorizedUserDto;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private DepartmentType departmentType;
    private String dateOfBirth;
    private String password;


    public UserDto(Long id, String name, String email, DepartmentType departmentType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentType = departmentType;
    }

    public static UserDto byAuthorizeUserDto(AuthorizedUserDto dto) {
        return new UserDto(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getDepartmentType()
        );
    }

    public UserDto andStudentInfoDto(StudentInfoDto dto) {
        this.dateOfBirth = dto.getBirth();
        this.password = dto.getPassword();
        return this;
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
