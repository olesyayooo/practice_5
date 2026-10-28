package ru.edu.pr02;
public class ExtraCheck {
    private static int passed = 0;
    private ExtraCheck() { }
    private static void valid(String line, int total, double average, int days, int weekly) {
        int[] h = TimesheetAnalyzer.parseHours(line);
        if (TimesheetAnalyzer.total(h) != total
                || Math.abs(TimesheetAnalyzer.average(h) - average) > 1e-9
                || TimesheetAnalyzer.overtimeDays(h) != days
                || TimesheetAnalyzer.weeklyOvertime(h) != weekly) {
            throw new AssertionError("Неверный результат: " + line);
        }
        passed++;
        System.out.println("OK: " + line + " => " + total + ", " + average + ", " + days + ", " + weekly);
    }
    private static void invalid(String line, String message) {
        try {
            TimesheetAnalyzer.parseHours(line);
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains(message)) throw new AssertionError(e.getMessage());
            passed++;
            System.out.println("OK: " + String.valueOf(line) + " => " + e.getMessage());
            return;
        }
        throw new AssertionError("Ожидалась ошибка: " + line);
    }
    public static void main(String[] args) {
        valid("8;8;8;8;8", 40, 8.0, 0, 5);
        valid("0;0;0;0;0", 0, 0.0, 0, 0);
        valid("16;16;16;16;16", 80, 16.0, 5, 45);
        valid("7;7;7;7;7", 35, 7.0, 0, 0);
        valid("8;7;7;7;7", 36, 7.2, 0, 1);
        valid("9;8;0;10;7", 34, 6.8, 2, 0);
        valid(" 8 ; 8;8;8;8 ", 40, 8.0, 0, 5);
        invalid("", "Позиция 1");
        invalid(null, "Позиция 1");
        invalid("8;17;8;8;8", "Позиция 2");
        invalid("-1;8;8;8;8", "Позиция 1");
        invalid("8;8;abc;8;8", "Позиция 3");
        invalid("8;8;8;8;", "Позиция 5");
        invalid("8;8;8;8", "Позиция 5");
        invalid("8;8;8;8;8;8", "Позиция 6");
        invalid("8;8;8.5;8;8", "Позиция 3");
        invalid("8;999999999999;8;8;8", "Позиция 2");
        invalid("8;;8;8;8", "Позиция 2");
        System.out.println("Пройдено проверок: " + passed);
    }
}
