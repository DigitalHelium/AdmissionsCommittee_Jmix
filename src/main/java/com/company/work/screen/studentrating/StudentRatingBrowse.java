package com.company.work.screen.studentrating;

import com.company.work.entity.Student;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.work.entity.StudentRating;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@UiController("StudentRating.browse")
@UiDescriptor("student-rating-browse.xml")
@LookupComponent("studentRatingsTable")
public class StudentRatingBrowse extends StandardLookup<StudentRating> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StudentRatingBrowse.class);
    @Autowired
    private CollectionContainer<Student> studentsDc;
    @Autowired
    private DataManager dataManager;

    @Subscribe("studentRatingsTable")
    public void onStudentRatingsTableSelection(Table.SelectionEvent<StudentRating> event) {
        StudentRating selectedRating= event.getSelected().iterator().next();
        log.warn("Course name = "+selectedRating.getCourse().getCourseName());

        studentsDc.setItems(selectedRating.getStudents());
    }

}