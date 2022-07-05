package com.company.work.screen.examresults;

import io.jmix.ui.screen.*;
import com.company.work.entity.ExamResults;

@UiController("ExamResults.edit")
@UiDescriptor("exam-results-edit.xml")
@EditedEntityContainer("examResultsDc")
public class ExamResultsEdit extends StandardEditor<ExamResults> {

}