package tasks;

import java.time.LocalDateTime;

public interface Repeatable {
    public LocalDateTime getNextDateAndTime();
}
