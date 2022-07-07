package com.company.work.screen.course;

import com.company.work.app.CourseService;
import io.jmix.ui.screen.*;
import com.company.work.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Course.edit")
@UiDescriptor("course-edit.xml")
@EditedEntityContainer("courseDc")
public class CourseEdit extends StandardEditor<Course> {
    @Autowired
    private CourseService courseService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Course> event) {
        event.getEntity().setCourseID(courseService.getNewCourseID());
    }

}