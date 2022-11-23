package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class YearlyTask extends Task implements Repeatable {
    public YearlyTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public LocalDateTime getNextDateAndTime() {
        LocalTime timeOfRepeat = LocalTime.of(getDateOfCompletion().getHour(), getDateOfCompletion().getMinute());
        int repeatDayOfYear = getDateOfCompletion().getDayOfYear();
        if (getDateOfCompletion().isAfter(LocalDateTime.now())) {
            return getDateOfCompletion();
        }
        if (repeatDayOfYear == LocalDate.now().getDayOfYear() && timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeOfRepeat);
        }
        if (repeatDayOfYear > LocalDateTime.now().getDayOfYear()) {
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfYear - LocalDateTime.now().getDayOfMonth()), timeOfRepeat);
        } else {
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear()).plusDays(repeatDayOfYear - 1), timeOfRepeat);
        }
    }

    @Override
    public boolean checkTaskOfDay(LocalDateTime data) {
        int repeatDayOfYear = getDateOfCompletion().getDayOfYear();
        if (getDateOfCompletion().isAfter(data)) {
            return false;
        }
        return data.getDayOfYear() == repeatDayOfYear;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи = 'Ежегодная'" +
                ", Дата ближайшего повторения = " + getNextDateAndTime().toLocalDate() +
                ", Время повторения = " + getNextDateAndTime().toLocalTime();
    }
}
