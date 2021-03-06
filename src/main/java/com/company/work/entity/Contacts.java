package com.company.work.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "CONTACTS")
@Entity
public class Contacts {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Pattern(message = "Неверный формат номера", regexp = "^((8|\\\\+7)[\\\\- ]?)?(\\\\(?\\\\d{3}\\\\)?[\\\\- ]?)?[\\\\d\\\\- ]{7,10}$")
    @Column(name = "MAIN_PHONE_NUM", nullable = false, length = 20)
    @NotNull
    private String mainPhoneNum;

    @Pattern(message = "Неверный формат номера", regexp = "^((8|\\\\+7)[\\\\- ]?)?(\\\\(?\\\\d{3}\\\\)?[\\\\- ]?)?[\\\\d\\\\- ]{7,10}$")
    @Column(name = "SECONDARY_PHONE_NUM", length = 20)
    private String secondaryPhoneNum;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "MAIN_ADDRESS_COUNTRY")),
            @AttributeOverride(name = "region", column = @Column(name = "MAIN_ADDRESS_REGION")),
            @AttributeOverride(name = "locality", column = @Column(name = "MAIN_ADDRESS_LOCALITY")),
            @AttributeOverride(name = "street", column = @Column(name = "MAIN_ADDRESS_STREET")),
            @AttributeOverride(name = "houseNum", column = @Column(name = "MAIN_ADDRESS_HOUSE_NUM")),
            @AttributeOverride(name = "block", column = @Column(name = "MAIN_ADDRESS_BLOCK")),
            @AttributeOverride(name = "apartmentNumber", column = @Column(name = "MAIN_ADDRESS_APARTMENT_NUMBER")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "MAIN_ADDRESS_POSTAL_CODE"))
    })
    private Address registrationAddress;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "RESIDENTIAL_ADDRESS_COUNTRY")),
            @AttributeOverride(name = "region", column = @Column(name = "RESIDENTIAL_ADDRESS_REGION")),
            @AttributeOverride(name = "locality", column = @Column(name = "RESIDENTIAL_ADDRESS_LOCALITY")),
            @AttributeOverride(name = "street", column = @Column(name = "RESIDENTIAL_ADDRESS_STREET")),
            @AttributeOverride(name = "houseNum", column = @Column(name = "RESIDENTIAL_ADDRESS_HOUSE_NUM")),
            @AttributeOverride(name = "block", column = @Column(name = "RESIDENTIAL_ADDRESS_BLOCK")),
            @AttributeOverride(name = "apartmentNumber", column = @Column(name = "RESIDENTIAL_ADDRESS_APARTMENT_NUMBER")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "RESIDENTIAL_ADDRESS_POSTAL_CODE"))
    })
    private Address residentialAddress;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Address getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(Address registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getSecondaryPhoneNum() {
        return secondaryPhoneNum;
    }

    public void setSecondaryPhoneNum(String secondaryPhoneNum) {
        this.secondaryPhoneNum = secondaryPhoneNum;
    }

    public String getMainPhoneNum() {
        return mainPhoneNum;
    }

    public void setMainPhoneNum(String mainPhoneNum) {
        this.mainPhoneNum = mainPhoneNum;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"mainPhoneNum", "registrationAddress"})
    public String getInstanceName() {
        return String.format("%s, %s", mainPhoneNum, registrationAddress.getInstanceName());
    }
}