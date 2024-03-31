package com.main;
import com.datastructures.sequential.*;
import java.util.Scanner;

public class ImplementationStack {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean bo = true;

        do {
            System.out.println("1. Stack of subjects");
            System.out.println("2. Stack of subject codes");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    Stack<String> stackSubjects = new Stack<>();
                    bo = stackOperationsString(stackSubjects, sc);
                    break;
                case 2:
                    Stack<Integer> stackCodes = new Stack<>();
                    bo = stackOperationsInteger(stackCodes, sc);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(bo);

    } // end of main

    private static int getChoice(Scanner sc) {
        int choice;

        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Top");
        System.out.println("4. Display");
        System.out.println("5. Is Empty?");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();

        return choice;
    } // end of getChoice

    public static boolean stackOperationsInteger(Stack<Integer> stack, Scanner sc) {
        int choice = 0;

        do {
            choice = getChoice(sc);

            switch(choice) {
                case 1:
                    System.out.print("Enter the element to push: ");
                    int element = sc.nextInt();
                    stack.push(element);
                    break;
                case 2:
                    System.out.println("Popped element: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Top element: " + stack.top());
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println("Is empty? " + stack.isEmpty());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(choice != 6);

        return false;
    } // end of stackOperationsInteger

    public static boolean stackOperationsString(Stack<String> stack, Scanner sc) {
        int choice = 0;

        do {
            choice = getChoice(sc);

            switch(choice) {
                case 1:
                    System.out.print("Enter the element to push: ");
                    String element = sc.next();
                    stack.push(element);
                    break;
                case 2:
                    System.out.println("Popped element: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Top element: " + stack.top());
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println("Is empty? " + stack.isEmpty());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(choice != 6);

        return false;
    } // end of stackOperationsString

}
