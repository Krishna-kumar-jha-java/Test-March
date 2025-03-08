public class Ticket {
    // Properties
    private int id;
    private String name;
    private Category category;
    private int point;
    private String assignedEmployee;
    private boolean isCompleted;

    // Default Constructor
    public Ticket() {
        this.id = -1;
        this.name = "Uninitialized Ticket";
        this.category = Category.UNKNOWN;
        this.point = 0;
        this.assignedEmployee = ""; // Initially empty
        this.isCompleted = false; // Initially false
        System.out.println("Warning: Ticket object is not initialized properly. Please provide valid details.");
    }

    // Parameterized Constructor
    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
        this.assignedEmployee = ""; // Initially empty
        this.isCompleted = false; // Initially false
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPoint() {
        return point;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void assignEmployee(String employeeName) {
        this.assignedEmployee = employeeName;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    // Display ticket details
    public void displayTicketInfo() {
        System.out.println("Ticket ID: " + id);
        System.out.println("Description: " + name);
        System.out.println("Category: " + category);
        System.out.println("Complexity Point: " + point);
        System.out.println("Assigned Employee: " + (assignedEmployee.isEmpty() ? "Not Assigned" : assignedEmployee));
        System.out.println("Status: " + (isCompleted ? "Completed" : "Pending"));
    }

    // Enum for Category
    public enum Category {
        NETWORKING, SOFTWARE, HARDWARE, SECURITY, UNKNOWN
    }

    // Main method for testing
    public static void main(String[] args) {
        // Creating a Ticket with default constructor
        Ticket defaultTicket = new Ticket();
        defaultTicket.displayTicketInfo();

        // Creating a Ticket with parameters
        Ticket ticket1 = new Ticket(101, "Fix network issue", Category.NETWORKING, 3);
        ticket1.displayTicketInfo();
    }
}
