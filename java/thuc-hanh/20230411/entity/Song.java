package entity;

public class Song {
    public static int autoid;
    private int id;
    private String name;
    private String singer;

    public Song() {
    }

    public Song(String name, String singer) {
        this.id = ++autoid;
        this.name = name;
        this.singer = singer;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = ++autoid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return this.singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Song name(String name) {
        setName(name);
        return this;
    }

    public Song singer(String singer) {
        setSinger(singer);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", singer='" + getSinger() + "'" +
            "}";
    }


    
}
