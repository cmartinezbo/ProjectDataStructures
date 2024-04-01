package com.main;

import com.datastructures.sequential.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestQueue {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int RANDOM_STRING_LENGTH = 10;

    public static List<String> generateRandomSubjects(int numberOfSubjects) {
        List<String> subjects = new ArrayList<>();
        for (int i = 0; i < numberOfSubjects; i++) {
            subjects.add(generateRandomString());
        }
        return subjects;
    }

    private static String generateRandomString() {
        StringBuilder randStr = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = random.nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public static void main(String[] args) {
        int numberOfSubjects = 20_000_000;

        List<String> randomSubjects = TestQueue.generateRandomSubjects(numberOfSubjects); // generates 10 random subjects

        Queue<String> queueSubjects = new Queue<>();

        long startTimeEnqueue, endTimeEnqueue, durationEnqueue;

        startTimeEnqueue = System.nanoTime();

        for (String subject : randomSubjects) {
            queueSubjects.enqueue(subject);
        }

        endTimeEnqueue = System.nanoTime();
        durationEnqueue = endTimeEnqueue - startTimeEnqueue;
        System.out.println("Number of subjects: " + numberOfSubjects);
        System.out.println("Time taken to enqueue subject: " + durationEnqueue + " nanoseconds");


        // Time taken to pop all subjects

        long startTimeDequeue, endTimeDequeue, durationDequeue;

        startTimeDequeue = System.nanoTime();

        while (!queueSubjects.isEmpty()) {
            queueSubjects.dequeue();
        }

        endTimeDequeue = System.nanoTime();
        durationDequeue = endTimeDequeue - startTimeDequeue;
        System.out.println("Time taken to dequeue all subjects: " + durationDequeue + " nanoseconds");
    }
}