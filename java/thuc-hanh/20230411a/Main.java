
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.SummonRift;
import handle.handleSummonRift;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<SummonRift> summonRifts = new ArrayList<>();

        handleSummonRift handleSummonRift = new handleSummonRift();
        
        handleSummonRift.createSummonRifts(sc, summonRifts);
        System.out.println(summonRifts);
    }
}