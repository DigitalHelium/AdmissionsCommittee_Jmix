package com.company.work.screen.desiredcourse;

import io.jmix.core.DataManager;
import io.jmix.ui.screen.*;
import com.company.work.entity.DesiredCourse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@UiController("DesiredCourse.edit")
@UiDescriptor("desired-course-edit.xml")
@EditedEntityContainer("desiredCourseDc")
public class DesiredCourseEdit extends StandardEditor<DesiredCourse> {
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInitEntity(InitEntityEvent<DesiredCourse> event) {
        int priority = 1;
        Set<DesiredCourse> courses = event.getEntity().getStudent().getDesiredCourses();
        while(isCoursesContainsPriority(courses, priority))
            priority++;
        event.getEntity().setPriority(priority);
    }
    private boolean isCoursesContainsPriority(Set<DesiredCourse> courses, int priority){
        boolean isContains = false;
        for(DesiredCourse dc:courses)
            if (dc.getPriority() == priority) {
                isContains = true;
                break;
            }
        return  isContains;
    }

}