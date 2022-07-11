package com.company.work.listener;

import com.company.work.app.RatingService;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentEventListener_1 {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentEventListener_1.class);
    @Autowired
    private RatingService ratingService;
    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onStudentChangedBeforeCommit(EntityChangedEvent<Student> event) {

        try {
            Student currentStud = dataManager.load(Student.class).
                    id(event.getEntityId())
                    .fetchPlan(fpb->fpb.addFetchPlan(FetchPlan.BASE).add("desiredCourses"))
                    .one();
            if(event.getType() == EntityChangedEvent.Type.CREATED) {
                log.warn("Student Added!");
                ratingService.addNewStudentToRating(currentStud);

            }else
                if(event.getType() == EntityChangedEvent.Type.UPDATED && event.getChanges().isChanged("desiredCourses")){
                log.warn("Student Updated!");
                ratingService.removeStudentFromRating(currentStud);
                ratingService.addNewStudentToRating(currentStud);
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        log.warn("Student Event!!!AFTER");
    }
}