package com.xylope.sogobot.global.enum_type;

import com.xylope.sogobot.global.exception.UnknownDepartmentTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DepartmentType {
    GWANGJU("gsm.hs.kr", "광주"),
    DAEDEOK("dsm.hs.kr", "대덕"),
    DAEGU("dgsm.hs.kr", "대구"),
    BUSAN("bssm.hs.kr", "부산");

    private final String domain;
    private final String schoolNamePrefix;

    public static DepartmentType of(String departmentName) {
        DepartmentType departmentType = null;
        for (DepartmentType value : DepartmentType.values()) {
            if(value.name().equals(departmentName)) departmentType = value;
        }
        if(departmentType == null) throw new UnknownDepartmentTypeException(departmentName);
        return departmentType;
    }
}
