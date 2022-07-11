package com.company.work.screen.studentrating;

import com.company.work.app.StudentService;
import com.company.work.entity.Course;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Slider;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.Timer;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.work.entity.StudentRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("StudentRating.browse")
@UiDescriptor("student-rating-browse.xml")
@LookupComponent("studentRatingsTable")
public class StudentRatingBrowse extends StandardLookup<StudentRating> {

    private static final Logger log = LoggerFactory.getLogger(StudentRatingBrowse.class);
    @Autowired
    private CollectionContainer<Student> studentsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Timer timer;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;
    private Course currentlySelectedCourse;
    private  StudentRating currentlySelectedRating;
    @Autowired
    private GroupTable<Student> studentRatingsTable1;
    @Autowired
    private StudentService studentService;
    @Autowired
    private Slider<Integer> timerDelaySlider;

    @Subscribe("studentRatingsTable")
    public void onStudentRatingsTableSelection(Table.SelectionEvent<StudentRating> event) {
        currentlySelectedRating= event.getSelected().iterator().next();
        if(currentlySelectedRating!=null) {
            log.warn("Course name = " + currentlySelectedRating.getCourse().getCourseName());
            currentlySelectedCourse = currentlySelectedRating.getCourse();

            studentsDc.setItems(currentlySelectedRating.getStudents());
        }


    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        timer.setRepeating(true);
        timer.start();
    }

    @Subscribe("timer")
    public void onTimerTimerAction(Timer.TimerActionEvent event) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(messages.getMessage(getClass(), "timerCaption"))
                .withDescription(event.getSource().getDelay() +" "+ messages.getMessage(getClass(), "timerDescription"))
                .show();
        studentService.setStudentsDcList(studentsDc,currentlySelectedCourse);
        timer.setDelay(timerDelaySlider.getValue()==null?5000:timerDelaySlider.getValue());

    }

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        timer.stop();
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(messages.getMessage(StudentRatingBrowse.class, "timerStoppedNotification"))
                .show();
    }
}