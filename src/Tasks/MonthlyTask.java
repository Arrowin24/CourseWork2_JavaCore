package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class MonthlyTask extends Task implements Repeatable{
    private LocalTime timeOfRepeat = LocalTime.of(getDateOfCompletion().getHour(), getDateOfCompletion().getMinute());
    private int repeatDayOfMonth = getDateOfCompletion().getDayOfMonth();

    public MonthlyTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public LocalDateTime GetNextDateAndTime() {
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
    public boolean checkTaskDaily(LocalDateTime data) {
        if (getDateOfCompletion().isAfter(data)) {
            return false;
        }
        return data.getDayOfMonth()==repeatDayOfMonth;
    }
    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи = 'Ежемесячная'" +
                ", Дата ближайшего повторения = " + GetNextDateAndTime().toLocalDate() +
                ", Время повторения = "+ GetNextDateAndTime().toLocalTime();
    }
}
