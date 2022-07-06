package com.company.work.screen.student;

import com.company.work.app.RatingService;
import com.company.work.app.StudentService;
import com.company.work.entity.ExamResults;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.work.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Student.edit")
@UiDescriptor("student-edit.xml")
@EditedEntityContainer("studentDc")
public class StudentEdit extends StandardEditor<Student> {
    @Autowired
    private Table<ExamResults> examResultsTable;
    @Autowired
    private TextField<Integer> scoreSumOfThreeSubjectsField;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private TextField<Integer> studentIDField;
    @Autowired
    private StudentService studentService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Student> event) {
        event.getEntity().setStudentID(studentService.getNewStudentID());
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        sumScores();

    }
    private void sumScores(){
        int sum=0;
        for (Object id:examResultsTable.getItems().getItemIds()){
            sum+=examResultsTable.getItems().getItem(id).getScore();
        }
        scoreSumOfThreeSubjectsField.setValue(sum);
    }
    
}