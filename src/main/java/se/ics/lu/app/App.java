package se.ics.lu.app;
import java.nio.file.Files;
import se.ics.lu.utils.AppEntry;
import se.ics.lu.utils.PathsUrl;
import se.ics.lu.repositories.JSONService;


public class App {
        public static void main(String[] args) throws Exception {
            if (!Files.exists(java.nio.file.Paths.get(PathsUrl.jsonPath))) {
                JSONService.createNewTaskJson();
            }
            AppEntry.launchMenu();
        }
    }

