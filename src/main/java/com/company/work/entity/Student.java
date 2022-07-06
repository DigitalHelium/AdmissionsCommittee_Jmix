package com.company.work.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JmixEntity
@Table(name = "STUDENT", indexes = {
        @Index(name = "IDX_STUDENT_CONTACTS_ID", columnList = "CONTACTS_ID"),
        @Index(name = "IDX_STUDENT", columnList = "ADMISSIONS_COMMITTEE_ID")
})
@Entity
public class Student {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    @NotNull
    private String lastName;

    @Column(name = "MIDDLE_NAME", length = 50)
    private String middleName;

    @Column(name = "GENDER", nullable = false, length = 15)
    @NotNull
    private String gender;

    @Column(name = "BIRTH_DATE", nullable = false)
    @NotNull
    private LocalDate birthDate;

    @Column(name = "STUDENT_ID", nullable = false, unique = true)
    @NotNull
    private Integer studentID;

    @Column(name = "CITIZENSHIP", nullable = false, length = 60)
    @NotNull
    private String citizenship;

    @Column(name = "IS_DORM_NEEDED", nullable = false)
    @NotNull
    private Boolean isDormNeeded = false;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "CONTACTS_ID", nullable = false)
    @Composition
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Contacts contacts;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "snilsID", column = @Column(name = "DOCUMENTS_SNILS_ID")),
            @AttributeOverride(name = "innID", column = @Column(name = "DOCUMENTS_INN_ID")),
            @AttributeOverride(name = "passportID", column = @Column(name = "DOCUMENTS_PASSPORT_ID"))
    })
    private DocumentCard documents;

    @OneToMany(mappedBy = "student")
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    private Set<DesiredCourse> desiredCourses;


    @Column(name = "SCORE_SUM_OF_THREE_SUBJECTS", nullable = false)
    @NotNull
    private Integer scoreSumOfThreeSubjects;

    @OneToMany(mappedBy = "student")
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    private Set<ExamResults> examResults;

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

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @JoinTable(name = "STUDENT_RATING_STUDENT_LINK",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_RATING_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<StudentRating> studentRatings;

    public void setStudentRatings(List<StudentRating> studentRatings) {
        this.studentRatings = studentRatings;
    }

    public List<StudentRating> getStudentRatings() {
        return studentRatings;
    }

    public void setScoreSumOfThreeSubjects(Integer scoreSumOfThreeSubjects) {
        this.scoreSumOfThreeSubjects = scoreSumOfThreeSubjects;
    }

    public DocumentCard getDocuments() {
        return documents;
    }

    public void setDocuments(DocumentCard documents) {
        this.documents = documents;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }


    public void setDesiredCourses(Set<DesiredCourse> desiredCourses) {
        this.desiredCourses = desiredCourses;
    }

    public Set<DesiredCourse> getDesiredCourses() {
        return desiredCourses;
    }

    public void setExamResults(Set<ExamResults> examResults) {
        this.examResults = examResults;
    }

    public Set<ExamResults> getExamResults() {
        return examResults;
    }

    public Integer getScoreSumOfThreeSubjects() {
        return scoreSumOfThreeSubjects;
    }

    public Boolean getIsDormNeeded() {
        return isDormNeeded;
    }

    public void setIsDormNeeded(Boolean isDormNeeded) {
        this.isDormNeeded = isDormNeeded;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}