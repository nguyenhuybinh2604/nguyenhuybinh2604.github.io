import java.util.Scanner;

import entity.SummonRift;
import handle.SummonRiftHandle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SummonRiftHandle summonRiftHandle = new SummonRiftHandle();
        SummonRift summonRift = summonRiftHandle.initiateMatch(sc);
        summonRiftHandle.displayInfo(summonRift);
    }
}