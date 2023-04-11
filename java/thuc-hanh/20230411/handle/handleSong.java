package handle;

import java.util.List;
import java.util.Scanner;

import entity.Song;

public class handleSong {
    public Song newSong(Scanner sc) {
        System.out.println("Nhap ten bai hat:");
        String name = sc.nextLine();
        System.out.println("Nhap ca sy:");
        String singer = sc.nextLine();
        Song song = new Song(name,singer);
        return song;
    }

    public void createSongs(Scanner sc, List<Song> songs) {
        System.out.println("Nhap so bai hat:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            songs.add(newSong(sc));
        }
    }
}
