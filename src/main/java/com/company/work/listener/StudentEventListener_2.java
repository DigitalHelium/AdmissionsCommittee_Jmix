package com.company.work.listener;

import com.company.work.app.RatingService;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Arrays;

@Component
public class StudentEventListener_2 {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentEventListener_2.class);
    @Autowired
    private DataManager dataManager;
    @Autowired
    private RatingService ratingService;

    @TransactionalEventListener
    public void onStudentChangedAfterCommit(EntityChangedEvent<Student> event) {

    }
}