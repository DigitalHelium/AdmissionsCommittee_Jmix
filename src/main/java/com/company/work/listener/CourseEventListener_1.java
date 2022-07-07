package com.company.work.listener;

import com.company.work.app.RatingService;
import com.company.work.entity.Course;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CourseEventListener_1 {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CourseEventListener_1.class);
    @Autowired
    private DataManager dataManager;
    @Autowired
    private RatingService ratingService;
    @EventListener
    public void onCourseChangedBeforeCommit(EntityChangedEvent<Course> event) {
        log.warn("Course Added or Changed!!!");
        try {
            ratingService.addNewCourseToRating(dataManager.load(Course.class).id(event.getEntityId()).one());
        }
        catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

        log.warn("Course Added or Changed!!!1");
    }
}