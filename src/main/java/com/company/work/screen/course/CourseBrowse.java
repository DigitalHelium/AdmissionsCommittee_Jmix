package com.company.work.screen.course;

import com.company.work.app.RatingService;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.work.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Course.browse")
@UiDescriptor("course-browse.xml")
@LookupComponent("coursesTable")
public class CourseBrowse extends StandardLookup<Course> {
    @Autowired
    private RatingService ratingService;

    @Subscribe("coursesTable.courseName")
    public void onCoursesTableCourseNameClick(Table.Column.ClickEvent<Course> event) {
       // ratingService.addNewCourseToRating();
    }
}