
package app.web.workerproperties;

import app.model.entity.Education;
import app.model.entity.Users;
import app.web.users.UsersController;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class EducationPageBean
{
    @Inject
    private WorkerPropertiesController workerPropertiesController;
    
    @Inject
    private UsersController usersController;
    
    private List<Education> listEducation;
    private DataModel<Education> myEducationCollection;
    
    private Education education = new Education();
    
    private Date educationBegin;
    private Date educationEnd;
    private String nameOfSchool ;
    private String kindOfSchool;
    private String specialization;
    
    String nameOfSchoolPattern = ""; 
    String kindOfSchoolPattern = ""; 
    String specializationPattern = "";
    
    @PostConstruct
    private void initEducationList() {
        listEducation = workerPropertiesController.searchEducationProperties
        (nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern);
        myEducationCollection = new ListDataModel<>(listEducation);
    }
    public DataModel<Education> getAllEducationList() {
        return (myEducationCollection = 
        new ListDataModel<>(workerPropertiesController.searchEducationProperties
        (nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern)));
    }
    public void clean() {
        nameOfSchoolPattern = ""; 
        kindOfSchoolPattern = ""; 
        specializationPattern = "";
    }
    public void search() {
        initEducationList();
    }
    
    public Users getUser() {
        return usersController.getAddedUser();
    }
    public DataModel<Education> getEducationCollection() {
        myEducationCollection = new ListDataModel<>(getUser().getEducationCollection());
        return myEducationCollection;
    }
    public Education getEducation()
    {
        return education;
    }    
    public void setEducation(Education education)
    {
        this.education = education;
    }

    public Date getEducationBegin()
    {
        return educationBegin;
    }

    public void setEducationBegin(Date educationBegin)
    {
        this.educationBegin = educationBegin;
    }

    public Date getEducationEnd()
    {
        return educationEnd;
    }

    public void setEducationEnd(Date educationEnd)
    {
        this.educationEnd = educationEnd;
    }

    public String getNameOfSchool()
    {
        return nameOfSchool;
    }

    public void setNameOfSchool(String nameOfSchool)
    {
        this.nameOfSchool = nameOfSchool;
    }

    public String getKindOfSchool()
    {
        return kindOfSchool;
    }

    public void setKindOfSchool(String kindOfSchool)
    {
        this.kindOfSchool = kindOfSchool;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }

    public String getNameOfSchoolPattern()
    {
        return nameOfSchoolPattern;
    }

    public void setNameOfSchoolPattern(String nameOfSchoolPattern)
    {
        this.nameOfSchoolPattern = nameOfSchoolPattern;
    }

    public String getKindOfSchoolPattern()
    {
        return kindOfSchoolPattern;
    }

    public void setKindOfSchoolPattern(String kindOfSchoolPattern)
    {
        this.kindOfSchoolPattern = kindOfSchoolPattern;
    }

    public String getSpecializationPattern()
    {
        return specializationPattern;
    }

    public void setSpecializationPattern(String specializationPattern)
    {
        this.specializationPattern = specializationPattern;
    }
    public Users getMyUser() {
        return usersController.getMyUser();
    }
    public void addEducations() {
        education.setEducationBegin(educationBegin);
        education.setEducationEnd(educationEnd);
        education.setNameOfSchool(nameOfSchool);
        education.setKindOfSchool(kindOfSchool);
        education.setSpecialization(specialization);

        workerPropertiesController.addEducations(education);
        getUser().getEducationCollection().add(education);
    }
    public void deleteEducation() {
        // myHistoryWorkCollection.getRowData() to obiekt ktory chcemy usunac
        workerPropertiesController.deleteEducation(myEducationCollection.getRowData()); 
        // usuniecie z bazy
        getUser().getEducationCollection().remove(myEducationCollection.getRowData()); 
        // usuniecie z obiektu usera ktory mamy zapamietany w sesji
    }
    public String showUser() {
        usersController.downloadUserToEdit(myEducationCollection.getRowData().getUserId());
        System.out.println(myEducationCollection.getRowData().getUserId());
        return "DataUserList";
    }
}
