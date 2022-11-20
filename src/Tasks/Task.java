package Tasks;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public abstract class Task{
    private static int idCounter;
    private final int id;
    private String headline;
    private String description;
    private boolean isWorkTask;
    private LocalDateTime dateOfCompletion;

    public Task(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        this.headline = headline;
        this.description = description;
        this.isWorkTask = isWorkTask;
        id = idCounter++;
        this.dateOfCompletion = dateOfCompletion;
    }

    public int getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public boolean isWorkTask() {
        return isWorkTask;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkTask(boolean workTask) {
        isWorkTask = workTask;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", isWorkTask=" + isWorkTask +
                ", dateOfCompletion=" + dateOfCompletion +
                '}';
    }
}
