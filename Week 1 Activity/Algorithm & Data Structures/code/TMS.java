class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskManager {
    private Node head;

    public TaskManager() {
        head = null;
    }

    // Method to add a task to the linked list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Method to traverse and display all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Method to delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false; // List is empty
        }

        // If the head node is to be deleted
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }

        Node current = head;
        Node previous = null;
        while (current != null && current.task.getTaskId() != taskId) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false; // Task not found
        }

        // Unlink the node to delete it
        previous.next = current.next;
        return true;
    }
}

public class TMS {
    public static void main(String[] args) {
        // Create a task manager
        TaskManager taskManager = new TaskManager();

        // Add tasks
        taskManager.addTask(new Task(1, "Design Database Schema", "In Progress"));
        taskManager.addTask(new Task(2, "Implement Authentication", "Not Started"));
        taskManager.addTask(new Task(3, "Setup CI/CD Pipeline", "In Progress"));
        taskManager.addTask(new Task(4, "Conduct Code Review", "Completed"));

        // Traverse and display all tasks
        System.out.println("All Tasks:");
        taskManager.traverseTasks();

        // Search for a task by ID
        int searchId = 3;
        Task foundTask = taskManager.searchTask(searchId);
        if (foundTask != null) {
            System.out.println("Task found: " + foundTask);
        } else {
            System.out.println("Task with ID " + searchId + " not found.");
        }

        // Delete a task by ID
        int deleteId = 2;
        boolean deleted = taskManager.deleteTask(deleteId);
        if (deleted) {
            System.out.println("Task with ID " + deleteId + " deleted.");
        } else {
            System.out.println("Task with ID " + deleteId + " not found.");
        }

        // Traverse and display all tasks after deletion
        System.out.println("Tasks after deletion:");
        taskManager.traverseTasks();
    }
}
