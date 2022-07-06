package com.company.work.app;

import com.company.work.entity.Course;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import com.company.work.entity.StudentRating;
import io.jmix.core.DataManager;
import io.jmix.core.Entity;
import io.jmix.core.FetchPlan;
import io.jmix.core.SaveContext;
import io.jmix.core.querycondition.PropertyCondition;
import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StudentService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private DataManager dataManager;


    public void addNewStudentToRating(){
        Student newStud = dataManager.load(Student.class)
                .query("SELECT s FROM Student s WHERE s.studentID = (SELECT MIN(st.studentID) FROM Student st)")
                .one();
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
}