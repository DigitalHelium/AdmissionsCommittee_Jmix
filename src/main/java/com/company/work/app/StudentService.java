package com.company.work.app;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentService {
    @Autowired
    private DataManager dataManager;

    public int getSumOfStudentsScores(UUID id){
        return dataManager.loadValues(
                "Select sum(er.SCORE)"+
                "From ExamResults er Where STUDENT_ID In (Select STUDENT_ID FROM Student Where ID="+id+")")
                .properties("scoreSum")
                .one().getValue("scoreSum");
    }
}