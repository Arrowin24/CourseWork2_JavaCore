package tasks;

import java.time.LocalDateTime;

public abstract class Task {
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

    //Метод для проверки задач на день
    public abstract boolean checkTaskOfDay(LocalDateTime date);

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
        if (!headline.isBlank()) {
            this.headline = headline;
        } else {
            throw new IllegalArgumentException("Неправильно введен Заголовок задачи");
        }
    }

    public void setDescription(String description) {
        if (!description.isBlank()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Неправильно введено Описание задачи");
        }

    }

    public void setWorkTask(boolean workTask) {
        isWorkTask = workTask;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }


    @Override
    public String toString() {
        String isWork;
        if (isWorkTask) {
            isWork = "Рабочая";
        } else {
            isWork = "Личная";
        }
        return "Задача: " +
                "Уникальный номер = " + id +
                ", Заголовок = '" + headline + '\'' +
                ", Описание = '" + description + '\'' +
                ", Тип = '" + isWork + '\'' +
                ", Дата начала выполнения  = " + dateOfCompletion.toLocalDate() +
                ", Время = " + dateOfCompletion.toLocalTime();
    }
}
