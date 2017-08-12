package app.endpoint;

import app.authentication.beans.LoginEJB;
import app.facade.EducationFacade;
import app.facade.HistoryWorkFacade;
import app.facade.QualificationsFacade;
import app.facade.TrainingsFacade;
import app.model.entity.Education;
import app.model.entity.HistoryWork;
import app.model.entity.Qualifications;
import app.model.entity.Trainings;
import app.model.entity.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class WorkerPropertiesEndpoint
{
    @EJB
    private LoginEJB loginEJB;

    @EJB
    private EducationFacade educationFacade;
    @EJB
    private TrainingsFacade trainingsFacade;
    @EJB
    private HistoryWorkFacade historyWorkFacade;
    @EJB 
    private QualificationsFacade qualificationsFacade;
    
    public void addQualificationsForMe(Qualifications qualification) {
        addQualificationForUser(loginEJB.getMyUser(), qualification);
    }
    public void addEducationForMe(Education education) {
        addEducationForUser(loginEJB.getMyUser(), education);
    }
    public void addTrainingsForMe(Trainings trainings) {
        addTrainingsForUser(loginEJB.getMyUser(), trainings);
    }
    public void addHistotyWorkForMe(HistoryWork historyWork) {
        addHistoryWorkForUser(loginEJB.getMyUser(), historyWork);
    }
        
    private void addQualificationForUser(Users user, Qualifications qualification) 
    {
        /* Zalozenia:
        encja User jest w stanie zarzadzanym
        encja Education jest w stanie nowym
        */
                
        qualificationsFacade.create(qualification);

        
        user.getQualificationsCollection().add(qualification);
        qualification.setUserId(user);
    }
    private void addEducationForUser(Users user, Education education) {
        educationFacade.create(education);
        
        user.getEducationCollection().add(education);
        education.setUserId(user);
    }
    private void addTrainingsForUser(Users user, Trainings trainings) {
        trainingsFacade.create(trainings);
        
        user.getTrainingsCollection().add(trainings);
        trainings.setUserId(user);
    }
    private void addHistoryWorkForUser(Users user, HistoryWork historyWork) {
        historyWorkFacade.create(historyWork);
        
        user.getHistoryWorkCollection().add(historyWork);
        historyWork.setUserId(user);
    }
    
    public void deleteHistoryWork(HistoryWork historyWork) {
        historyWorkFacade.remove(historyWork);
    }
    public void deleteEducation(Education education) {
        educationFacade.remove(education);
    }
    public void deleteQualifications(Qualifications qualifications) {
        qualificationsFacade.remove(qualifications);
    }
    public void deleteTrainings(Trainings trainings) {
        trainingsFacade.remove(trainings);
    }
    public List<HistoryWork> searchHistoryWorkProperties
        (String companyPattern, String positionPattern, String aquiredSkillsPattern) 
    {
        return historyWorkFacade.searchHistoryWorkProperties(companyPattern, positionPattern, aquiredSkillsPattern);
    }
    public List<Qualifications> searchQualificationsProperties
        (String progLanguagePattern, String frameworkPattern, String foreignLanguagePattern, String softwarePattern) 
    {
        return qualificationsFacade.searchQualificationsProperties
        (progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern);
    }
    public List<Education> searchEducationProperties
        (String nameOfSchoolPattern, String kindOfSchoolPattern, String specializationPattern) 
    {
        return educationFacade.searchEducationProperties(nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern);
    }
    public List<Trainings> searchTrainingsProperties
        (String contentTrainingPattern) 
    {
        return trainingsFacade.searchTrainingsProperties(contentTrainingPattern);
    }
    public void invokeAllSearchMethod(
            String companyPattern, String positionPattern, String aquiredSkillsPattern,
            String nameOfSchoolPattern, String kindOfSchoolPattern, String specializationPattern, 
            String contentTrainingPattern,
            String progLanguagePattern, String frameworkPattern, String foreignLanguagePattern, String softwarePattern
    ) 
    {
        searchHistoryWorkProperties(companyPattern, positionPattern, aquiredSkillsPattern);
        searchEducationProperties(nameOfSchoolPattern, kindOfSchoolPattern, specializationPattern);
        searchTrainingsProperties(contentTrainingPattern);
        searchQualificationsProperties(progLanguagePattern, frameworkPattern, foreignLanguagePattern, softwarePattern);
    }
}
