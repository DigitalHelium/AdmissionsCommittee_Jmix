package com.company.work.screen.student;

import io.jmix.ui.screen.*;
import com.company.work.entity.Student;

@UiController("Student.browse")
@UiDescriptor("student-browse.xml")
@LookupComponent("studentsTable")
public class StudentBrowse extends StandardLookup<Student> {
}