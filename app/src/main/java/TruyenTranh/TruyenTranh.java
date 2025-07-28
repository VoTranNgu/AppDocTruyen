package TruyenTranh;

public class TruyenTranh {
    private  String tenTruyen;
    private String tenChap;

    public int getImgTruyen() {
        return imgTruyen;
    }

    public void setImgTruyen(int imgTruyen) {
        this.imgTruyen = imgTruyen;
    }

    private int imgTruyen;
    public TruyenTranh(){

    }

    public TruyenTranh(String tenTruyen,int imgTruyen) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.imgTruyen = imgTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }
}
