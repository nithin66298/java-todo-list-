import java.io.*;
import java.util.*;

public class Todolist {
    private static final String TASKS_FILE = "tasks.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== 📌 TO-DO LIST MENU ======");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    viewTasks();
                    break;
                case "2":

                
                    addTask(sc);
                    break;
                case "3":
                    deleteTask(sc);
                    break;
                case "4":
                    System.out.println("👋 Goodbye!");
                    saveTasks();
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }

    private static void loadTasks() {
        try {
            File file = new File(TASKS_FILE);
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    tasks.add(reader.nextLine());
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading tasks.");
        }
    }

    private static void saveTasks() {
        try {
            FileWriter writer = new FileWriter(TASKS_FILE);
            for (String task : tasks) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("⚠️ Error saving tasks.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\n📭 No tasks found!");
        } else {
            System.out.println("\n📋 Your To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner sc) {
        System.out.print("✏️ Enter new task: ");
        String task = sc.nextLine().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            saveTasks();
            System.out.println("✅ Task added successfully!");
        } else {
            System.out.println("⚠️ Task cannot be empty.");
        }
    }

    private static void deleteTask(Scanner sc) {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter task number to delete: ");
            try {
                int index = Integer.parseInt(sc.nextLine()) - 1;
                if (index >= 0 && index < tasks.size()) {
                    String removed = tasks.remove(index);
                    saveTasks();
                    System.out.println("🗑️ Task '" + removed + "' deleted successfully!");
                } else {
                    System.out.println("⚠️ Invalid task number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number.");
            }
        }
    }
}
