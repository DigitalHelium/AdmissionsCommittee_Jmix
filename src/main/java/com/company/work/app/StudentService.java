package com.company.work.app;

import com.company.work.entity.ExamResults;
import com.company.work.entity.Student;
import com.company.work.entity.StudentRating;
import io.jmix.core.DataManager;
import io.jmix.ui.component.data.TableItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
}