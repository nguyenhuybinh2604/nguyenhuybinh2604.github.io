public class Doctor extends Person {
    double workingHour;
    String department;

    public Doctor() {
    }

    public Doctor(String name, int age, String address, double workingHour, String department) {
        super(name, age, address);
        this.workingHour = workingHour;
        this.department = department;
    }

    public double getWorkingHour() {
        return this.workingHour;
    }

    public void setWorkingHour(double workingHour) {
        this.workingHour = workingHour;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() +
        " So gio lam=" + getWorkingHour() + " " +
                ", Chuyen khoa=" + getDepartment() + " ";
    }

}
