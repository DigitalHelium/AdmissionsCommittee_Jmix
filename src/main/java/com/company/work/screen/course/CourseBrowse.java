package com.company.work.screen.course;

import io.jmix.ui.screen.*;
import com.company.work.entity.Course;

@UiController("Course.browse")
@UiDescriptor("course-browse.xml")
@LookupComponent("coursesTable")
public class CourseBrowse extends StandardLookup<Course> {
}