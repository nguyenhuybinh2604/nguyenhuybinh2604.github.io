package handle;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import entity.Pet;

public class PetHandle {
    public Pet createPet(Scanner sc) {
        InputControl inputControl = new InputControl();
        
        
        
    }

    public void addWorker(Scanner sc, List<Worker> workers, List<Worker> salaryLog) {
        InputControl inputControl = new InputControl();
        System.out.println("Nhap so cong nhan can them:");
        int n = inputControl.getInput(sc, 0, null);
        for (int i = 0; i < n; i++) {
            Worker newWorker = createWorker(sc);
            workers.add(newWorker);
            // Record vao log
            salaryLog.add(newWorker);
        }
    }

    
}
