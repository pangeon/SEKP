package app.web.workerproperties;

import app.endpoint.WorkerPropertiesEndpoint;
import app.model.entity.Education;
import app.model.entity.HistoryWork;
import app.model.entity.Qualifications;
import app.model.entity.Trainings;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SessionScoped
public class WorkerPropertiesController implements Serializable {

    @EJB
    private WorkerPropertiesEndpoint workerPropertiesEndpoint;
    
    public void addQualifications(Qualifications qualifications) {
        workerPropertiesEndpoint.addQualificationsForMe(qualifications);
    }

    public void addEducations(Education education) {
        workerPropertiesEndpoint.addEducationForMe(education);
    }

    public void addTraining(Trainings training) {
        workerPropertiesEndpoint.addTrainingsForMe(training);
    }

    public void addHistoryWork(HistoryWork historyWork) {
        workerPropertiesEndpoint.addHistotyWorkForMe(historyWork);
    }
    
    public String sendMessage(String resource, String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resource);
        FacesMessage facesMessage = new FacesMessage(resourceBundle.getString(key));
        facesContext.addMessage(null, facesMessage);
        return null;
    }
    
    public void deleteHistoryWork(HistoryWork historyWork) {
        workerPropertiesEndpoint.deleteHistoryWork(historyWork);
    }
    public void deleteEducation(Education education) {
        workerPropertiesEndpoint.deleteEducation(education);
    }
    public void deleteQualifications(Qualifications qualifications) {
        workerPropertiesEndpoint.deleteQualifications(qualifications);
    }
    public void deleteTrainings(Trainings trainings) {
        workerPropertiesEndpoint.deleteTrainings(trainings);
    }
    public List<HistoryWork> searchHistoryWorkProperties
        (String companyPattern, String positionPattern, String aquiredSkillsPattern) 
    {
        return workerPropertiesEndpoint.searchHistoryWorkProperties(companyPattern, positionPattern, aquiredSkillsPattern);
    }
    public List<Qualifications> searchQualificationsProperties
        (String progLanguagePattern, String frameworkPattern, String foreignLanguagePattern, String softwarePattern) 
    {
        return workerPropertiesEndpoint.searchQualificationsProperties
        (progLanguagePattern, frameworkPattern,foreignLanguagePattern, softwarePattern);
    }
    public List<Education> searchEducationProperties
        (String nameOfSchoolPattern, String kindOfSchoolPattern, String specializationPattern) 
    {
        return workerPropertiesEndpoint.searchEducationProperties(nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern);
    }
    public List<Trainings> searchTrainingsProperties
        (String contentTrainingPattern) 
    {
        return workerPropertiesEndpoint.searchTrainingsProperties(contentTrainingPattern);
    }
    public void invokeAllSearchMethod(
            String companyPattern, String positionPattern, String aquiredSkillsPattern,
            String nameOfSchoolPattern, String kindOfSchoolPattern, String specializationPattern, 
            String contentTrainingPattern,
            String progLanguagePattern, String frameworkPattern, String foreignLanguagePattern, String softwarePattern
    ) 
    {
        workerPropertiesEndpoint.invokeAllSearchMethod(
                companyPattern, positionPattern, aquiredSkillsPattern, 
                nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern, 
                contentTrainingPattern, 
                progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern);
    } 
    
}
