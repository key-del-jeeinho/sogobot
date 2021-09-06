package com.xylope.sogobot.domain.selfcheck.api;

import com.xylope.sogobot.domain.selfcheck.dto.StudentInfoDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DummySelfCheckAPI implements SelfCheckAPI {
    private final HashMap<StudentInfoDto, Boolean> students; //student, isEnrolled

    public DummySelfCheckAPI() {
        this.students = new HashMap<>();
    }

    public void addStudent(StudentInfoDto student) {
        students.put(student, false);
    }

    @Override
    public boolean validate(StudentInfoDto studentInfo) {
        return students.containsKey(studentInfo);
    }

    @Override
    public void enrollToMacro(StudentInfoDto studentInfo) {
        students.replace(studentInfo, true);
    }
}
