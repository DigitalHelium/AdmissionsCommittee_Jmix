package com.company.work.app;

import com.company.work.entity.Course;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import com.company.work.entity.StudentRating;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Component
public class RatingService {
    private static final Logger log = LoggerFactory.getLogger(RatingService.class);
    @Autowired
    private DataManager dataManager;

    public void addNewStudentToRating(Student newStud){
        log.warn(newStud.getLastName());
        Set<DesiredCourse> desiredCourseList = newStud.getDesiredCourses();
        log.warn("courses size = "+desiredCourseList.size());

        for(DesiredCourse dc:desiredCourseList){
            UUID cID = dc.getDesiredCourse().getId();
            log.warn("course UUID = "+cID);
            Course c = dataManager.load(Course.class).id(cID).one();
            log.warn("course Name = "+c.getCourseName());
            StudentRating studentRating = dataManager.load(StudentRating.class)
                            .query("SELECT sr FROM StudentRating sr Where sr.course = :course")
                            .parameter("course",c)
                            .fetchPlan(fpb->fpb.addFetchPlan(FetchPlan.BASE).add("students"))
                            .one();
            log.warn("students size = "+studentRating.getStudents().size());
            Set<Student> students =studentRating.getStudents();
            students.add(newStud);
            studentRating.setStudents(students);
            log.warn("students size NEW = "+studentRating.getStudents().size());
            log.warn("isNewStudInList= "+studentRating.getStudents().contains(newStud));
            dataManager.save(studentRating);
        }
    }
    public void addNewCourseToRating(Course newCourse){
        log.warn("Course ID = "+newCourse.getId());
        log.warn("Course Name = "+newCourse.getCourseName());
        StudentRating newRating = dataManager.create(StudentRating.class);
        newRating.setCourse(newCourse);
        log.warn("Rating Name = "+newRating.getId());
        dataManager.save(newRating);
    }
}