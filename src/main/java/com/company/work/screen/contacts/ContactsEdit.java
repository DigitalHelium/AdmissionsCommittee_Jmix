package com.company.work.screen.contacts;

import io.jmix.ui.screen.*;
import com.company.work.entity.Contacts;

@UiController("Contacts.edit")
@UiDescriptor("contacts-edit.xml")
@EditedEntityContainer("contactsDc")
public class ContactsEdit extends StandardEditor<Contacts> {
}