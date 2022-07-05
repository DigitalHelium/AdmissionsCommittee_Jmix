package com.company.work.app;

import com.company.work.entity.Course;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import com.company.work.entity.StudentRating;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.querycondition.PropertyCondition;
import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class StudentService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private DataManager dataManager;


    public void addNewStudentToRating(){
        Student newStud = dataManager.load(Student.class).all().one();
        /*
        Student newStud = dataManager.load(Student.class)
                .query("SELECT s FROM Student s")
                .list().stream().findFirst().get();*/
        Set<DesiredCourse> desiredCourseList = newStud.getDesiredCourses();

        for(DesiredCourse dc:desiredCourseList){
            UUID cID = dc.getDesiredCourse().getId();
            Course c = dataManager.load(Course.class).id(cID).one();
            StudentRating studentRating = dataManager.load(StudentRating.class)
                            .query("SELECT sr FROM StudentRating sr Where sr.course = :course")
                            .parameter("course",c)
                            .one();

           studentRating.
        }
    }
}