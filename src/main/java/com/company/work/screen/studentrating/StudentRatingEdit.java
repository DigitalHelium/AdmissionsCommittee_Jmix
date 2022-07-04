package com.company.work.screen.studentrating;

import io.jmix.ui.screen.*;
import com.company.work.entity.StudentRating;

@UiController("StudentRating.edit")
@UiDescriptor("student-rating-edit.xml")
@EditedEntityContainer("studentRatingDc")
public class StudentRatingEdit extends StandardEditor<StudentRating> {
}