package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyTask extends Task implements Repeatable{
    private LocalTime timeOfRepeat = LocalTime.of(getDateOfCompletion().getHour(), getDateOfCompletion().getMinute());

    public DailyTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    // Check the recurrence date, and only then check the current time and the time of the daily task
    @Override
    public LocalDateTime GetNextDateAndTime() {
        if (getDateOfCompletion().isAfter(LocalDateTime.now())) {
            return getDateOfCompletion();
        }
        if (timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeOfRepeat);
        }
        return LocalDateTime.of(LocalDate.now().plusDays(1), timeOfRepeat);
    }

    //If the start of the daily task is scheduled after the specified date, then it is not displayed
    @Override
    public boolean checkTaskOfDay(LocalDateTime data) {
        return getDateOfCompletion().isBefore(data);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи = 'Ежедневная'"+
                ", Дата ближайшего повторения = " + GetNextDateAndTime().toLocalDate() +
                ", Время повторения = "+ GetNextDateAndTime().toLocalTime();
    }
}
