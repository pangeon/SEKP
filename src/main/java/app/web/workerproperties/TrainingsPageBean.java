
package app.web.workerproperties;

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
public class TrainingsPageBean
{
    @Inject
    private WorkerPropertiesController workerPropertiesController;
    
    @Inject
    private UsersController usersController;
    
    private List<Trainings> listTrainings;
    private DataModel<Trainings> myTrainingsCollection;
    
    private Trainings trainings = new Trainings();
    
    private Date trainingBegin;
    private Date trainingEnd;
    private String contentTraining;
    private Short trainingTime;
    
    private String contentTrainingPattern = "";
    
    @PostConstruct
    private void initTrainingsList() {
        listTrainings = workerPropertiesController.searchTrainingsProperties(contentTrainingPattern);
        myTrainingsCollection = new ListDataModel<>(listTrainings);
    }
    public DataModel<Trainings> getAllTrainingsList() {
        return (myTrainingsCollection = 
        new ListDataModel<>(workerPropertiesController.searchTrainingsProperties(contentTrainingPattern)));
    }
    public void clean() {
        contentTrainingPattern = "";
    }
    public void search() {
        initTrainingsList();
    }
    
    public Users getUser() {
        return usersController.getAddedUser();
    }
    public DataModel<Trainings> getTrainingsCollection() {
        myTrainingsCollection = new ListDataModel<>(getUser().getTrainingsCollection());
        return myTrainingsCollection;
    }
    public Trainings getTraings()
    {
        return trainings;
    }

    public void setTraings(Trainings traings)
    {
        this.trainings = traings;
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
    public Users getMyUser() {
        return usersController.getMyUser();
    }   
    public void addTraining() {
        trainings.setTrainingBegin(trainingBegin);
        trainings.setTrainingEnd(trainingEnd);
        trainings.setContentTraining(contentTraining);
        trainings.setTrainingTime(trainingTime);

        workerPropertiesController.addTraining(trainings);
        getUser().getTrainingsCollection().add(trainings);
    }
    public void deleteTrainings() {
        workerPropertiesController.deleteTrainings(myTrainingsCollection.getRowData());
        getUser().getTrainingsCollection().remove(myTrainingsCollection.getRowData());
    }
    public String showUser() {
        usersController.downloadUserToEdit(myTrainingsCollection.getRowData().getUserId());
        System.out.println(myTrainingsCollection.getRowData().getUserId());
        return "DataUserList";
    }
}
