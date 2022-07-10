package com.company.work.screen.student;

import com.company.work.app.RatingService;
import com.company.work.app.StudentService;
import com.company.work.entity.DesiredCourse;
import com.company.work.entity.ExamResults;
import io.jmix.core.Messages;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.DialogAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionContainer;
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
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private Button examResultsTableCreateButton;
    @Autowired
    private Button desiredCourseTableCreateButton;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Student> event) {
        event.getEntity().setStudentID(studentService.getNewStudentID());
    }


    @Subscribe(id = "examResultsDc", target = Target.DATA_CONTAINER)
    public void onExamResultsDcCollectionChange(CollectionContainer.CollectionChangeEvent<ExamResults> event) {
        if(getEditedEntity().getExamResults()!=null) {
            getEditedEntity().setScoreSumOfThreeSubjects(sumScores(getEditedEntity()));
            examResultsTableCreateButton.setEnabled(!isThreeExams(getEditedEntity()));
        }
    }

    @Subscribe(id = "desiredCoursesDc", target = Target.DATA_CONTAINER)
    public void onDesiredCoursesDcCollectionChange(CollectionContainer.CollectionChangeEvent<DesiredCourse> event) {
        if(getEditedEntity().getDesiredCourses()!=null) {
            desiredCourseTableCreateButton.setEnabled(!isThreeCourses(getEditedEntity()));
        }
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if(checkIfLowerThanPassRate(getEditedEntity())){
            event.preventCommit();
            dialogs.createOptionDialog()
                    .withCaption(messages.getMessage(getClass(), "passRateCheckDialogCaption"))
                    .withMessage(messages.getMessage(getClass(), "passRateCheck"))
                    .withActions(
                            new DialogAction(DialogAction.Type.OK, Action.Status.PRIMARY)
                                    .withHandler(e->event.resume()),
                            new DialogAction(DialogAction.Type.NO)
                    )
                    .show();
        }
    }
     private boolean isThreeExams(Student stud){
        return stud.getExamResults().size()>=3;
    }
    private boolean isThreeCourses(Student stud){
        return stud.getDesiredCourses().size()>=3;
    }

    private int sumScores(Student stud){
        int sum=0;
        for (ExamResults ex:stud.getExamResults()){
            sum+=ex.getScore();
        }
        return sum;
    }
    private boolean checkIfLowerThanPassRate(Student stud){
        boolean isLowerThenPassRate = false;
        for(DesiredCourse dc:stud.getDesiredCourses()){
            if (stud.getScoreSumOfThreeSubjects() < dc.getDesiredCourse().getPassRate()) {
                isLowerThenPassRate = true;
                break;
            }
        }
        return  isLowerThenPassRate;

    }
    
}