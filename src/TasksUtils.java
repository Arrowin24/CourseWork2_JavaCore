
import Tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TasksUtils {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Task> deletedTasks = new HashMap<>();

    public TasksUtils() {
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Task> getDeletedTasks() {
        return deletedTasks;
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //Определение типа повторяемости задач и проверка правильности заполнения
    public Task createTask(String headline, String description, LocalDateTime dateOfCompletion, boolean isWorkTask, int repeatable) {
        switch (repeatable) {
            case 0:
                return new OneRepeatTask(headline, description, isWorkTask, dateOfCompletion);
            case 1:
                return new DailyTask(headline, description, isWorkTask, dateOfCompletion);
            case 2:
                return new WeeklyTask(headline, description, isWorkTask, dateOfCompletion);
            case 3:
                return new MonthlyTask(headline, description, isWorkTask, dateOfCompletion);
            case 4:
                return new YearlyTask(headline, description, isWorkTask, dateOfCompletion);
            default:
                throw new IllegalArgumentException("Неправильно был выбран тип повторяемости задача");
        }

    }

    // Проверка по формату введенной Даты и Времени
    public LocalDateTime createDateAndTime(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        return LocalDateTime.of(localDate, localTime);
    }

    // Проверка по формату введенного Типа задачи
    public boolean createIsWork(int isWork) {
        if (isWork == 1) {
            return true;
        }
        if (isWork == 0) {
            return false;
        }
        throw new IllegalArgumentException("Не правильно введен тип задачи!");
    }

    //Вывод всех активных задач
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Пока что ни одна задача не была создана");
            return;
        }
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }

    public void printTasksOf(LocalDateTime date) {
        boolean haveDailyTask = false;
        for (Task task : tasks.values()) {
            if (task.checkTaskOfDay(date)) {
                haveDailyTask = true;
                System.out.println(task);
            }
        }
        if (!haveDailyTask) {
            System.out.println("На данный день нет никаких запланированных задач");
        }
    }

    //Методы для печати "удаленных задач"
    public void printDeletedTasks() {
        if (deletedTasks.isEmpty()) {
            System.out.println("Пока что ни одна задача не удалена");
            return;
        }
        for (Task task : deletedTasks.values()) {
            System.out.println(task);
        }
    }

    //"Удаление" задачи из списка актуальных задач
    public void deleteTask(int taskId) {
        Task task = tasks.get(taskId);
        deletedTasks.put(task.getId(), task);
        tasks.remove(taskId);
    }

    //Методы для изменения какого-либо параметра задачи
    public void checkTaskId(int taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Задачи с таким ID не существует");
        }
    }

    public void changeHeadline(int taskId, String headline) {
        tasks.get(taskId).setHeadline(headline);
    }

    public void changeDescription(int taskId, String description) {
        tasks.get(taskId).setDescription(description);
    }

    public void changeDate(int taskId, LocalDateTime date) {
        LocalDate localDate = date.toLocalDate();
        LocalTime localTime = tasks.get(taskId).getDateOfCompletion().toLocalTime();
        tasks.get(taskId).setDateOfCompletion(LocalDateTime.of(localDate, localTime));
    }

    public void changeTime(int taskId, LocalDateTime time) {
        LocalDate localDate = tasks.get(taskId).getDateOfCompletion().toLocalDate();
        LocalTime localTime = time.toLocalTime();
        tasks.get(taskId).setDateOfCompletion(LocalDateTime.of(localDate, localTime));
    }

    public void changeIsWork(int taskId, boolean isWork) {
        tasks.get(taskId).setWorkTask(isWork);
    }

    //Метод для печати сортированного списка задач
    public void printSortedByDateTasks() {
        List<Task> sortedTask = new ArrayList<>(tasks.values());

        //Сортировка пузырьком листа по датам
        for (int i = sortedTask.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                LocalDateTime dateTime1 = getNextDateTime(sortedTask.get(j));
                LocalDateTime dateTime2 = getNextDateTime(sortedTask.get(j + 1));
                if (dateTime1.isAfter(dateTime2)) {
                    Task task = sortedTask.get(j);
                    sortedTask.set(j, sortedTask.get(j + 1));
                    sortedTask.set(j + 1, task);
                }
            }
        }
        for (Task task : sortedTask) {
            System.out.println(task);
        }
    }

    //Метод необходимый для сортировки массива. Определяет ближайшую дату задачи
    private LocalDateTime getNextDateTime(Task task) {
        if (task.getClass().equals(OneRepeatTask.class)) {
            return task.getDateOfCompletion();
        } else {
            Repeatable repeat = (Repeatable) task;
            return repeat.GetNextDateAndTime();
        }
    }
}
