import Tasks.Task;
import Tasks.WeeklyTask;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static TasksUtils tasksUtils = new TasksUtils();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание: ");
        String description = scanner.next();
        System.out.println("Введите тип задачи:\n" +
                "Если задача личная, то введите: 0 \t" +
                "Если задача рабочая, то введите: 1");
        System.out.print("Вы ввели: ");
        boolean isWork = tasksUtils.createIsWork(scanner.nextInt());
        System.out.println("Введите число от 0 до 4 для выбора повторяемости:\n" +
                "0. Однократня \t 1. Ежедневная \t 2. Еженедельная \t 3. Ежемесячная \t 4. Ежегодная");
        System.out.print("Вы ввели: ");
        int repeatable = scanner.nextInt();
        System.out.println("Введите дату начала задачи в формате дд.мм.гггг : ");
        String date = scanner.next();
        System.out.println("Введите время начала задачи в формате чч:мм : ");
        String time = scanner.next();
        LocalDateTime localDateTime = tasksUtils.createDateAndTime(date, time);
        Task task = tasksUtils.createTask(taskName, description, localDateTime, isWork, repeatable);
        tasksUtils.addTask(task);
        System.out.println("Созданные задачи:");
        System.out.println(tasksUtils.getTasks().toString());
    }

    private static void printMenu() {
        System.out.println("" +
                "1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "0. Выход");
    }
}