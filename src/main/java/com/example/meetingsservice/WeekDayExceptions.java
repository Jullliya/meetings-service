package com.example.meetingsservice;

public class WeekDayExceptions extends Exception {
    public String toString() {
        return "This day is week! Choose another date.";
    }
}