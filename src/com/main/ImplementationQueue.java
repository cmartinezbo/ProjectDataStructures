package com.main;
import com.datastructures.sequential.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ImplementationQueue {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean bo = true;

        try {
            do {
                System.out.println("1. Queue of subjects");
                System.out.println("2. Queue of subject codes");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch(choice) {
                    case 1:
                        Queue<String> stackSubjects = new Queue<>();
                        bo = stackOperationsString(stackSubjects, sc);
                        break;
                    case 2:
                        Queue<Integer> stackCodes = new Queue<>();
                        bo = stackOperationsInteger(stackCodes, sc);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while(bo);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
            System.out.println("Exiting...");
        }

    } // end of main

    private static int getChoice(Scanner sc) {
        int choice;

        System.out.println("Menu");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Display");
        System.out.println("4. Is Empty?");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();

        return choice;
    } // end of getChoice

    public static boolean stackOperationsInteger(Queue<Integer> queue, Scanner sc) {

        int choice = 0;
        Scanner scInt = new Scanner(System.in);

        try {
            do {
                choice = getChoice(sc);

                switch(choice) {
                    case 1:
                        System.out.print("Enter the element to push: ");
                        int element = scInt.nextInt();
                        queue.enqueue(element);
                        break;
                    case 2:
                        System.out.println("Popped element: " + queue.dequeue());
                        break;
                    case 3:
                        queue.display();
                        break;
                    case 4:
                        System.out.println("Is empty? " + queue.isEmpty());
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while(choice != 5);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
            System.out.println("Exiting...");
        }

        scInt.close();

        return false;
    } // end of stackOperationsInteger

    public static boolean stackOperationsString(Queue<String> queue, Scanner sc) {
        int choice = 0;
        Scanner scStr = new Scanner(System.in);

        try {
            do {
                choice = getChoice(sc);

                switch(choice) {
                    case 1:
                        System.out.print("Enter the element to push: ");
                        String element = scStr.nextLine();
                        queue.enqueue(element);
                        break;
                    case 2:
                        System.out.println("Popped element: " + queue.dequeue());
                        break;
                    case 3:
                        queue.display();
                        break;
                    case 4:
                        System.out.println("Is empty? " + queue.isEmpty());
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while(choice != 5);
        }  catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
            System.out.println("Exiting...");
        }
        scStr.close();

        return false;
    } // end of stackOperationsString

}

