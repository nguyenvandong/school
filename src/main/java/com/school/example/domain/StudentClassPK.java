package com.school.example.domain;

import java.io.Serializable;

/**
 * Created by DONG on 6/15/2018.
 */
public class StudentClassPK implements Serializable {

    private Long studentId;
    private Long classId;

    public StudentClassPK() {
    }

    public StudentClassPK(Long studentId, Long classId) {
        this.studentId = studentId;
        this.classId = classId;
    }
}
