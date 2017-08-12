
package app.web.workerproperties;

import app.model.entity.HistoryWork;
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
public class HistoryWorkPageBean
{
    @Inject
    private WorkerPropertiesController workerPropertiesController;
    
    @Inject
    private UsersController usersController;
    
    private List<HistoryWork> listHistoryWork;
    private DataModel<HistoryWork> myHistoryWorkCollection;
    
    private HistoryWork historyWork = new HistoryWork();
    
    private Date workBegin;
    private Date workEnd;
    private String company;
    private String position;
    private String characterOfWork;
    private String acquiredSkills;
        
    String companyPattern = ""; 
    String positionPattern = ""; 
    String aquiredSkillsPattern = "";
    
    @PostConstruct
    private void initHistoryWorkList() {
        listHistoryWork = workerPropertiesController.searchHistoryWorkProperties
        (companyPattern, positionPattern, aquiredSkillsPattern);
        myHistoryWorkCollection = new ListDataModel<>(listHistoryWork);
    }
    public DataModel<HistoryWork> getAllHistoryWorkList() {
        return (myHistoryWorkCollection = 
        new ListDataModel<>(workerPropertiesController.searchHistoryWorkProperties
        (companyPattern, positionPattern, aquiredSkillsPattern)));
    }
    public void clean() {
        companyPattern = ""; 
        positionPattern = ""; 
        aquiredSkillsPattern = "";
    }
    public void search() {
        initHistoryWorkList();
    }
    
    public Users getUser() {
        return usersController.getAddedUser();
    }
    
    public DataModel<HistoryWork> getMyHistoryWorkCollection() {
        myHistoryWorkCollection = new ListDataModel<>(getUser().getHistoryWorkCollection());
        return myHistoryWorkCollection;
    }
    public void getUserForDatabase() {
        getUser();
    }

    public HistoryWork getHistoryWork()
    {
        return historyWork;
    }

    public void setHistoryWork(HistoryWork historyWork)
    {
        this.historyWork = historyWork;
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

    public Users getMyUser() {
        return usersController.getMyUser();
    }
    public void addHistoryWork() {
        historyWork.setWorkBegin(workBegin);
        historyWork.setWorkEnd(workEnd);
        historyWork.setCompany(company);
        historyWork.setPosition(position);
        historyWork.setCharacterOfWork(characterOfWork);
        historyWork.setAcquiredSkills(acquiredSkills);
        
        workerPropertiesController.addHistoryWork(historyWork);
        getUser().getHistoryWorkCollection().add(historyWork);
    }    
    public void deleteHistoryWork() {
        workerPropertiesController.deleteHistoryWork(myHistoryWorkCollection.getRowData()); // usuniecie z bazy
        getUser().getHistoryWorkCollection().remove(myHistoryWorkCollection.getRowData()); // usuniecie z obiektu usera ktory mamy zapamietany w sesji
    }   
    public String showUser() {
        usersController.downloadUserToEdit(myHistoryWorkCollection.getRowData().getUserId());
        System.out.println(myHistoryWorkCollection.getRowData().getUserId());
        return "DataUserList";
    }
}
