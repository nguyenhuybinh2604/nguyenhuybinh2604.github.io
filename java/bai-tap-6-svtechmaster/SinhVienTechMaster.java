public abstract class SinhVienTechMaster {
    public String name;
    public String major;

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public abstract double getDiem();

    public String getHocLuc() {
        if (getDiem() < 5) {
            return "Yeu";
        } else if (getDiem() >= 5 && getDiem() < 6.5) {
            return "Trung binh";
        } else if (getDiem() >= 6.5 && getDiem() < 7.5) {
            return "Kha";
        } else {
            return "Gioi";
        }
    };
    public String xuat(){
        return " Ho ten: " + name + "; " +
        " Nganh: " + major + "; " +
        " Diem: " + getDiem() + "; " +
        " Hoc luc: " + getHocLuc() + "; "
        ;
    };

}
