package com.company.work.app;

import com.company.work.entity.*;
import io.jmix.core.DataManager;
import io.jmix.ui.component.data.TableItems;
import io.jmix.ui.model.CollectionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class StudentService {
    @Autowired
    private DataManager dataManager;

    public int getNewStudentID(){
        try {
            Student maxStud = dataManager.load(Student.class)
                    .query("SELECT s FROM Student s WHERE s.studentID = (SELECT MAX(st.studentID) FROM Student st)")
                    .one();

            return maxStud.getStudentID() + 1;
        }
        catch (IllegalStateException e){
            return 1;
        }
    }
    public void setStudentsDcList(CollectionContainer<Student> studentsDc, Course currentlySelectedCourse){
        studentsDc.getMutableItems().sort((o1, o2) -> {
            if(o1.getScoreSumOfThreeSubjects()>o2.getScoreSumOfThreeSubjects())
                return -1;
            if(o1.getScoreSumOfThreeSubjects()< o2.getScoreSumOfThreeSubjects())
                return 1;
            return Integer.compare(getPriority(o2, currentlySelectedCourse), getPriority(o1, currentlySelectedCourse));
        });
    }
    private int getPriority(Student stud, Course currentlySelectedCourse){
        if(currentlySelectedCourse !=null) {
            for (DesiredCourse dc : stud.getDesiredCourses())
                if (dc.getDesiredCourse() == currentlySelectedCourse)
                    return dc.getPriority();
        }
        return 0;
    }
}