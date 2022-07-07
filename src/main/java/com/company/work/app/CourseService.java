package com.company.work.app;

import com.company.work.entity.Course;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseService {
    @Autowired
    private DataManager dataManager;

    public int getNewCourseID(){
        try {
            Course newCourse = dataManager.load(Course.class)
                    .query("SELECT c FROM Course c WHERE c.courseID = (SELECT MAX(cr.courseID) FROM Course cr)")
                    .one();
            return newCourse.getCourseID() + 1;
        }
        catch (IllegalStateException e){
            return 1;
        }
    }
}