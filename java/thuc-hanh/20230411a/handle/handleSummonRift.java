package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.SummonRift;
import entity.Team;

public class handleSummonRift {
    public SummonRift newSummonRift(Scanner sc) {
System.out.println("Nhap thoi gian bat dau:");
String timeBegin = sc.nextLine();

        List<Team> teams = new ArrayList<>();

        handleTeam handleteam = new handleTeam();
        handleteam.createTeam(sc, teams);

        SummonRift SummonRift = new SummonRift(timeBegin, teams);
        return SummonRift;
    }

    public void createSummonRifts(Scanner sc, List<SummonRift> SummonRifts) {
        System.out.println("Nhap so SummonRift:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            SummonRifts.add(newSummonRift(sc));
        }
    }

}
