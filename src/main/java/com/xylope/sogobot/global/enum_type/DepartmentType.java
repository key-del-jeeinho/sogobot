package com.xylope.sogobot.global.enum_type;

import com.xylope.sogobot.global.exception.UnknownDepartmentTypeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum DepartmentType {
    GWANGJU("gsm.hs.kr", "광주", 882461583882735666L),
    DAEDEOK("dsm.hs.kr", "대덕", 882461611011493921L),
    DAEGU("dgsm.hs.kr", "대구", 882461652577050634L),
    BUSAN("bssm.hs.kr", "부산", 882461666854449232L);

    private final String domain;
    private final String schoolNamePrefix;
    private final Long roleId;

    public static DepartmentType of(String departmentName) {
        DepartmentType departmentType = null;
        for (DepartmentType value : DepartmentType.values()) {
            if(value.name().equals(departmentName)) departmentType = value;
        }
        if(departmentType == null) throw new UnknownDepartmentTypeException(departmentName);
        return departmentType;
    }
}
