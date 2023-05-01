package com.example.meetingsservice;

public class AlreadyExistsExceprions extends Exception {
    public String toString() {
        return "Meeting on this datetime already exist! Choose another datetime.";
    }
}
