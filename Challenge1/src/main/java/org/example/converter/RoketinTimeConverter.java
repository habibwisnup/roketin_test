package org.example.converter;

import org.example.model.Time;

public class RoketinTimeConverter extends TimeConverter {
    private static final double EARTH_SECONDS_IN_DAY = 24 * 60 * 60;
    private static final double ROKETIN_SECONDS_IN_DAY = 10 * 100 * 100;

    @Override
    public Time convert(Time earthTime) {
        int totalEarthSeconds = earthTime.toSeconds();
        int totalRoketinSeconds = (int) ((totalEarthSeconds / EARTH_SECONDS_IN_DAY) * ROKETIN_SECONDS_IN_DAY);

        int hours = totalRoketinSeconds / (100 * 100);
        int minutes = (totalRoketinSeconds % (100 * 100)) / 100;
        int seconds = totalRoketinSeconds % 100;

        return new Time(hours, minutes, seconds);
    }
}
