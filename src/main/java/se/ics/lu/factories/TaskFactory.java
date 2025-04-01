package se.ics.lu.factories;

import se.ics.lu.models.Task;
import se.ics.lu.repositories.JSONService;
import se.ics.lu.repositories.Listenable;
import se.ics.lu.utils.PathsUrl;
import se.ics.lu.utils.UserValidation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TaskFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final Path jsonPath = Paths.get(PathsUrl.jsonPath);

    public static void createTask(Listenable listener) {
        int id = UserValidation.getValidId();
        String status = UserValidation.getValidStatus();
        String description = UserValidation.getNonEmptyInput("Input the Task Description: ");
        Task task = new Task(id, description, status);
        JSONService.addTaskToJson(task);
        listener.onTasksChanged();
    }

    public static void updateTask(int id, Scanner scanner, Listenable listener) {

        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();

        try {
            String jsonContent = new String(Files.readAllBytes(jsonPath), StandardCharsets.UTF_8);
            List<Task> tasks = objectMapper.readValue(jsonContent, new TypeReference<List<Task>>() {
            });

            boolean found = false;

            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setDescription(newDescription);
                    task.setUpdatedAt();
                    found = true;
                    break;
                }
            }

            if (found) {
                objectMapper.writeValue(jsonPath.toFile(), tasks);
                System.out.println("Task updated!");
                listener.onTasksChanged();
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateStatus(int id, Scanner scanner, Listenable listener, String status) {

        try {
            String jsonContent = new String(Files.readAllBytes(jsonPath), StandardCharsets.UTF_8);
            List<Task> tasks = objectMapper.readValue(jsonContent, new TypeReference<List<Task>>() {
            });

            boolean found = false;

            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setStatus(status);;
                    task.setUpdatedAt();
                    found = true;
                    break;
                }
            }
            if (found) {
                objectMapper.writeValue(jsonPath.toFile(), tasks);
                System.out.println("Task Status of Task ID:" + id + "updated to" + status);
                listener.onTasksChanged();
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteTask(int id, Listenable listener) {
        try {
            String jsonContent = new String(Files.readAllBytes(jsonPath), StandardCharsets.UTF_8);
            List<Task> tasks = objectMapper.readValue(jsonContent, new TypeReference<List<Task>>() {});
    
            boolean found = false;
    
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getId() == id) {
                    iterator.remove();  // ✅ removes it from the list
                    found = true;
                    break;
                }
            }
    
            if (found) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonPath.toFile(), tasks);
                System.out.println("Task " + id + " has been deleted.");
                listener.onTasksChanged();
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
