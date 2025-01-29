package org.example;

import org.example.converter.RoketinTimeConverter;
import org.example.converter.TimeConverter;
import org.example.model.Time;
import org.example.utils.TimeValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Time earthTime = TimeValidator.getValidTime(scanner);

        TimeConverter converter = new RoketinTimeConverter();
        Time roketinTime = converter.convert(earthTime);

        System.out.println("On Earth: " + earthTime + ", On Roketin Planet: " + roketinTime);

        scanner.close();
    }
}