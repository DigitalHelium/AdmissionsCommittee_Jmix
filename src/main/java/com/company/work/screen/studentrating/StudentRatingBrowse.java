package com.company.work.screen.studentrating;

import io.jmix.ui.screen.*;
import com.company.work.entity.StudentRating;

@UiController("StudentRating.browse")
@UiDescriptor("student-rating-browse.xml")
@LookupComponent("studentRatingsTable")
public class StudentRatingBrowse extends StandardLookup<StudentRating> {
}