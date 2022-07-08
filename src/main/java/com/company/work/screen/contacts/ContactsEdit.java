package com.company.work.screen.contacts;

import com.company.work.entity.ExamResults;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.work.entity.Contacts;
import org.checkerframework.checker.regex.qual.Regex;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

@UiController("Contacts.edit")
@UiDescriptor("contacts-edit.xml")
@EditedEntityContainer("contactsDc")
public class ContactsEdit extends StandardEditor<Contacts> {

}