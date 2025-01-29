package org.example;

import org.example.converter.RoketinTimeConverter;
import org.example.converter.TimeConverter;
import org.example.model.Time;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Earth time (hours minutes seconds): ");
        int earthHours = scanner.nextInt();
        int earthMinutes = scanner.nextInt();
        int earthSeconds = scanner.nextInt();

        Time earthTime = new Time(earthHours, earthMinutes, earthSeconds);
        TimeConverter converter = new RoketinTimeConverter();
        Time roketinTime = converter.convert(earthTime);

        System.out.println("On Earth: " + earthTime + ", On Roketin Planet: " + roketinTime);

        scanner.close();
    }
}