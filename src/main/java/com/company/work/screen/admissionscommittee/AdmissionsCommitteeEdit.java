package com.company.work.screen.admissionscommittee;

import io.jmix.ui.screen.*;
import com.company.work.entity.AdmissionsCommittee;

@UiController("AdmissionsCommittee.edit")
@UiDescriptor("admissions-committee-edit.xml")
@EditedEntityContainer("admissionsCommitteeDc")
public class AdmissionsCommitteeEdit extends StandardEditor<AdmissionsCommittee> {
}