
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Tiktok;
import handle.handleTiktok;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Tiktok> tiktoks = new ArrayList<>();

        handleTiktok handleTiktok = new handleTiktok();
        
        handleTiktok.createTiktoks(sc, tiktoks);
        System.out.println(tiktoks);
    }
}