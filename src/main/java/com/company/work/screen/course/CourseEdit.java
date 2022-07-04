package com.company.work.screen.course;

import io.jmix.ui.screen.*;
import com.company.work.entity.Course;

@UiController("Course.edit")
@UiDescriptor("course-edit.xml")
@EditedEntityContainer("courseDc")
public class CourseEdit extends StandardEditor<Course> {
}