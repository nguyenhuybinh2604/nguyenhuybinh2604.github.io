public class SinhVienIT extends SinhVienTechMaster {
    private double diemJava, diemHtml, diemCss;

    public void setDiemJava(double diemJava) {
        this.diemJava = diemJava;
    }

    public void setDiemHtml(double diemHtml) {
        this.diemHtml = diemHtml;
    }

    public void setDiemCss(double diemCss) {
        this.diemCss = diemCss;
    }

    @Override
    public double getDiem() {
        return ((diemJava * 2 + diemHtml + diemCss) / 4);
    };

}
