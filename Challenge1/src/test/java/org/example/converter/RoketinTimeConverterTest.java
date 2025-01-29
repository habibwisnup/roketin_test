package org.example.converter;

import org.example.model.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoketinTimeConverterTest {
    private final RoketinTimeConverter converter = new RoketinTimeConverter();

    @Test
    void testConvertEarthTimeToRoketinTime() {
        Time earthTime = new Time(12, 0, 0);
        Time expectedRoketinTime = new Time(05, 0, 0);

        Time actualRoketinTime = converter.convert(earthTime);

        assertEquals(expectedRoketinTime.toString(), actualRoketinTime.toString());
    }

    @Test
    void testConvertEdgeCaseMidnight() {
        Time earthTime = new Time(0, 0, 0);
        Time expectedRoketinTime = new Time(0, 0, 0);

        Time actualRoketinTime = converter.convert(earthTime);

        assertEquals(expectedRoketinTime.toString(), actualRoketinTime.toString());
    }

    @Test
    void testConvertEarthTimeWithMinutesAndSeconds() {
        Time earthTime = new Time(6, 30, 45);
        Time expectedRoketinTime = new Time(02, 71, 35);

        Time actualRoketinTime = converter.convert(earthTime);

        assertEquals(expectedRoketinTime.toString(), actualRoketinTime.toString());
    }

    @Test
    void testConvertEdgeCaseMaxTime() {
        Time earthTime = new Time(23, 59, 59);
        Time expectedRoketinTime = new Time(9, 99, 98);

        Time actualRoketinTime = converter.convert(earthTime);

        assertEquals(expectedRoketinTime.toString(), actualRoketinTime.toString());
    }
}