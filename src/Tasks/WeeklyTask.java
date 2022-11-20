package Tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task implements Repeatable {

    private LocalTime timeOfRepeat = LocalTime.of(getDateOfCompletion().getHour(), getDateOfCompletion().getMinute());
    private DayOfWeek repeatDayOfWeek = DayOfWeek.of(getDateOfCompletion().getDayOfWeek().getValue());

    public WeeklyTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public LocalDateTime GetNextDateAndTime() {
        if (getDateOfCompletion().isAfter(LocalDateTime.now())) {
            return getDateOfCompletion();
        }
        if (repeatDayOfWeek.equals(LocalDate.now().getDayOfWeek()) && timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeOfRepeat);
        }
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.next(repeatDayOfWeek)), timeOfRepeat);
    }

    @Override
    public boolean checkTaskDaily(LocalDateTime data) {
        if (getDateOfCompletion().isAfter(data)) {
            return false;
        }
        return data.getDayOfWeek().equals(repeatDayOfWeek);
    }
}
