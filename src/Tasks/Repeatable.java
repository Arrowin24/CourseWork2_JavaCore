package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Repeatable {
    public LocalDateTime GetNextDateAndTime();
    public boolean checkTaskDaily(LocalDateTime data);
    
}
