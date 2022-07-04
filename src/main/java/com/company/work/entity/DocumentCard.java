package com.company.work.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@JmixEntity
@Embeddable
public class DocumentCard {
    @Column(name = "SNILS_ID", nullable = false, unique = true, length = 20)
    @NotNull
    private String snilsID;

    @Column(name = "INN_ID", nullable = false, unique = true, length = 10)
    @NotNull
    private String innID;

    @Column(name = "PASSPORT_ID", nullable = false, unique = true, length = 20)
    @NotNull
    private String passportID;

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getPassportID() {
        return passportID;
    }

    public String getPassporttID() {
        return passportID;
    }

    public void setPassporttID(String passporttID) {
        this.passportID = passporttID;
    }

    public String getInnID() {
        return innID;
    }

    public void setInnID(String innID) {
        this.innID = innID;
    }

    public String getSnilsID() {
        return snilsID;
    }

    public void setSnilsID(String snilsID) {
        this.snilsID = snilsID;
    }
}