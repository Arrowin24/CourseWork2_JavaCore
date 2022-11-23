package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class MonthlyTask extends Task implements Repeatable {


    public MonthlyTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public LocalDateTime getNextDateAndTime() {
        LocalTime timeOfRepeat = LocalTime.of(getDateOfCompletion().getHour(), getDateOfCompletion().getMinute());
        int repeatDayOfMonth = getDateOfCompletion().getDayOfMonth();
        if (getDateOfCompletion().isAfter(LocalDateTime.now())) {
            return getDateOfCompletion();
        }
        if (repeatDayOfMonth == LocalDate.now().getDayOfMonth() && timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeOfRepeat);
        }
        if (repeatDayOfMonth > LocalDateTime.now().getDayOfMonth()) {
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfMonth - LocalDateTime.now().getDayOfMonth()), timeOfRepeat);
        } else {
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()).plusDays(repeatDayOfMonth - 1), timeOfRepeat);
        }
    }

    @Override
    public boolean checkTaskOfDay(LocalDateTime data) {
        int repeatDayOfMonth = getDateOfCompletion().getDayOfMonth();
        if (getDateOfCompletion().isAfter(data)) {
            return false;
        }
        return data.getDayOfMonth() == repeatDayOfMonth;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи = 'Ежемесячная'" +
                ", Дата ближайшего повторения = " + getNextDateAndTime().toLocalDate() +
                ", Время повторения = " + getNextDateAndTime().toLocalTime();
    }
}
