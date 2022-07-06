package com.company.work.screen.student;

import com.company.work.app.RatingService;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.work.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Student.browse")
@UiDescriptor("student-browse.xml")
@LookupComponent("studentsTable")
public class StudentBrowse extends StandardLookup<Student> {
    @Autowired
    private RatingService ratingService;

    @Subscribe("studentsTable.firstName")
    public void onStudentsTableFirstNameClick(Table.Column.ClickEvent<Student> event) {
        ratingService.addNewStudentToRating();
    }
}