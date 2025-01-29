package org.example.utils;

import org.example.model.Time;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TimeValidator {
    public static Time getValidTime(Scanner scanner) {
        int hours, minutes, seconds;

        while (true) {
            try {
                System.out.print("Enter Earth time (hours minutes seconds): ");

                hours = scanner.nextInt();
                if (hours < 0 || hours >= 24) {
                    throw new IllegalArgumentException("Invalid hours! Please enter a value between 0 and 23.");
                }

                minutes = scanner.nextInt();
                if (minutes < 0 || minutes >= 60) {
                    throw new IllegalArgumentException("Invalid minutes! Please enter a value between 0 and 59.");
                }

                seconds = scanner.nextInt();
                if (seconds < 0 || seconds >= 60) {
                    throw new IllegalArgumentException("Invalid seconds! Please enter a value between 0 and 59.");
                }

                return new Time(hours, minutes, seconds);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter integers only.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
