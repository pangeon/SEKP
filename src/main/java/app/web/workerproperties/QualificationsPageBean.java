
package app.web.workerproperties;

import app.model.entity.Qualifications;
import app.model.entity.Users;
import app.web.users.UsersController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class QualificationsPageBean
{
    @Inject
    private WorkerPropertiesController workerPropertiesController;
    
    @Inject
    private UsersController usersController;
    
    private List<Qualifications> listQualifications;
    private DataModel<Qualifications> myQualificationsCollection;
   
    private Qualifications qualifications = new Qualifications();
    
    private String programmingLanguage;
    private String frameworks;
    private String foreignLanguages;
    private String software;    
    private String otherSkills;
    
    private String progLanguagePattern = "";
    private String frameworkPattern = "";
    private String foreignLanguagePattern = "";
    private String softwarePattern = "";
    
    @PostConstruct
    private void initQualificationsList() {
        listQualifications = workerPropertiesController.searchQualificationsProperties
        (progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern);
        myQualificationsCollection = new ListDataModel<>(listQualifications);
    }
    public DataModel<Qualifications> getAllQualificationsList() {
        return (myQualificationsCollection = 
        new ListDataModel<>(workerPropertiesController.searchQualificationsProperties
        (progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern)));
    }
    public void clean() {
        progLanguagePattern = "";
        frameworkPattern = "";
        foreignLanguagePattern = "";
        softwarePattern = "";
    }
    public void search() {
        initQualificationsList();
    }
    public Users getUser() {
        return usersController.getAddedUser();
    }
    public DataModel<Qualifications> getQualificationsCollection() {
        myQualificationsCollection = new ListDataModel<>(getUser().getQualificationsCollection());
        return myQualificationsCollection;
    }
    public Qualifications getQualifications() {
        return qualifications;
    }
    public void setQualifications(Qualifications qualification) {
        this.qualifications = qualifications;
    }

    public String getProgrammingLanguage()
    {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage)
    {
        this.programmingLanguage = programmingLanguage;
    }

    public String getFrameworks()
    {
        return frameworks;
    }

    public void setFrameworks(String frameworks)
    {
        this.frameworks = frameworks;
    }

    public String getForeignLanguages()
    {
        return foreignLanguages;
    }

    public void setForeignLanguages(String foreignLanguages)
    {
        this.foreignLanguages = foreignLanguages;
    }

    public String getSoftware()
    {
        return software;
    }

    public void setSoftware(String software)
    {
        this.software = software;
    }

    public String getOtherSkills()
    {
        return otherSkills;
    }

    public void setOtherSkills(String otherSkills)
    {
        this.otherSkills = otherSkills;
    }

    public String getProgLanguagePattern()
    {
        return progLanguagePattern;
    }

    public void setProgLanguagePattern(String progLanguagePattern)
    {
        this.progLanguagePattern = progLanguagePattern;
    }

    public String getFrameworkPattern()
    {
        return frameworkPattern;
    }

    public void setFrameworkPattern(String frameworkPattern)
    {
        this.frameworkPattern = frameworkPattern;
    }

    public String getForeignLanguagePattern()
    {
        return foreignLanguagePattern;
    }

    public void setForeignLanguagePattern(String foreignLanguagePattern)
    {
        this.foreignLanguagePattern = foreignLanguagePattern;
    }

    public String getSoftwarePattern()
    {
        return softwarePattern;
    }

    public void setSoftwarePattern(String softwarePattern)
    {
        this.softwarePattern = softwarePattern;
    }
    public Users getMyUser() {
        return usersController.getMyUser();
    }
    public void addQualifications() {
        qualifications.setProgrammingLanguage(programmingLanguage);
        qualifications.setFrameworks(frameworks);
        qualifications.setForeignLanguages(foreignLanguages);
        qualifications.setSoftware(software);
        qualifications.setOtherSkills(otherSkills);

        workerPropertiesController.addQualifications(qualifications);
        getUser().getQualificationsCollection().add(qualifications);
    }  
    public void deleteQualifications() {
        workerPropertiesController.deleteQualifications(myQualificationsCollection.getRowData());
        getUser().getQualificationsCollection().remove(myQualificationsCollection.getRowData());
    }
    public String showUser() {
        usersController.downloadUserToEdit(myQualificationsCollection.getRowData().getUserId());
        System.out.println(myQualificationsCollection.getRowData().getUserId());
        return "DataUserList";
    }
}
