package com.company.work.screen.desiredcourse;

import io.jmix.ui.screen.*;
import com.company.work.entity.DesiredCourse;

@UiController("DesiredCourse.edit")
@UiDescriptor("desired-course-edit.xml")
@EditedEntityContainer("desiredCourseDc")
public class DesiredCourseEdit extends StandardEditor<DesiredCourse> {
}