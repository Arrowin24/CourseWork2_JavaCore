package Tasks;

import java.time.LocalDateTime;

public class OneRepeatTask extends Task implements Repeatable {

    public OneRepeatTask(String headline, String description, boolean isWorkTask, LocalDateTime dateOfCompletion) {
        super(headline, description, isWorkTask, dateOfCompletion);
    }

    @Override
    public LocalDateTime GetNextDateAndTime() {
        return getDateOfCompletion();
    }

    @Override
    public boolean checkTaskDaily(LocalDateTime data) {
        return data.equals(getDateOfCompletion());
    }


}
