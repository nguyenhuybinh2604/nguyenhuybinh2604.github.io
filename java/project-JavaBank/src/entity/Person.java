package entity;

public abstract class Person {
    protected String personId;
    protected String name;
    protected String gender;
    protected int age;
    protected String address;

    public Person() {

    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                " personId='" + getPersonId() + "'" +
                ", name='" + getName() + "'" +
                ", gender='" + getGender() + "'" +
                ", age='" + getAge() + "'" +
                ", address='" + getAddress() + "'" +
                "}";
    }

}