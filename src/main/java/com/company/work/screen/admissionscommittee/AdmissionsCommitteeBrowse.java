package com.company.work.screen.admissionscommittee;

import io.jmix.ui.screen.*;
import com.company.work.entity.AdmissionsCommittee;

@UiController("AdmissionsCommittee.browse")
@UiDescriptor("admissions-committee-browse.xml")
@LookupComponent("admissionsCommitteesTable")
public class AdmissionsCommitteeBrowse extends StandardLookup<AdmissionsCommittee> {
}