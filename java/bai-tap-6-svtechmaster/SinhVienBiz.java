public class SinhVienBiz extends SinhVienTechMaster {
    private double diemMarketing, diemSales;

    public void setDiemMarketing(double diemMarketing) {
        this.diemMarketing = diemMarketing;
    }

    public void setDiemSales(double diemSales) {
        this.diemSales = diemSales;
    }

    @Override
    public double getDiem() {
        return ((diemMarketing * 2 + diemSales) / 3);
    };

}
