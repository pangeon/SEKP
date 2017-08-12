
package app.web.workerproperties;

import app.model.entity.Education;
import app.model.entity.HistoryWork;
import app.model.entity.Qualifications;
import app.model.entity.Trainings;
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
public class BigListPageBean
{
    @Inject
    private WorkerPropertiesController workerPropertiesController;
    
    @Inject
    private UsersController usersController;
    
    private List<HistoryWork> listHistoryWork;
    private DataModel<HistoryWork> myHistoryWorkCollection;
    
    private List<Education> listEducation;
    private DataModel<Education> myEducationCollection;
    
    private List<Trainings> listTrainings;
    private DataModel<Trainings> myTrainingsCollection;
    
    private List<Qualifications> listQualifications;
    private DataModel<Qualifications> myQualificationsCollection;
    
    private Date workBegin;
    private Date workEnd;
    private String company;
    private String position;
    private String characterOfWork;
    private String acquiredSkills;
    
    String companyPattern = ""; 
    String positionPattern = ""; 
    String aquiredSkillsPattern = "";

    private Date educationBegin;
    private Date educationEnd;
    private String nameOfSchool ;
    private String kindOfSchool;
    private String specialization;
    
    String nameOfSchoolPattern = ""; 
    String kindOfSchoolPattern = ""; 
    String specializationPattern = "";
    
    private Date trainingBegin;
    private Date trainingEnd;
    private String contentTraining;
    private Short trainingTime;
    
    private String contentTrainingPattern = "";
    
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
    private void initAllList() {
        listHistoryWork = workerPropertiesController.searchHistoryWorkProperties
        (companyPattern, positionPattern, aquiredSkillsPattern);
        myHistoryWorkCollection = new ListDataModel<>(listHistoryWork);
        
        listEducation = workerPropertiesController.searchEducationProperties
        (nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern);
        myEducationCollection = new ListDataModel<>(listEducation);
        
        listTrainings = workerPropertiesController.searchTrainingsProperties(contentTrainingPattern);
        myTrainingsCollection = new ListDataModel<>(listTrainings);
        
        listQualifications = workerPropertiesController.searchQualificationsProperties
        (progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern);
        myQualificationsCollection = new ListDataModel<>(listQualifications);
        
    }
    public void clean() {
        companyPattern = ""; 
        positionPattern = ""; 
        aquiredSkillsPattern = "";
        nameOfSchoolPattern = ""; 
        kindOfSchoolPattern = ""; 
        specializationPattern = "";
        contentTrainingPattern = "";
        progLanguagePattern = "";
        frameworkPattern = "";
        foreignLanguagePattern = "";
        softwarePattern = "";
    }
    public void search() {
        initAllList();
    }
    
    public Users getUser() {
        return usersController.getAddedUser();
    }

    public UsersController getUsersController()
    {
        return usersController;
    }

    public void setUsersController(UsersController usersController)
    {
        this.usersController = usersController;
    }

    public List<HistoryWork> getListHistoryWork()
    {
        return listHistoryWork;
    }

    public void setListHistoryWork(List<HistoryWork> listHistoryWork)
    {
        this.listHistoryWork = listHistoryWork;
    }

    public DataModel<HistoryWork> getMyHistoryWorkCollection()
    {
        return myHistoryWorkCollection;
    }

    public void setMyHistoryWorkCollection(DataModel<HistoryWork> myHistoryWorkCollection)
    {
        this.myHistoryWorkCollection = myHistoryWorkCollection;
    }

    public List<Education> getListEducation()
    {
        return listEducation;
    }

    public void setListEducation(List<Education> listEducation)
    {
        this.listEducation = listEducation;
    }

    public DataModel<Education> getMyEducationCollection()
    {
        return myEducationCollection;
    }

    public void setMyEducationCollection(DataModel<Education> myEducationCollection)
    {
        this.myEducationCollection = myEducationCollection;
    }

    public List<Trainings> getListTrainings()
    {
        return listTrainings;
    }

    public void setListTrainings(List<Trainings> listTrainings)
    {
        this.listTrainings = listTrainings;
    }

    public DataModel<Trainings> getMyTrainingsCollection()
    {
        return myTrainingsCollection;
    }

    public void setMyTrainingsCollection(DataModel<Trainings> myTrainingsCollection)
    {
        this.myTrainingsCollection = myTrainingsCollection;
    }

    public List<Qualifications> getListQualifications()
    {
        return listQualifications;
    }

    public void setListQualifications(List<Qualifications> listQualifications)
    {
        this.listQualifications = listQualifications;
    }

    public DataModel<Qualifications> getMyQualificationsCollection()
    {
        return myQualificationsCollection;
    }

    public void setMyQualificationsCollection(DataModel<Qualifications> myQualificationsCollection)
    {
        this.myQualificationsCollection = myQualificationsCollection;
    }

    public Date getWorkBegin()
    {
        return workBegin;
    }

    public void setWorkBegin(Date workBegin)
    {
        this.workBegin = workBegin;
    }

    public Date getWorkEnd()
    {
        return workEnd;
    }

    public void setWorkEnd(Date workEnd)
    {
        this.workEnd = workEnd;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getCharacterOfWork()
    {
        return characterOfWork;
    }

    public void setCharacterOfWork(String characterOfWork)
    {
        this.characterOfWork = characterOfWork;
    }

    public String getAcquiredSkills()
    {
        return acquiredSkills;
    }

    public void setAcquiredSkills(String acquiredSkills)
    {
        this.acquiredSkills = acquiredSkills;
    }

    public String getCompanyPattern()
    {
        return companyPattern;
    }

    public void setCompanyPattern(String companyPattern)
    {
        this.companyPattern = companyPattern;
    }

    public String getPositionPattern()
    {
        return positionPattern;
    }

    public void setPositionPattern(String positionPattern)
    {
        this.positionPattern = positionPattern;
    }

    public String getAquiredSkillsPattern()
    {
        return aquiredSkillsPattern;
    }

    public void setAquiredSkillsPattern(String aquiredSkillsPattern)
    {
        this.aquiredSkillsPattern = aquiredSkillsPattern;
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

    public Date getTrainingBegin()
    {
        return trainingBegin;
    }

    public void setTrainingBegin(Date trainingBegin)
    {
        this.trainingBegin = trainingBegin;
    }

    public Date getTrainingEnd()
    {
        return trainingEnd;
    }

    public void setTrainingEnd(Date trainingEnd)
    {
        this.trainingEnd = trainingEnd;
    }

    public String getContentTraining()
    {
        return contentTraining;
    }

    public void setContentTraining(String contentTraining)
    {
        this.contentTraining = contentTraining;
    }

    public Short getTrainingTime()
    {
        return trainingTime;
    }

    public void setTrainingTime(Short trainingTime)
    {
        this.trainingTime = trainingTime;
    }

    public String getContentTrainingPattern()
    {
        return contentTrainingPattern;
    }

    public void setContentTrainingPattern(String contentTrainingPattern)
    {
        this.contentTrainingPattern = contentTrainingPattern;
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
    
    
}
