package se.ics.lu.repositories;

import se.ics.lu.models.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public List<Task> tasks = new ArrayList<Task>();
    public ArrayList<Task> completedTasks = new ArrayList<Task>();
    public ArrayList<Task> uncompletedTasks = new ArrayList<Task>();

    public TaskRepository() {
        try {
            loadLists();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void clearLists()
    {
        tasks.clear();
        completedTasks.clear();
        uncompletedTasks.clear();
    }

    public void loadLists()
    {
        try {
            tasks = JSONService.getTasksFromJson();

            for (Task t : tasks) {
                if (t.getStatus().equals("COMPLETED")) {
                    Task cloneTask = (Task) t.clone();
                    completedTasks.add(cloneTask);
                } else {
                    Task cloneTask = (Task) t.clone();
                    uncompletedTasks.add(cloneTask);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Task addTask(int id, String Description, String status) {
        return new Task(id, Description, status);
    }

    public void editTaskId(Task task, int newId) {
        task.setId(newId);
    }

    public void editTaskStatus(Task task, String newStatus) {
        task.setStatus(newStatus);
    }

    public void editTaskDescription(Task task, String newDescription) {
        task.setDescription(newDescription);
    }

    public void printTasks() throws Exception {
        List<Task> viewList = tasks;
    
        for (Task t : viewList) {
            System.out.println("Task ID: " + t.getId());
            System.out.println("Description: " + t.getDescription());
            System.out.println("Status: " + t.getStatus());
            System.out.println(); // For spacing between tasks
        }
    }

    public void printCompletedTasks() throws Exception {
        List<Task> viewList = completedTasks;
    
        for (Task t : viewList) {
            System.out.println("Task ID: " + t.getId());
            System.out.println("Description: " + t.getDescription());
            System.out.println("Status: " + t.getStatus());
            System.out.println(); // For spacing between tasks
        }
        
    }

    public void printUnfinishedTasks() throws Exception {
        List<Task> viewList = uncompletedTasks;
    
        for (Task t : viewList) {
            System.out.println("Task ID: " + t.getId());
            System.out.println("Description: " + t.getDescription());
            System.out.println("Status: " + t.getStatus());
            System.out.println(); // For spacing between tasks
        }
        
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(ArrayList<Task> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public ArrayList<Task> getUncompletedTasks() {
        return uncompletedTasks;
    }

    public void setUncompletedTasks(ArrayList<Task> uncompletedTasks) {
        this.uncompletedTasks = uncompletedTasks;
    }
}
