package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Idol;
import entity.Song;
import entity.Tiktok;

public class handleTiktok {
    public Tiktok newTiktok(Scanner sc) {

        List<Idol> idols = new ArrayList<>();
        handleIdol handleIdol = new handleIdol();
        handleIdol.createIdol(sc, idols);

        List<Song> songs = new ArrayList<>();
        handleSong handleSong = new handleSong();
        handleSong.createSongs(sc, songs);

        Tiktok tiktok = new Tiktok(idols, songs);
        return tiktok;
    }

    public void createTiktoks(Scanner sc, List<Tiktok> tiktoks) {
        System.out.println("Nhap so tiktok:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            tiktoks.add(newTiktok(sc));
        }
    }

}
