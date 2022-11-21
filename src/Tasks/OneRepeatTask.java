package Tasks;

import java.time.LocalDateTime;

public class OneRepeatTask extends Task {

    public OneRepeatTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public boolean checkTaskOfDay(LocalDateTime data) {
        return data.equals(getDateOfCompletion());
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи = 'Одноразовая'";
    }
}
