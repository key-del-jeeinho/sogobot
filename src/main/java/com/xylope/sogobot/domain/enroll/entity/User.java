package com.xylope.sogobot.domain.enroll.entity;

import com.xylope.sogobot.global.annotation.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private Long id;
    private String name;
    @Email
    private String email;
    @Department
    private String departmentType;
}
