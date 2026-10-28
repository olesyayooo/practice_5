package ru.edu.pr02;

public class TimesheetAnalyzer {
    public static final int DAYS = 5;
    public static final int MAX_HOURS = 16;
    public static final int DAILY_NORM = 8;
    public static final int WEEKLY_NORM = 35; // Вариант 6

    public static int[] parseHours(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Позиция 1: отсутствует значение");
        }
        String[] parts = line.split(";", -1);
        if (parts.length != DAYS) {
            int position = parts.length < DAYS ? parts.length + 1 : DAYS + 1;
            throw new IllegalArgumentException("Позиция " + position
                    + ": требуется ровно " + DAYS + " значений, получено " + parts.length);
        }
        int[] hours = new int[DAYS];
        for (int i = 0; i < parts.length; i++) {
            String value = parts[i].trim();
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Позиция " + (i + 1) + ": пустое значение");
            }
            try {
                hours[i] = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Позиция " + (i + 1) + ": требуется целое число");
            }
            if (hours[i] < 0 || hours[i] > MAX_HOURS) {
                throw new IllegalArgumentException("Позиция " + (i + 1)
                        + ": часы должны быть от 0 до " + MAX_HOURS);
            }
        }
        return hours;
    }
    // Расчётные методы получают проверенный массив из parseHours.
    public static int total(int[] hours) {
        int sum = 0;
        for (int i = 0; i < hours.length; i++) sum += hours[i];
        return sum;
    }
    public static double average(int[] hours) {
        return (double) total(hours) / hours.length;
    }
    public static int overtimeDays(int[] hours) {
        int count = 0;
        int i = 0;
        while (i < hours.length) {
            if (hours[i] > DAILY_NORM) count++;
            i++;
        }
        return count;
    }
    public static int weeklyOvertime(int[] hours) {
        return Math.max(0, total(hours) - WEEKLY_NORM);
    }
}
