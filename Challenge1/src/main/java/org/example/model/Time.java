package org.example.model;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int toSeconds() {
        return (hours * 3600) + (minutes * 60) + seconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
