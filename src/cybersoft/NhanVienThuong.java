package cybersoft;

public class NhanVienThuong extends NhanVien {

	private TruongPhong truongPhongHienTai;

	public NhanVienThuong(int maSo, String hoTen, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
		super(maSo, hoTen, soDienThoai, soNgayLamViec, luongMotNgay);
	}

	public TruongPhong getTruongPhongHienTai() {
		return truongPhongHienTai;
	}

	public void setTruongPhongHienTai(TruongPhong truongPhongHienTai) {
		this.truongPhongHienTai = truongPhongHienTai;
	}

	@Override
	public double tinhLuongThang() {
		return this.getLuongMotNgay() * this.getSoNgayLamViec();
	}

}
