package com.company.work.listener;

import com.company.work.app.RatingService;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener_1 {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentEventListener_1.class);
    @Autowired
    private RatingService ratingService;
    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onStudentChangedBeforeCommit(EntityChangedEvent<Student> event) {
        log.warn("Student Added or Changed!!!");
        try {
            ratingService.addNewStudentToRating(dataManager.load(Student.class).id(event.getEntityId()).one());
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        log.warn("Student Added or Changed!!!AFTER");
    }
}