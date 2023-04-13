package handle;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import entity.Worker;

public class WorkerHandle {
    public Worker createWorker(Scanner sc) {
        InputControl inputControl = new InputControl();
        System.out.println("Nhap ten:");
        String name = sc.nextLine();
        System.out.println("Nhap tuoi:");
        int age = inputControl.getInput(sc, 1, null);
        System.out.println("Nhap muc luong:");
        double salary = inputControl.getInput(sc, 0, null);
        System.out.println("Nhap dia diem lam viec:");
        String workplace = sc.nextLine();
        return new Worker(name, age, salary, workplace);
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

    public Worker findWorker(Scanner sc, List<Worker> workers, int id) {
        Worker returnValue = null;
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getId() == id) {
                returnValue = workers.get(i);
                break;
            } else {
                returnValue = null;
            }
        }
        return returnValue;
    }

    public void displayWorker(List<Worker> workers) {
        System.out.println("W No.\t  Name\t  Age\t  Salary\t Status\t Date");
        for (int i = 0; i < workers.size(); i++) {
            System.out.println(workers.get(i));
        }
    }

    public void salaryChange(Scanner sc, List<Worker> workers, List<Worker> salaryLog, int choice) {
        System.out.println("Nhap id cua cong nhan can giam luong:");
        InputControl inputControl = new InputControl();
        int id = inputControl.getInput(sc, 1, null);

        // Tim id trong list
        if (findWorker(sc, workers, id) != null) {
            int index = workers.indexOf(findWorker(sc, workers, id));
            System.out.println("Nhap muc luong giam di:");
            double salaryChanged = inputControl.getInput(sc, 0, null);
            switch (choice) {
                case 1: {
                    double salaryOld = workers.get(index).getSalary();
                    workers.get(index).setSalary(salaryOld + salaryChanged);
                    System.out.println("Hoan thanh.");
                    // Record vao log
                    salaryLog.add(workers.get(index));
                    salaryLog.get(salaryLog.size()-1).setSalaryDate(LocalDate.now());
                    salaryLog.get(salaryLog.size()-1).setSalaryStatus("UP");
                    break;
                }
                case 2: {
                    // Check co vuot qua muc hien tai khong
                    if (findWorker(sc, workers, id).getSalary() > salaryChanged) {
                        double salaryOld = workers.get(index).getSalary();
                        workers.get(index).setSalary(salaryOld - salaryChanged);
                        System.out.println("Hoan thanh.");

                        // Record vao log
                        salaryLog.add(workers.get(index));
                        salaryLog.get(salaryLog.size()-1).setSalaryDate(LocalDate.now());
                        salaryLog.get(salaryLog.size()-1).setSalaryStatus("DOWN");
                    } else {
                        System.out.println("Muc giam tru vuot qua muc hien tai.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Khong tim thay id " + id);
        }
    }

    public void recordLog() {

    }
}
