class Employee {
    private String fullName;
    private int pointLevel;
    private Ticket.Category assignedCategory;

    public Employee() {
        this("Krishna", 0, Ticket.Category.NEXTURN_ENGINEER);
        System.out.println("Warning: Employee not properly initialized. Please provide details.");
    }

    public Employee(String fullName, int pointLevel, Ticket.Category assignedCategory) {
        this.fullName = fullName;
        this.pointLevel = pointLevel;
        this.assignedCategory = assignedCategory;
    }

    public String getFullName() { return fullName; }
    public int getPointLevel() { return pointLevel; }
}

class Ticket {
    private int id, point;
    private String name, assignedEmployee = "";
    private Category category;
    private boolean isCompleted = false;

    public Ticket() {
        this(-1, "Uninitialized Ticket", Category.NEXTURN_ENGINEER, 0);
        System.out.println("Warning: Ticket not properly initialized.");
    }

    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
    }

    public int getId() { return id; }
    public int getPoint() { return point; }
    public boolean isCompleted() { return isCompleted; }
    public void assignEmployee(String employeeName) { assignedEmployee = employeeName; }
    public void markCompleted() { isCompleted = true; }

    public enum Category { NETWORKING, SOFTWARE, HARDWARE, SECURITY, NEXTURN_ENGINEER }
}

class HelpDesk {
    private Employee emp1, emp2;
    private Ticket[] tickets = new Ticket[5];

    public void addEmployee(Employee e, int pos) {
        if (pos == 1) emp1 = e;
        else if (pos == 2) emp2 = e;
        else System.out.println("Invalid position! Use 1 or 2.");
    }

    public void addTicket(Ticket t, int pos) {
        if (pos >= 1 && pos <= 5) tickets[pos - 1] = t;
        else System.out.println("Invalid position! Use 1-5.");
    }

    public void completeTicket(String employeeName, int ticketId) {
        Employee e = (emp1 != null && emp1.getFullName().equals(employeeName)) ? emp1 :
                     (emp2 != null && emp2.getFullName().equals(employeeName)) ? emp2 : null;
        if (e == null) { System.out.println("Employee not found."); return; }

        for (Ticket t : tickets) {
            if (t != null && t.getId() == ticketId) {
                if (t.isCompleted()) { System.out.println("Ticket already completed."); return; }
                if (e.getPointLevel() >= t.getPoint()) {
                    t.markCompleted();
                    System.out.println("Ticket " + ticketId + " completed by " + employeeName);
                } else {
                    System.out.println("Ticket " + ticketId + " cannot be completed by " + employeeName + " (Insufficient points).");
                }
                return;
            }
        }
        System.out.println("Ticket not found.");
    }

    public int getWaitingTicketCount() {
        int count = 0;
        for (Ticket t : tickets) if (t != null && !t.isCompleted()) count++;
        return count;
    }

    public int getCompletedTicketsTotalPoint() {
        int sum = 0;
        for (Ticket t : tickets) if (t != null && t.isCompleted()) sum += t.getPoint();
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();
        
        Employee alice = new Employee("Alice Brown", 5, Ticket.Category.SOFTWARE);
        Employee bob = new Employee("Bob Smith", 8, Ticket.Category.HARDWARE);
        helpDesk.addEmployee(alice, 1);
        helpDesk.addEmployee(bob, 2);
        
        Ticket t1 = new Ticket(101, "Software Bug", Ticket.Category.SOFTWARE, 4);
        Ticket t2 = new Ticket(102, "Network Issue", Ticket.Category.HARDWARE, 7);
        Ticket t3 = new Ticket(103, "System Crash", Ticket.Category.HARDWARE, 10);
        Ticket t4 = new Ticket(104, "Printer Not Working", Ticket.Category.HARDWARE, 3);
        Ticket t5 = new Ticket(105, "UI Bug", Ticket.Category.SOFTWARE, 2);
        
        helpDesk.addTicket(t1, 1);
        helpDesk.addTicket(t2, 2);
        helpDesk.addTicket(t3, 3);
        helpDesk.addTicket(t4, 4);
        helpDesk.addTicket(t5, 5);
        
        helpDesk.completeTicket("Alice Brown", 101);
        helpDesk.completeTicket("Bob Smith", 102);
        helpDesk.completeTicket("Alice Brown", 103);
        helpDesk.completeTicket("Bob Smith", 104);
        helpDesk.completeTicket("Alice Brown", 105);
        
        System.out.println(helpDesk.getWaitingTicketCount());
        System.out.println(helpDesk.getCompletedTicketsTotalPoint());
    }
}
