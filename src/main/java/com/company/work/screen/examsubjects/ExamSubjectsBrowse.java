package com.company.work.screen.examsubjects;

import io.jmix.ui.screen.*;
import com.company.work.entity.ExamSubjects;

@UiController("ExamSubjects.browse")
@UiDescriptor("exam-subjects-browse.xml")
@LookupComponent("examSubjectsesTable")
public class ExamSubjectsBrowse extends StandardLookup<ExamSubjects> {
}