import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManageHospital {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so bac sy: ");
        int numberDoctor = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap so benh nhan: ");
        int numberPatient = Integer.parseInt(sc.nextLine());
        if (numberDoctor >= 0 && numberPatient >= 0) {
            Doctor[] doctors = new Doctor[numberDoctor];
            Patient[] patients = new Patient[numberPatient];
            for (int i = 0; i < numberDoctor; i++) {
                Doctor doctor = new Doctor();
                System.out.println("Nhap thong tin bac sy thu " + (i + 1) + ":");
                System.out.print("Ten: ");
                doctor.name = sc.nextLine();
                System.out.print("Tuoi: ");
                doctor.age = Integer.parseInt(sc.nextLine());
                System.out.print("Dia chi: ");
                doctor.address = sc.nextLine();
                System.out.print("Chuyen khoa: ");
                doctor.department = sc.nextLine();
                System.out.print("So gio lam viec: ");
                doctor.workingHour = Double.parseDouble(sc.nextLine());
                doctors[i] = doctor;
            }
            for (int i = 0; i < numberPatient; i++) {
                Patient patient = new Patient();
                System.out.println("Nhap thong tin benh nhan thu " + (i + 1) + ":");
                System.out.print("Ten: ");
                patient.name = sc.nextLine();
                System.out.print("Tuoi: ");
                patient.age = Integer.parseInt(sc.nextLine());
                System.out.print("Dia chi: ");
                patient.address = sc.nextLine();
                System.out.print("So benh an: ");
                patient.recordID = sc.nextLine();
                System.out.print("Ngay nhap vien: ");
                patient.hospitalizeDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                patients[i] = patient;
            }
            for (int i = 0; i < numberDoctor; i++) {
                System.out.println("Bac sy thu "+ (i+1) + ": " + doctors[i].toString());
            }
            for (int i = 0; i < numberPatient; i++) {
                System.out.println("Benh nhan thu "+ (i+1) + ": " + patients[i].toString());
            }
        } else {
            System.out.println("Phai nhap so nguyen khong am.");
        }
    }
}