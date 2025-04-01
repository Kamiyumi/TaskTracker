package se.ics.lu.app;

import java.nio.file.Files;
import java.util.Scanner;

import se.ics.lu.utils.PathsUrl;
import se.ics.lu.factories.TaskFactory;
import se.ics.lu.repositories.JSONService;
import se.ics.lu.repositories.TaskRepository;

public class App {

    public static void main(String[] args) throws Exception {
        if (!Files.exists(java.nio.file.Paths.get(PathsUrl.jsonPath))) {
            JSONService.createNewTaskJson();
        }
        launchMenu();
    }

    public static void launchMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        TaskRepository taskRepository = new TaskRepository();
        boolean isRunning = true;
        System.out.println("Welcome to task-cli! Type 'help' for available commands.\n");
        String input;

        printHelp();

        while (isRunning) {
            System.out.print("> ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "add":
                    TaskFactory.createTask(taskRepository);
                    break;

                case "update":
                    System.out.println("Input the task ID to update:");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    TaskFactory.updateTask(updateId, scanner, taskRepository);
                    System.out.println("Updating a task...");
                    break;

                case "delete":
                    System.out.println("Input the task ID to delete:");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    TaskFactory.deleteTask(deleteId, taskRepository);
                    System.out.println("Deleting a task...");
                    break;

                case "mark-in-progress":
                    System.out.println("Input the task ID to mark as \"IN-PROGRESS\":");
                    int statusProgressId = Integer.parseInt(scanner.nextLine());
                    TaskFactory.updateStatus(statusProgressId, scanner, taskRepository, "IN-PROGRESS");
                    System.out.println("Marking task as in-progress...");
                    break;

                case "mark-done":
                    System.out.println("Input the task ID:");
                    int statusDoneId = Integer.parseInt(scanner.nextLine());
                    TaskFactory.updateStatus(statusDoneId, scanner, taskRepository, "COMPLETED");
                    System.out.println("Marking task as done...");
                    break;

                case "list":
                    System.out.println("Listing all tasks...");
                    taskRepository.printTasks();
                    break;

                case "list done":
                    System.out.println("Listing completed tasks...");
                    taskRepository.printCompletedTasks();
                    break;

                case "list in-progress":
                    System.out.println("Listing in-progress tasks...");
                    taskRepository.printUnfinishedTasks();
                    break;

                case "list todo":
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
