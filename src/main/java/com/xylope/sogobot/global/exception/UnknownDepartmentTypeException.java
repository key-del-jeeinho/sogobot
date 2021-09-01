package com.xylope.sogobot.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnknownDepartmentTypeException extends RuntimeException {
    private final String departmentName;
}
