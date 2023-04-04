import java.time.LocalDate;

public class Patient extends Person {
    LocalDate hospitalizeDate;
    String recordID;

    public Patient() {
    }

    public Patient(String name, int age, String address, LocalDate hospitalizeDate, String recordID) {
        super(name, age, address);
        this.hospitalizeDate = hospitalizeDate;
        this.recordID = recordID;
    }

    public LocalDate getHospitalizeDate() {
        return this.hospitalizeDate;
    }

    public void setHospitalizeDate(LocalDate hospitalizeDate) {
        this.hospitalizeDate = hospitalizeDate;
    }

    public String getRecordID() {
        return this.recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    @Override
    public String toString() {
        return super.toString() +
        " Ngay nhap vien=" + getHospitalizeDate() + " " +
                ", So benh an=" + getRecordID() + " ";
    }

}
