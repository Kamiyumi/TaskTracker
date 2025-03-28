package se.ics.lu.utils;

import java.util.Scanner;
import se.ics.lu.repositories.TaskRepository;

public class AppEntry {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskRepository taskRepository = new TaskRepository();

    public static void launchMenu() throws Exception {
        boolean isRunning = true;
        System.out.println("Welcome to task-cli! Type 'help' for available commands.\n");

        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "add":
                    // TODO: Prompt for description and implement add task
                    System.out.println("Adding a task...");
                    break;

                case "update":
                    // TODO: Prompt for ID and description, then update task
                    System.out.println("Updating a task...");
                    break;

                case "delete":
                    // TODO: Prompt for ID and delete task
                    System.out.println("Deleting a task...");
                    break;

                case "mark-in-progress":
                    // TODO: Prompt for ID and mark task in progress
                    System.out.println("Marking task as in-progress...");
                    break;

                case "mark-done":
                    // TODO: Prompt for ID and mark task as done
                    System.out.println("Marking task as done...");
                    break;

                case "list":
                    // TODO: Implement list all tasks
                    System.out.println("Listing all tasks...");
                    taskRepository.printTasks();
                    break;

                case "list done":
                    // TODO: Implement list completed tasks
                    System.out.println("Listing completed tasks...");
                    taskRepository.printCompletedTasks();
                    break;

                case "list in-progress":
                    // TODO: Implement list in-progress tasks
                    System.out.println("Listing in-progress tasks...");
                    taskRepository.printUnfinishedTasks();
                    break;

                case "list todo":
                    // TODO: Implement list unfinished tasks
                    System.out.println("Listing todo tasks...");
                    taskRepository.printUnfinishedTasks();
                    break;

                case "exit":
                    isRunning = false;
                    System.out.println("Exiting task-cli. Goodbye!");
                    break;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for available options.");
                    break;
            }
        }

        scanner.close();
    }


    




    private static void printHelp() {
        System.out.println("""
Available commands:
  add
  update
  delete
  mark-in-progress
  mark-done
  list
  list done
  list todo
  list in-progress
  exit
""");
    }
}
