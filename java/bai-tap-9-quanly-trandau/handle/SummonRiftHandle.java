package handle;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.SummonRift;
import entity.Team;

public class SummonRiftHandle {
    public SummonRift initiateMatch(Scanner sc) {
        System.out.println("Nhap thoi gian bat dau tran dau (HH:mm):");
        LocalTime timeBegin = LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        TeamHandle teamHandle = new TeamHandle();
        List<Team> teams = new ArrayList<>();
        System.out.println("Thiet lap team SKT:");
        teams.add(teamHandle.createTeam(sc, "SKT"));
        System.out.println("Thiet lap team G2:");
        teams.add(teamHandle.createTeam(sc, "G2"));
        return new SummonRift(timeBegin, teams);
    }

    public void displayInfo(SummonRift summonRift) {
        System.out.println(summonRift);
    }
}
