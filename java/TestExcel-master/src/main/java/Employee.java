import java.util.Date;

public class Employee {
    private String name;
    private String email;
    private Date dateOfBirth;
    private double daysOfWork;
    private double salaryPerDay;
    private Double totalSalary;

    public Employee(String name, String email, Date dateOfBirth, double daysOfWork, double salaryPerDay, Double totalSalary) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.daysOfWork = daysOfWork;
        this.salaryPerDay = salaryPerDay;
        this.totalSalary = totalSalary;
    }
}
