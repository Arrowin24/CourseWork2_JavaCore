
import Tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TasksUtils {
    private HashMap<Integer, Task> tasks = new HashMap<>();

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public TasksUtils() {
    }

    public Task createTask(String headline, String description, LocalDateTime dateOfCompletion, boolean isWorkTask, int repeatable){
        switch (repeatable){
            case 0:
                return new OneRepeatTask(headline,description,isWorkTask,dateOfCompletion);
            case 1:
                return new DailyTask(headline,description,isWorkTask,dateOfCompletion);
            case 2:
                return new WeeklyTask(headline,description,isWorkTask,dateOfCompletion);
            case 3:
                return new MonthlyTask(headline,description,isWorkTask,dateOfCompletion);
            case 4:
                return new YearlyTask(headline,description,isWorkTask,dateOfCompletion);
            default:
                throw new IllegalArgumentException("Неправильно была выбрана задача");
        }

    }
    public void addTask(Task task){
        tasks.put(task.getId(),task);
    }

    public LocalDateTime createDateAndTime(String date, String time){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date,dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time,timeFormatter);
        return LocalDateTime.of(localDate,localTime);
    }
    public boolean createIsWork(int isWork){
        return isWork==1;
    }

}
