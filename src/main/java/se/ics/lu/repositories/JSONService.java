package se.ics.lu.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import se.ics.lu.utils.PathsUrl;

import se.ics.lu.models.Task;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONService {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void createNewTaskJson() {
        File taskJson = new File(PathsUrl.jsonPath);
        try {
            if (taskJson.createNewFile()) {
                System.out.println("File created: " + taskJson.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void taskListToJson(ArrayList<Task> tasks) {
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (Task t : tasks) {
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("id", t.getId());
            jsonNode.put("description", t.getDescription());
            jsonNode.put("status", t.getStatus());
            jsonNode.put("createdAt", t.getCreatedAt().toString());
            jsonNode.put("updatedAt", t.getUpdatedAt().toString());

            arrayNode.add(jsonNode);
        }

        try {
            objectMapper.writeValue(new File(PathsUrl.jsonPath), arrayNode);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void addTaskToJson(Task task) {

        File file = new File(PathsUrl.jsonPath);
        ArrayNode arrayNode;
        try {
            if (file.exists() && file.length() > 0) {
                arrayNode = (ArrayNode) objectMapper.readTree(file);
            } else {
                arrayNode = objectMapper.createArrayNode();
            }
            ObjectNode taskNode = objectMapper.createObjectNode();
            taskNode.put("id", task.getId());
            taskNode.put("description", task.getDescription());
            taskNode.put("status", task.getStatus());
            taskNode.put("createdAt", task.getCreatedAt().toString());
            taskNode.put("updatedAt", task.getUpdatedAt().toString());
            arrayNode.add(taskNode);
            objectMapper.writeValue(file, arrayNode);
            System.out.println("Task added to JSON: " + task.getId());
        } catch (IOException e) {
            System.out.println("Error appending task to JSON: " + e.getMessage());
        }
    }

    public static List<Task> getTasksFromJson() {
        try {

            objectMapper.registerModule(new JavaTimeModule()); // this line is key
            Path jsonPath = Paths.get(
                    "C:\\\\Users\\\\simon\\\\Documents\\\\GitHub\\\\TaskTracker\\\\src\\\\main\\\\java\\\\se\\\\ics\\\\lu\\\\resources\\\\taskList.json");
            String jsonContent = new String(Files.readAllBytes(jsonPath), StandardCharsets.UTF_8);

            return objectMapper.readValue(jsonContent, new TypeReference<List<Task>>() {
            });
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<Task>();
    }

}
