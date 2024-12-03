package cybersoft;

public abstract class NhanVien {

	private int maSo;
	private String hoTen;
	private String soDienThoai;
	private int soNgayLamViec;
	private double luongMotNgay;

	public NhanVien(int maSo, String hoTen, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
		this.maSo = maSo;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.soNgayLamViec = soNgayLamViec;
		this.luongMotNgay = luongMotNgay;
	}

	public NhanVien() {
	}

	public int getMaSo() {
		return maSo;
	}

	public void setMaSo(int maSo) {
		this.maSo = maSo;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getSoNgayLamViec() {
		return soNgayLamViec;
	}

	public void setSoNgayLamViec(int soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}

	public double getLuongMotNgay() {
		return luongMotNgay;
	}

	public void setLuongMotNgay(double luongMotNgay) {
		this.luongMotNgay = luongMotNgay;
	}

	public abstract double tinhLuongThang();

	public void hienThiThongTinNhanVien() {
		System.out.println("Mã số: " + this.maSo + " - Họ và tên: " + this.hoTen + " - Số điện thoại: " + this.soDienThoai
						+ " - Số ngày làm việc: " + this.soNgayLamViec + " - Lương một ngày: " + this.luongMotNgay);
	}

}
