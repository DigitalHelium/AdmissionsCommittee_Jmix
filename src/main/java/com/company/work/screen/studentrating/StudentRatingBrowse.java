package com.company.work.screen.studentrating;

import com.company.work.entity.Course;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.Messages;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.Timer;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.work.entity.StudentRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    private Course curentlySelectedCourse;
    @Autowired
    private GroupTable<Student> studentRatingsTable1;

    @Subscribe("studentRatingsTable")
    public void onStudentRatingsTableSelection(Table.SelectionEvent<StudentRating> event) {
        StudentRating selectedRating= event.getSelected().iterator().next();
        log.warn("Course name = "+selectedRating.getCourse().getCourseName());
        curentlySelectedCourse=selectedRating.getCourse();

        studentsDc.setItems(selectedRating.getStudents());

        timer.setDelay(10000);
        timer.setRepeating(true);
        timer.start();
    }

    @Subscribe("timer")
    public void onTimerTimerAction(Timer.TimerActionEvent event) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(messages.getMessage(getClass(), "timerCaption"))
                .withDescription(event.getSource().getDelay() +" "+ messages.getMessage(getClass(), "timerDescription"))
                .show();
        setStudentsDcList();

    }

    private void setStudentsDcList(){
        studentsDc.getMutableItems().sort((o1, o2) -> {
            if(o1.getScoreSumOfThreeSubjects()>o2.getScoreSumOfThreeSubjects())
                return -1;
            if(o1.getScoreSumOfThreeSubjects()< o2.getScoreSumOfThreeSubjects())
                return 1;
            if(getPriority(o1)>getPriority(o2))
                return -1;
            if(getPriority(o1)<getPriority(o2))
                return 1;
            return 0;
        });
    }
    private int getPriority(Student stud){
        for(DesiredCourse dc:stud.getDesiredCourses())
            if(dc.getDesiredCourse()==curentlySelectedCourse)
                return dc.getPriority();
        return 0;
    }
}