package ru.edu.pr02;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите часы за 5 дней через ;");
        String line = scanner.hasNextLine() ? scanner.nextLine() : null;
        try {
            int[] h = TimesheetAnalyzer.parseHours(line);
            System.out.println("Сумма: " + TimesheetAnalyzer.total(h));
            System.out.println("Среднее: " + TimesheetAnalyzer.average(h));
            System.out.println("Дней с переработкой: " + TimesheetAnalyzer.overtimeDays(h));
            System.out.println("Недельная переработка: " + TimesheetAnalyzer.weeklyOvertime(h));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
