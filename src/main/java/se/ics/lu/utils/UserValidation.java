package se.ics.lu.utils;

import java.util.List;
import java.util.Scanner;

import se.ics.lu.enums.StatusTypes;
import se.ics.lu.models.Task;
import se.ics.lu.repositories.JSONService;

public class UserValidation {

    public static Scanner scanner = new Scanner(System.in);
    public static List<Task> listTask = JSONService.getTasksFromJson();

    public static int getValidId() {
        while (true) {
            try {
                int id = Integer.parseInt(prompt("Input the Task ID: "));
                if (id >= 0 && listTask.stream().noneMatch(t -> t.getId() == id)) {
                    return id;
                }
                System.out.println("ID is invalid or already exists. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
    
    public static String getValidStatus() {
        while (true) {
            String input = prompt("Input the Task Status: ").trim().toUpperCase();
            for (StatusTypes st : StatusTypes.values()) {
                if (st.toString().equalsIgnoreCase(input)) {
                    return st.toString();
                }
            }
            printAvailableStatuses();
        }
    }
    
    public static String getNonEmptyInput(String message) {
        while (true) {
            String input = prompt(message).trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty. Try again.");
        }
    }
    
    public static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    
    public static void printAvailableStatuses() {
        System.out.println("AVAILABLE STATUS TYPES:");
        for (StatusTypes st : StatusTypes.values()) {
            System.out.println("- " + st);
        }
    }

}
