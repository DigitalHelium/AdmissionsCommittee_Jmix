package com.company.work.screen.examsubjects;

import io.jmix.ui.screen.*;
import com.company.work.entity.ExamSubjects;

@UiController("ExamSubjects.edit")
@UiDescriptor("exam-subjects-edit.xml")
@EditedEntityContainer("examSubjectsDc")
public class ExamSubjectsEdit extends StandardEditor<ExamSubjects> {
}