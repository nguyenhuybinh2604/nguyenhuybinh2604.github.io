public class Employee extends Person {
    private double experience;
    private String placeWork;

    public Employee() {
    }

    public Employee(double experience, String placeWork) {
        
        this.experience = experience;
        this.placeWork = placeWork;
    }

    public double getExperience() {
        return this.experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public String getPlaceWork() {
        return this.placeWork;
    }

    public void setPlaceWork(String placeWork) {
        this.placeWork = placeWork;
    }

    @Override
    public String toString() {
        return super.toString() +
                " experience='" + getExperience() + "'" +
                ", placeWork='" + getPlaceWork() + "'" +
                "}";
    }

}