package entity;

public class Techmaster {
    private Manager manager;
    private Teacher teacher;
    private Group group;

    public Techmaster() {
    }

    public Techmaster(Manager manager, Teacher teacher, Group group) {
        this.manager = manager;
        this.teacher = teacher;
        this.group = group;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {

        return 
                " Quan ly: " + this.manager.toString() + " \n" +
                " Giang vien: " + this.teacher.toString() + " \n" +
                " Lop: \n " + this.group.toString() + " ";

    }

}
