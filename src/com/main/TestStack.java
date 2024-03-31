package com.main;

import com.datastructures.sequential.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestStack {

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
        int numberOfSubjects = 10_000;

        List<String> randomSubjects = TestStack.generateRandomSubjects(numberOfSubjects); // generates 10 random subjects

        Stack<String> stackSubjects = new Stack<>();

        long startTimePush, endTimePush, durationPush;

        startTimePush = System.nanoTime();

        for (String subject : randomSubjects) {
            stackSubjects.push(subject);
        }

        endTimePush = System.nanoTime();
        durationPush = endTimePush - startTimePush;
        System.out.println("Number of subjects: " + numberOfSubjects);
        System.out.println("Time taken to push subject: " + durationPush + " nanoseconds");


        // Time taken to pop all subjects

        long startTimePop, endTimePop, durationPop;

        startTimePop = System.nanoTime();

        while (!stackSubjects.isEmpty()) {
            stackSubjects.pop();
        }

        endTimePop = System.nanoTime();
        durationPop = endTimePop - startTimePop;
        System.out.println("Time taken to pop all subjects: " + durationPop + " nanoseconds");
    }
}
