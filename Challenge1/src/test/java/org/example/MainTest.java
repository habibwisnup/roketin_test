package org.example;

import org.example.converter.RoketinTimeConverter;
import org.example.model.Time;
import org.example.utils.TimeValidator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testValidTimeInput() {
        String input = "12 30 45\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Time time = TimeValidator.getValidTime(scanner);
        assertEquals("12:30:45", time.toString());
    }

    @Test
    void testTimeConversion() {
        Time earthTime = new Time(12, 0, 0);
        RoketinTimeConverter converter = new RoketinTimeConverter();
        Time roketinTime = converter.convert(earthTime);
        assertEquals("05:00:00", roketinTime.toString());
    }

    @Test
    void testInvalidTimeInput() {
        String input = "25 65 80\n12 30 45\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Time time = TimeValidator.getValidTime(scanner);
        assertEquals("12:30:45", time.toString());
    }
}