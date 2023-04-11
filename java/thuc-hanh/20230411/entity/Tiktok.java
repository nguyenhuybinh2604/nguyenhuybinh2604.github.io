package entity;

import java.util.List;

public class Tiktok {
    public static int autoid;
    private int id;
    private List<Idol> idols;
    private List<Song> songs;


    public Tiktok() {
    }

    public Tiktok(List<Idol> idols, List<Song> songs) {
        this.id = ++autoid;
        this.idols = idols;
        this.songs = songs;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = ++autoid;
    }

    public List<Idol> getidols() {
        return this.idols;
    }

    public void setidols(List<Idol> idols) {
        this.idols = idols;
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Tiktok idols(List<Idol> idols) {
        setidols(idols);
        return this;
    }

    public Tiktok songs(List<Song> songs) {
        setSongs(songs);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idols='" + getidols() + "'" +
            ", songs='" + getSongs() + "'" +
            "}";
    }
 

}
