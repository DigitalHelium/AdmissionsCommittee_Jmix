package com.company.work.screen.examresults;

import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.work.entity.ExamResults;
import org.springframework.beans.factory.annotation.Autowired;


@UiController("ExamResults.edit")
@UiDescriptor("exam-results-edit.xml")
@EditedEntityContainer("examResultsDc")
public class ExamResultsEdit extends StandardEditor<ExamResults> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if(isScoreOutOfBounds(getEditedEntity())){
            notifications.create().withCaption(messages.getMessage(getClass(), "scoreNotification")).show();
            event.preventCommit();
        }
        else
            event.resume();

    }
    public boolean isScoreOutOfBounds(ExamResults results){
        return results.getScore()>100 || results.getScore()<0;
    }

}