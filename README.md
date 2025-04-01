# 🧩 TaskTracker CLI 

A command-line task management tool written in **Java**, designed to help you track your tasks with ease. This app follows clean architectural principles with separation of concerns across models, repositories, services, factories, and utilities.
Created as part of the roadmap.sh backend developer track --> https://roadmap.sh/projects/task-tracker
---

## ✨ Features

- ✅ Add, update, and delete tasks
- 🔁 Mark tasks as **in-progress** or **completed**
- 📃 List all tasks or filter by status: **done**, **in-progress**, or **todo**
- 💾 Tasks are persisted in a local `JSON` file (no external DB or frameworks)
- 🧠 Functional `Listenable` interface to update state on task modification
- 🧼 Clean, modular Java architecture

---

## 🗂️ Project Structure

## 🏗️ Architecture Overview

src/main/java/
├── se.ics.lu.app             # Application entry point (App.java)
├── se.ics.lu.enums           # Enum types (e.g., StatusTypes)
├── se.ics.lu.factories       # TaskFactory: create, update, delete tasks
├── se.ics.lu.interfaces      # Listenable interface for task update events
├── se.ics.lu.models          # Task model class
├── se.ics.lu.repositories    # JSONService and TaskRepository
├── se.ics.lu.resources       # JSON file for storing task data
│   └── taskList.json
├── se.ics.lu.utils           # PathsUrl and UserValidation utilities

## 🧪 Usage (via CLI)

```bash
# Adding a task
task-cli add

# Updating or deleting
task-cli update
task-cli delete

# Marking status
task-cli mark-in-progress
task-cli mark-done

# Listing tasks
task-cli list
task-cli list done
task-cli list todo
task-cli list in-progress
