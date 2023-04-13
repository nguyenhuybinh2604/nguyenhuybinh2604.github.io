package entity;

public class Pet {
    
    public static int autoid;
    private int id;
    private String name;
    private String species;
    private int age;
    private String sex;
    private String description;
    private TYPE type;
    private String images;

    public Pet() {
        this.id = ++autoid;
    }

    public Pet(String name, String species, int age, String sex, String description, TYPE type, String images) {
        this.id = ++autoid;
        this.name = name;
        this.species = species;
        this.age = age;
        this.sex = sex;
        this.description = description;
        this.images = images;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return this.images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Pet id(int id) {
        setId(id);
        return this;
    }

    public Pet name(String name) {
        setName(name);
        return this;
    }

    public Pet species(String species) {
        setSpecies(species);
        return this;
    }

    public Pet age(int age) {
        setAge(age);
        return this;
    }

    public Pet sex(String sex) {
        setSex(sex);
        return this;
    }

    public Pet description(String description) {
        setDescription(description);
        return this;
    }

    public Pet images(String images) {
        setImages(images);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", species='" + getSpecies() + "'" +
            ", age='" + getAge() + "'" +
            ", sex='" + getSex() + "'" +
            ", description='" + getDescription() + "'" +
            ", images='" + getImages() + "'" +
            "}";
    }
    
}
